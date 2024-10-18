package account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Class_num;
import bean.Teacheraccount;
import dao.AccountDAO;
import dao.ClassDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherloginAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();

		String teacher_id=request.getParameter("teacher_id");
		String password=request.getParameter("password");

		AccountDAO dao=new AccountDAO();
		Teacheraccount account=dao.teacher_search(teacher_id, password);
		
		ClassDAO cdao=new ClassDAO();
		List<Class_num> class_num=cdao.getallclass();
		
		
		String today_temperature = "変更されていない";
		Document Doc = Jsoup.connect("https://www.msn.com/ja-jp/weather/forecast/").get();
		Element Elm = Doc.body();
		Elements temperatures = Elm.getElementsByClass("summaryLineGroupCompact-E1_1");
		
		
		
		for(Element temperature : temperatures) {
			today_temperature = temperature.text();
		}
		ArrayList<String> today_temperature_data  = new ArrayList<String>(Arrays.asList(today_temperature.split(" ")));
		today_temperature_data.add(0,"現在の気温");
		today_temperature_data.add(2,"現在の天気");
		
		if (account!=null) {
			session.setAttribute("account", account);
			session.setAttribute("class_num", class_num);
			session.setAttribute("today_temperature_data", today_temperature_data);
			return "../common/top.jsp";
		}
		request.setAttribute("login_error", "IDまたはパスワードが確認できませんでした");
		return "student_login.jsp";
	}
}