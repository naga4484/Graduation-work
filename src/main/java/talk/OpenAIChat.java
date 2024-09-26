package talk;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.JSONObject;

public class OpenAIChat {

    private static final String API_KEY = "your-api-key-here";  // OpenAIのAPIキーをここに設定
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();

        System.out.println("OpenAI Chatbotへようこそ！終了するには 'exit' と入力してください。");

        while (true) {
            System.out.print("あなた: ");
            String userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("チャットを終了します。");
                break;
            }

            // OpenAIのAPIにPOSTするデータを作成
            String response = getAIResponse(client, userInput);
            System.out.println("AI: " + response);
        }

        scanner.close();
    }

    // APIリクエストを送信してAIからの応答を取得するメソッド
    private static String getAIResponse(HttpClient client, String userInput) throws Exception {
        JSONObject json = new JSONObject();
        json.put("model", "gpt-3.5-turbo");  // 使用するモデルを指定
        json.put("messages", new JSONObject[] {
            new JSONObject().put("role", "system").put("content", "You are a helpful assistant."),
            new JSONObject().put("role", "user").put("content", userInput)
        });

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content").trim();
    }
}
