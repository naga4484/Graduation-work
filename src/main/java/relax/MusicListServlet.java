package relax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/relax/musicList") // サーブレットのURLパス
public class MusicListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // webapp/music フォルダのパスを取得
        String musicDirectoryPath = getServletContext().getRealPath("/music");
        File musicDirectory = new File(musicDirectoryPath);
        List<String> musicFiles = new ArrayList<>();

        // music フォルダが存在し、ディレクトリの場合のみ処理を行う
        if (musicDirectory.exists() && musicDirectory.isDirectory()) {
            // ディレクトリ内のファイルをループ処理
            for (File file : musicDirectory.listFiles()) {
                // .mp3ファイルのみをリストに追加
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    musicFiles.add(file.getName());
                }
            }
        }

        // JSON形式で音楽ファイルリストをレスポンスとして返す
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Gsonを使用してリストをJSON形式に変換
        Gson gson = new Gson();
        String json = gson.toJson(musicFiles);
        response.getWriter().write(json);
    }
}
