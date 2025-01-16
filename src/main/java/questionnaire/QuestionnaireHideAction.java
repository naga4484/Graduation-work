package questionnaire;

import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireHideAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User_id user = (User_id) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("errorMessage", "ログインが必要です");
            return "../account/login.jsp";
        }

        String questionnaireId = request.getParameter("questionnaireId");
        if (questionnaireId == null || questionnaireId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "アンケートIDが指定されていません");
            return "QuestionnaireParticipateList.action";
        }

        try {
            QuestionnaireDAO dao = new QuestionnaireDAO();
            dao.hideQuestionnaireForUser(user.getUser_id(), questionnaireId);
            request.setAttribute("message", "アンケートを非表示にしました");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "アンケートの非表示中にエラーが発生しました");
        }

        return "QuestionnaireParticipateList.action";
    }
}
