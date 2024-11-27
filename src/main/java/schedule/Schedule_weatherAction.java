package schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.User_id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class Schedule_weatherAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

			HttpSession session=request.getSession();
			User_id select_user_id = (User_id)session.getAttribute("user");
			if(select_user_id == null) {
				return "../account/Error_function.action";
			}

			// 天気情報を取得する処理
	        List<List<String>> todayTemperatureData = new ArrayList<>();
	        List<String> tempdays_list = new ArrayList<>();
	        
            // Yahoo!の天気サイトから7日分のデータを取得(今日から7日分のみの取得)
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	        LocalDate today = LocalDate.now();
	        tempdays_list.add(today.format(formatter));
	        for(int i = 1;i <= 7;i++) {
	        	LocalDate days_num = today.plusDays(i);
	        	tempdays_list.add(days_num.format(formatter));
	        }
            
	        String inner_temperature_name = "";
            for(int i = 1;i <= 8;i++) {
            	String link = "https://weather.yahoo.co.jp/weather/jp/33/?day=" + i;
            	Document docin = Jsoup.connect(link).get();
                Element elmin = docin.body();
                Elements temperatures = elmin.getElementsByClass("point pt6610");
                List<String> inner_temperature =  new ArrayList<>();
                for(Element el:temperatures) {
                	// 地名を取得
                    String name = el.select("dt.name").text().trim();

                    // 最高気温、最低気温を取得
                    String highTemp = el.select("em.high").text();
                    String lowTemp = el.select("em.low").text();
                    String precipitation =  el.select("p.precip").text();

                    // 天気予報アイコンの alt 属性（天気の説明）を取得
                    String weather = el.select("p.icon img").attr("alt");
                    
                    inner_temperature_name=name;
                    inner_temperature.add(weather);
                    inner_temperature.add(highTemp + "℃");
                    inner_temperature.add(lowTemp + "℃");
                    inner_temperature.add(precipitation);
                }
                todayTemperatureData.add(inner_temperature);
            }
			
            session.setAttribute("temperature_name", inner_temperature_name);
            session.setAttribute("weeks_days", tempdays_list);
            session.setAttribute("weeks_weather", todayTemperatureData);
			return "schedule_weather.jsp";
		}
	}