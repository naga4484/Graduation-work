package teacher_function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import bean.Studentaccount;
import bean.User_id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_auto_create_retryAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			session.removeAttribute("reader_flag");
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			Random rand = new Random();
			int member_num = (int)session.getAttribute("member_num");
			String flag = (String)session.getAttribute("flag");
			List<Studentaccount> st_list = (List<Studentaccount>)session.getAttribute("copy_st_list");
			List<Studentaccount> copy_st_list = new ArrayList<>(st_list);
			session.setAttribute("copy_st_list", copy_st_list);
			
			//グループ数の計算
			int group_num = (int)session.getAttribute("group_num");
			
			HashMap<String, ArrayList<Studentaccount>> group_list = new HashMap<>();
			//リーダーを設定しない場合
			if(flag.equals("off") == true) {
				for(int i = 0;i < group_num;i++) {
					ArrayList<Studentaccount> list = new ArrayList<>();
					for(int j = 0;j < member_num;j++) {
						
						//学生が残っている場合のみ
						if(st_list.size() != 0) {
							//元のstudentのリストから学生情報を抜き取る
							Studentaccount st = st_list.remove(rand.nextInt(st_list.size()));
							list.add(st);
						}
					}
					group_list.put("List_" + i, list);
				}
			}
			//リーダーを設定する場合
			else if(flag.equals("on") == true) {
				List<Studentaccount> reader_list = (List<Studentaccount>)session.getAttribute("copy_reader_list");
				List<Studentaccount> member_list = (List<Studentaccount>)session.getAttribute("copy_member_list");
				List<Studentaccount> copy_reader_list = new ArrayList<>(reader_list);
				List<Studentaccount> copy_member_list = new ArrayList<>(member_list);
				session.setAttribute("copy_reader_list", copy_reader_list);
				session.setAttribute("copy_member_list", copy_member_list);
				member_num -= 1;
				
				
				//グループの振り分け
				for(int i = 0;i < group_num;i++) {
					ArrayList<Studentaccount> list = new ArrayList<>();
					//リーダーアカウントの挿入
					Studentaccount rd = reader_list.remove(0);
					list.add(rd);
					for(int j = 0;j < member_num;j++) {
						//学生が残っている場合のみ
						if(member_list.size() != 0) {
							//元のstudentのリストから学生情報を抜き取る
							Studentaccount st = member_list.remove(rand.nextInt(member_list.size()));
							list.add(st);
						}
					}
					group_list.put("List_" + i, list);
				}
				session.setAttribute("reader_flag", "リーダーを設定しています");
			}
			
			request.setAttribute("retry_mes", "再抽選しました");
			session.setAttribute("group_num", group_num);
			session.setAttribute("group_list", group_list);
			return "group_construction.jsp";
			
		}
	}