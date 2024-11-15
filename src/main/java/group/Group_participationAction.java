package group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_participationAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			User_id user_id = (User_id)session.getAttribute("user");
			
			String group_id=request.getParameter("group_id");
			
			GroupDAO dao=new GroupDAO();
			Group id_group = dao.search_group_id(group_id);
			
			if(id_group == null) {
				request.setAttribute("none_error", "入力したIDは存在しません");
				return "group_create_participation.jsp";
			}
			
			
			//csvファイルを読み込む(これも本番環境では無理そう)
			FileInputStream fis = new FileInputStream(id_group.getAccept_user());
			
			List<String> strlist = new ArrayList<>(); 

			try {
				//UTF-8で読み込む
				BufferedReader b_reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				try (Scanner scanner = new Scanner(b_reader)) {
					while (scanner.hasNext()) { // 次に読み込むべき行があるか判定
					    String line = scanner.next(); // 1行を読み込む
					    strlist.add(line); // strlistに追加
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			for(String i:strlist) {
				if(Integer.toString(user_id.getUser_id()).equals(i) == true) {
					request.setAttribute("dis_par_error", "既にそのグループに参加しています");
					return "group_create_participation.jsp";
				}
			}
			
			
			//csvファイル書き込み用(恐らく本番環境では使用できない)
			try {
	            // 出力ファイルの作成
	            FileWriter fw = new FileWriter(id_group.getAccept_user(), true);
	            // PrintWriterクラスのオブジェクトを生成
	            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
	 
	            // データを書き込む
                pw.print(user_id.getUser_id());
                pw.println();
	            
	 
	            // ファイルを閉じる
	            pw.close();
	      
	 
	        // 出力に失敗したときの処理
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			
			request.setAttribute("comp_mes", "グループ名「" + id_group.getGroup_name() + "」に参加しました");
			return "group_top.jsp";
	}
}