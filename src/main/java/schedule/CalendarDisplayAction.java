package schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/schedule/CalendarDisplayAction")
public class CalendarDisplayAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // カレンダーから選択された日付を取得
        String selectedDate = request.getParameter("selectedDate");
        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = "日付が選択されていません";
        }
        request.setAttribute("selectedDate", selectedDate);

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

        // リクエスト属性に天気データを設定
        request.setAttribute("today_temperature_data", todayTemperatureData);

        // calendar_display.jspへフォワード
        request.getRequestDispatcher("/schedule/calendar_display.jsp").forward(request, response);
    }
}
