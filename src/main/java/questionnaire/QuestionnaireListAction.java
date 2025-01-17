package questionnaire;

import java.util.List;

import bean.Questionnaire;
import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        // ログインユーザーを取得
        User_id user = (User_id) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("errorMessage", "ログインが必要です");
            return "../account/login.jsp";
        }

        try {
            QuestionnaireDAO dao = new QuestionnaireDAO();

            // 全アンケートを取得
            List<Questionnaire> questionnaireList = dao.getAllQuestionnaires();

            // 作成者情報を確認して追加
            for (Questionnaire questionnaire : questionnaireList) {
                boolean isAnswered = dao.isAnswered(questionnaire.getQuestionnaireId(), user.getUser_id());
                questionnaire.setAnswered(isAnswered);

                // 現在ログイン中のユーザーが作成者かどうかを設定
                questionnaire.setCreator(user.getUser_id() == questionnaire.getUserId());
            }

            // アンケートリストをリクエストスコープに設定
            request.setAttribute("questionnaireList", questionnaireList);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "アンケート一覧の取得中にエラーが発生しました");
        }

        return "../questionnaire/questionnaire_list.jsp";
    }
}
