package questionnaire;

import java.util.List;

import bean.Questionnaire;
import bean.QuestionnaireOption;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireResultAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // ユーザー情報の確認
        Object user = session.getAttribute("user");
        if (user == null) {
            request.setAttribute("errorMessage", "ログインが必要です");
            return "../account/login.jsp";
        }

        // パラメータからアンケートIDを取得
        String questionnaireId = request.getParameter("questionnaireId");
        if (questionnaireId == null || questionnaireId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "アンケートIDが指定されていません");
            return "questionnaire_list.jsp";
        }

        try {
            QuestionnaireDAO dao = new QuestionnaireDAO();

            // アンケート情報を取得
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaire.setTitle(dao.getQuestionnaireTitle(questionnaireId));
            questionnaire.setQuestionnaire(dao.getQuestionnaireContent(questionnaireId));

            // 選択肢と回答結果を取得
            List<QuestionnaireOption> options = dao.getQuestionnaireOptions(questionnaireId);
            List<Integer> results = dao.getAnswerCounts(questionnaireId);

            // 各選択肢に回答数を設定
            for (int i = 0; i < options.size(); i++) {
                int count = (i < results.size()) ? results.get(i) : 0; // 回答数が不足している場合は0を設定
                options.get(i).setCount(count); // countプロパティを設定
            }
            questionnaire.setOptions(options);

            // デバッグログ
            System.out.println("アンケート結果:");
            for (QuestionnaireOption option : options) {
                System.out.println(option.getOptionContent() + " (" + option.getCount() + ")");
            }

            // リクエストスコープに設定
            request.setAttribute("questionnaire", questionnaire);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "アンケート結果の取得中にエラーが発生しました");
            return "questionnaire_list.jsp";
        }

        return "../questionnaire/questionnaire_result.jsp";
    }
}
