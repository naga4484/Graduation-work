package questionnaire;

import java.util.ArrayList;
import java.util.List;

import bean.Questionnaire;
import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireCreateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // ユーザー情報を取得
        User_id user = (User_id) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "ログインが必要です");
            return "../account/login.jsp";
        }

        // フォームデータを取得
        String title = request.getParameter("title");
        String question = request.getParameter("question");
        List<String> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            String option = request.getParameter("answer" + i);
            if (option != null && !option.trim().isEmpty()) {
                options.add(option.trim());
            }
        }

        // デバッグ: 受信した選択肢をログ出力
        System.out.println("Received options:");
        for (String option : options) {
            System.out.println(option);
        }

        // 入力チェック
        if (title == null || title.trim().isEmpty() ||
            question == null || question.trim().isEmpty() ||
            options.size() < 2) {
            request.setAttribute("error", "全ての項目を正しく入力してください。選択肢は最低2つ必要です。");
            return "questionnaire_create.jsp";
        }

        // アンケートデータを準備
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireId(generateQuestionnaireId());
        questionnaire.setTitle(title.trim());
        questionnaire.setQuestionnaire(question.trim());
        questionnaire.setUserId(user.getUser_id());

        // DAOを使用してデータベースに保存
        QuestionnaireDAO dao = new QuestionnaireDAO();
        try {
            dao.saveQuestionnaireWithOptions(questionnaire, options);
            // 成功メッセージをセッションに保存
            session.setAttribute("message", "アンケートを作成しました");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "アンケートの保存中にエラーが発生しました");
            return "questionnaire_create.jsp";
        }

        // アンケート一覧画面にリダイレクト
        return "QuestionnaireList.action";
    }

    private String generateQuestionnaireId() {
        int randomId = (int) (Math.random() * 9000) + 1000;
        return "Q" + randomId;
    }
}
