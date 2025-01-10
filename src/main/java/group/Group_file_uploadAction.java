package group;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

public class Group_file_uploadAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			User_id user_id = (User_id)session.getAttribute("user");
			
			
			GroupDAO dao=new GroupDAO();
			List<Group> group_list = dao.list_group();
			List<Group> my_group_list = new ArrayList<>();
			
			
			for(Group i : group_list) {
				//csvファイルを読み込む(これも本番環境では無理そう)
				FileInputStream fis = new FileInputStream(i.getAccept_user());
				
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
				
				for(String j:strlist) {
					if(Integer.toString(user_id.getUser_id()).equals(j) == true) {
						my_group_list.add(i);
					}
				}
			}
			if(my_group_list.size() == 0) {
				request.setAttribute("no_par_error", "参加しているグループが存在しません");
				return "group_top.jsp";
			}
			session.setAttribute("my_group_list", my_group_list);
			return "group_file_upload.jsp";
	}
}