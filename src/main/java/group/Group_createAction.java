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

			User_id user_id = (User_id)session.getAttribute("user");
			
			String group_name=request.getParameter("group_name");
			String group_id=request.getParameter("group_id");
			
			GroupDAO dao=new GroupDAO();
			Group name_group = dao.search_group_name(group_name);
			Group id_group = dao.search_group_id(group_id);
			
			if(name_group != null) {
				if(id_group != null) {
					request.setAttribute("dis_cre_error", "グループ名とグループIDが重複しています");
					return "group_create_participation.jsp";
				}
				request.setAttribute("dis_cre_error", "グループ名が重複しています");
				return "group_create_participation.jsp";
			}
			if(id_group != null) {
				request.setAttribute("dis_cre_error", "グループIDが重複しています");
				return "group_create_participation.jsp";
			}
			
			
			//csvファイル書き込み用(恐らく本番環境では使用できない)
			try {
				//ファイル名
				String file_name = group_name + "_user.csv";
	            // 出力ファイルの作成
	            FileWriter fw = new FileWriter(file_name, false);
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
	            
	            int line = dao.group_create(group_id, group_name, file_name, user_id.getUser_id());
	      
	 
	        // 出力に失敗したときの処理
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
			
			request.setAttribute("comp_mes", "グループ名「" + group_name + "」を作成しました");
			return "group_top.jsp";
	}
}