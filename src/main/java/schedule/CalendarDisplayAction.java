package schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Calendar;
import bean.Studentaccount;
import bean.Subject;
import bean.Teacheraccount;
import bean.Timetable;
import bean.User_id;
import dao.CalendarDAO;
import dao.SubjectDAO;
import dao.TimetableDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

@WebServlet("/schedule/CalendarDisplayAction")
public class CalendarDisplayAction  extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
        // カレンダーから選択された日付を取得
        String selectedDate = request.getParameter("selectedDate");
        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = "日付が選択されていません";
        }
        HttpSession session=request.getSession();
        session.setAttribute("selectedDate", selectedDate);

        // 天気情報を取得する処理
        String todayTemperature = "情報取得エラー";
        List<String> todayTemperatureData = new ArrayList<>();

        try {
            // MSN天気情報から気温データを取得
            Document doc = Jsoup.connect("https://www.msn.com/ja-jp/weather/forecast/").get();
            Element elm = doc.body();
            Elements temperatures = elm.getElementsByClass("summaryLineGroupCompact-E1_1");

            for (Element temperature : temperatures) {
                todayTemperature = temperature.text();
            }

            todayTemperatureData = new ArrayList<>(Arrays.asList(todayTemperature.split(" ")));
            todayTemperatureData.add(0, "現在の気温");
            todayTemperatureData.add(2, "現在の天気");
        } catch (Exception e) {
            e.printStackTrace();
            todayTemperatureData = Arrays.asList("情報取得エラー");
        }
        TimetableDAO dao = new TimetableDAO();
        String[] data_list = selectedDate.split("/");
        String year_data = data_list[0];
        String month_data = data_list[1];
        String date_data = data_list[2];
        if(month_data.length() == 1) {
        	month_data = "0" + month_data;
        }
        if(date_data.length() == 1) {
        	date_data = "0" + date_data;
        }
        String data = year_data + "年" + month_data + "月" + date_data + "日";
        User_id user_id = (User_id)session.getAttribute("user");
        SubjectDAO sdao=new SubjectDAO();
        if(user_id.getStudent_id() == null) {
        	Teacheraccount account = (Teacheraccount)session.getAttribute("account");
        	List<Timetable> timetable = dao.timetable_search(account.getClass_id(), data);
        	session.setAttribute("schedule_timetable", timetable);
        	
    		List<Subject> class_subject = sdao.getclasssubject(account.getClass_id());
    		session.setAttribute("class_subject", class_subject);
        	
        }else if(user_id.getTeacher_id() == null) {
        	Studentaccount account = (Studentaccount)session.getAttribute("account");
        	List<Timetable> timetable = dao.timetable_search(account.getClass_id(), data);
        	session.setAttribute("schedule_timetable", timetable);
        	
    		List<Subject> class_subject = sdao.getclasssubject(account.getClass_id());
    		session.setAttribute("class_subject", class_subject);
        }
        
        CalendarDAO cdao = new CalendarDAO();
        List<Calendar> calender_list = cdao.calender_list(user_id.getUser_id(), selectedDate);
        session.setAttribute("cal_list", calender_list);
        

        // リクエスト属性に天気データを設定
        request.setAttribute("today_temperature_data", todayTemperatureData);

        return "../schedule/calendar_display.jsp";
    }
}
