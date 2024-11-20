package teacher_function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import bean.Studentaccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Group_auto_createAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();

			Random rand = new Random();
			String class_id=request.getParameter("class_id");
			int member_num = Integer.parseInt(request.getParameter("group_member_num"));
			String flag = request.getParameter("reader_flag");
			List<Studentaccount> st_list = (List<Studentaccount>)session.getAttribute("st_list");
			
			//グループ数の計算
			int group_num = (int)Math.ceil(st_list.size()/(double)member_num);
			
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
				List<Studentaccount> reader_list = new ArrayList<>();
				
				//指定したリーダーメンバーの取得
				for(int i = 0;i <= st_list.size();i++) {
					String reader_flag=request.getParameter("reader_" + i);
					if(reader_flag.equals("リーダー")==true) {
						reader_list.add(st_list.get(i));
					}
				}
				
				//リーダーメンバーの人数とグループ数が一致するか判定
				if(group_num != reader_list.size()) {
					request.setAttribute("num_error", "リーダーの人数とグループ数が一致しません");
					return "group_auto_create.jsp";
				}
			}
			
			session.setAttribute("group_num", group_num);
			session.setAttribute("group_list", group_list);
			return "group_construction.jsp";
			
		}
	}