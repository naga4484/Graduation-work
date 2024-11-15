package account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Class_num;
import bean.Studentaccount;
import bean.Subject;
import bean.User_id;
import dao.AccountDAO;
import dao.ClassDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentloginAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        String student_id = request.getParameter("student_id");
        String password = request.getParameter("password");

        AccountDAO dao = new AccountDAO();
        Studentaccount account = dao.student_search(student_id, password);
        if(account == null) {
        	request.setAttribute("login_error", "IDまたはパスワードが確認できませんでした");
            return "student_login.jsp";
        }

        ClassDAO cdao = new ClassDAO();
        List<Class_num> class_num = cdao.getallclass();
        
        SubjectDAO sdao=new SubjectDAO();
		List<Subject> class_subject = sdao.getclasssubject(account.getClass_id());
        
        UserDAO udao = new UserDAO();
		User_id user_id = udao.user_student(student_id);
		if(user_id == null) {
			int line = udao.user_stu_insert(student_id,account.getName());
			user_id = udao.user_student(student_id);
		}

        // 天気情報を取得するための処理
        String today_temperature = "変更されていない";
        Document doc = Jsoup.connect("https://www.msn.com/ja-jp/weather/forecast/").get();
        Element elm = doc.body();
        Elements temperatures = elm.getElementsByClass("summaryLineGroupCompact-E1_1");

        for (Element temperature : temperatures) {
            today_temperature = temperature.text();
        }

        ArrayList<String> today_temperature_data = new ArrayList<>(Arrays.asList(today_temperature.split(" ")));
        today_temperature_data.add(0, "現在の気温");
        today_temperature_data.add(2, "現在の天気");

        if (account != null) {
            // ログイン成功時にセッションに情報を保存
        	session.setAttribute("user", user_id);
            session.setAttribute("account", account);
            session.setAttribute("class_num", class_num);
            session.setAttribute("class_subject", class_subject);
            session.setAttribute("today_temperature_data", today_temperature_data);

            // 追加：ユーザー情報をセッションに保存
            session.setAttribute("userId", account.getStudent_id()); // 学生IDをセッションに保存
            session.setAttribute("accountKind", "学生"); // アカウント種別を学生として設定
            session.setAttribute("userName", account.getName()); // 学生の名前をセッションに保存
            session.setAttribute("email", account.getAddress()); // 学生のメールアドレスをセッションに保存

            return "../common/top.jsp";
        }
        return "student_login.jsp";
    }
}
