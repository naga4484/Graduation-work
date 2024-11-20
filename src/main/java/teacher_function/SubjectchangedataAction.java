package teacher_function;

import java.util.List;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectchangedataAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			
			
			
			SubjectDAO dao=new SubjectDAO();
			List<Subject> subject=dao.getallsubject();
			int line = 0;
			if(subject == null) {
				return "Subjectlisttop.action";
			}
			
			for(Subject i : subject) {
				String id = i.getSubject_id();
				String subject_name=request.getParameter(id + "_subject_name");
				String total_unit_num = request.getParameter(id + "_total_unit");
				if(total_unit_num == "" ||total_unit_num == null) {
					total_unit_num = "0";
				}
				int total_unit=Integer.parseInt(total_unit_num);
				
				if(subject_name == "") {
					request.setAttribute("none_mes", "科目名の未入力は許容されません");
					return "Subjectlisttop.action";
				}
			}
			
			for(Subject i : subject) {
				String id = i.getSubject_id();
				String subject_name=request.getParameter(id + "_subject_name");
				String total_unit_num = request.getParameter(id + "_total_unit");
				if(total_unit_num == "" ||total_unit_num == null) {
					total_unit_num = "0";
				}
				int total_unit=Integer.parseInt(total_unit_num);
				String subject_color=request.getParameter(id+"_subject_color");
				
				
				line = dao.change_subject(id, subject_name, total_unit,subject_color);
			}
			if(line > 0) {
				request.setAttribute("change_mes", "変更が完了いたしました。");
				return "Subjectlisttop.action";
			}
			return "Subjectlisttop.action";
		}
	}