package questionnaire;

import java.util.List;

import bean.Questionnaire;
import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireParticipateListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User_id user = (User_id) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("errorMessage", "ログインが必要です");
            return "../account/login.jsp";
        }

        try {
            // DAOを使用して、削除されたアンケートを除外した一覧を取得
            QuestionnaireDAO dao = new QuestionnaireDAO();
            List<Questionnaire> questionnaireList = dao.getFilteredQuestionnaires(user.getUser_id());

            // デバッグログ: アンケート一覧を確認
            System.out.println("アンケート一覧:");
            for (Questionnaire questionnaire : questionnaireList) {
                System.out.println("ID: " + questionnaire.getQuestionnaireId() + ", 回答済み: " + questionnaire.isAnswered());
            }

            // リクエストスコープにアンケートリストを設定
            request.setAttribute("questionnaireList", questionnaireList);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "アンケート一覧の取得中にエラーが発生しました");
        }

        return "../questionnaire/questionnaire_list.jsp";
    }
}
