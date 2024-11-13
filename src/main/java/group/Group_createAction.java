package group;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import bean.Group;
import bean.User_id;
import dao.GroupDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_createAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			//自己管理における共通機能の処理
			User_id user_id = (User_id)session.getAttribute("user");
			
			String group_name=request.getParameter("group_name");
			String group_id=request.getParameter("group_id");
			
			GroupDAO dao=new GroupDAO();
			Group name_group = dao.search_group_name(group_name);
			Group id_group = dao.search_group_id(group_id);
			
			if(name_group != null) {
				if(id_group != null) {
					request.setAttribute("dis_error", "グループ名とグループIDが重複しています");
				}
				request.setAttribute("dis_error", "グループ名が重複しています");
			}
			if(id_group != null) {
				request.setAttribute("dis_error", "グループIDが重複しています");
			}
			
			System.out.println("Current directory: " + System.getProperty("user.dir"));
			try {
	            // 出力ファイルの作成
	            FileWriter fw = new FileWriter(group_name + "_user.csv", false);
	            // PrintWriterクラスのオブジェクトを生成
	            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
	 
	            // ヘッダーの指定
	            pw.print("参加ユーザー");
	            pw.println();
	 
	            // データを書き込む
                pw.print(user_id.getUser_id());
                pw.println();
	            
	 
	            // ファイルを閉じる
	            pw.close();
	 
	            // 出力確認用のメッセージ
	            System.out.println("csvファイルを出力しました");
	 
	        // 出力に失敗したときの処理
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			
			
			return "group_top.jsp";
	}
}