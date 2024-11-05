package schedule;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/WeatherAction")
public class WeatherAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String todayTemperature = "情報取得エラー";

        try {
            // MSN天気情報から気温データを取得
            Document doc = Jsoup.connect("https://www.msn.com/ja-jp/weather/forecast/").get();
            Element elm = doc.body();
            Elements temperatures = elm.getElementsByClass("summaryLineGroupCompact-E1_1");

            for (Element temperature : temperatures) {
                todayTemperature = temperature.text();
            }

            List<String> todayTemperatureData = new ArrayList<>(Arrays.asList(todayTemperature.split(" ")));
            todayTemperatureData.add(0, "現在の気温");
            todayTemperatureData.add(2, "現在の天気");

            // リクエスト属性に天気データを設定
            request.setAttribute("today_temperature_data", todayTemperatureData);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("today_temperature_data", Arrays.asList("情報取得エラー"));
        }

        // calendar_display.jspへフォワード
        request.getRequestDispatcher("/schedule/calendar_display.jsp").forward(request, response);
        return null;
    }
}
