package account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Class_num;
import bean.Subject;
import bean.Teacheraccount;
import bean.User_id;
import dao.AccountDAO;
import dao.ClassDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TeacherloginAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        String teacher_id = request.getParameter("teacher_id");
        String password = request.getParameter("password");

        AccountDAO dao = new AccountDAO();
        Teacheraccount account = dao.teacher_search(teacher_id, password);

        if (account == null) {
            request.setAttribute("login_error", "IDまたはパスワードが違います");
            return "teacher_login.jsp";
        }

        ClassDAO cdao = new ClassDAO();
        List<Class_num> class_num = cdao.getallclass();

        SubjectDAO sdao = new SubjectDAO();
        List<Subject> class_subject = sdao.getclasssubject(account.getClass_id());

        UserDAO udao = new UserDAO();
        User_id user_id = udao.user_teacher(teacher_id);
        if (user_id == null) {
            int line = udao.user_tea_insert(teacher_id,account.getName());
            user_id = udao.user_teacher(teacher_id);
        }

        String today_temperature = "変更されていない";
        Document Doc = Jsoup.connect("https://www.msn.com/ja-jp/weather/forecast/").get();
        Element Elm = Doc.body();
        Elements temperatures = Elm.getElementsByClass("summaryLineGroupCompact-E1_1");

        for (Element temperature : temperatures) {
            today_temperature = temperature.text();
        }
        ArrayList<String> today_temperature_data = new ArrayList<String>(Arrays.asList(today_temperature.split(" ")));
        today_temperature_data.add(0, "現在の気温");
        today_temperature_data.add(2, "現在の天気");

        session.setAttribute("user", user_id);
        session.setAttribute("account", account);
        session.setAttribute("class_num", class_num);
        session.setAttribute("class_subject", class_subject);
        session.setAttribute("today_temperature_data", today_temperature_data);

        // 問い合わせシステムで利用する情報を追加してる
        session.setAttribute("accountKind", "教師"); 
        session.setAttribute("userId", account.getTeacher_id()); 
        session.setAttribute("userName", account.getName()); 

        return "../common/top.jsp";
    }
}