package teacher_function;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bean.Studentaccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;


//エラー解消にAIを使用しているので
public class Group_excel_dlAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("reader_flag");

        int member_num = (int) session.getAttribute("member_num");
        String flag = (String) session.getAttribute("flag");
        HashMap<String, ArrayList<Studentaccount>> group_list =(HashMap<String, ArrayList<Studentaccount>>) session.getAttribute("group_list");
        // グループ数の計算
        int group_num = (int) session.getAttribute("group_num");

        // Excelファイル関連の処理
        Workbook outputWorkbook = new XSSFWorkbook();

        // シートを作成
        Sheet outputSheet = outputWorkbook.createSheet("group_data");

        int count = 0;

        // グループ毎に振り分け
        for (Map.Entry<String, ArrayList<Studentaccount>> entry : group_list.entrySet()) {
            // 行を作成
            Row outputRow = outputSheet.createRow(count);

            // セルを作成
            Cell outputCell_group_num = outputRow.createCell(0);
            outputCell_group_num.setCellValue("グループ" + (count + 1));

            String key = entry.getKey();
            ArrayList<Studentaccount> studentList = entry.getValue();

            int inner_count = 1;
            for (Studentaccount student : studentList) {
                Cell outputCell_name = outputRow.createCell(inner_count); 
                outputCell_name.setCellValue(student.getName());
                inner_count++;
            }
            count++;
        }

        // 日付をファイル名に付け加える
        Date date = new Date();
        String sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'").format(date);
        String fileName = "Group_" + sdf + ".xlsx";

        //レスポンスのコンテンツタイプを設定
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        
        response.reset();
        
        // ファイル名をエンコード（日本語や特殊文字を考慮）
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

        try (OutputStream out = response.getOutputStream()) {
            outputWorkbook.write(out);  // ファイルをHTTPレスポンスに書き込む
            out.flush(); // 出力を確実にフラッシュ
        } catch (Exception e) {
            e.printStackTrace();  // エラーハンドリング
        } finally {
            // ワークブックを閉じる
            outputWorkbook.close();
        }

        // リダイレクトやメッセージ設定
        request.setAttribute("retry_mes", "Excelファイルをダウンロードしました");
        return "group_construction.jsp";
    }
}