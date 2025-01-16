package questionnaire;

import java.util.List;

import bean.Questionnaire;
import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubmitAnswerAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        User_id user = (User_id) session.getAttribute("user");

        if (user == null) {
            request.setAttribute("errorMessage", "ログインが必要です");
            return "../account/login.jsp";
        }

        String questionnaireId = request.getParameter("questionnaireId");
        String selectedOption = request.getParameter("selectedOption");

        if (questionnaireId == null || questionnaireId.trim().isEmpty() || selectedOption == null) {
            System.out.println("Error: questionnaireId or selectedOption is null or empty.");
            request.setAttribute("errorMessage", "アンケートIDまたは回答が指定されていません");
            return "../questionnaire/questionnaire_answer.jsp";
        }

        try {
            QuestionnaireDAO dao = new QuestionnaireDAO();
            dao.saveAnswer(questionnaireId, user.getUser_id(), Integer.parseInt(selectedOption));
            request.setAttribute("message", "回答が正常に保存されました。ありがとうございました！");

            // デバッグ用ログ
            System.out.println("Answer submitted: [QUESTIONNAIRE_ID=" + questionnaireId + ", SELECTED_OPTION=" + selectedOption + ", USER_ID=" + user.getUser_id() + "]");

            // アンケート一覧を再取得
            List<Questionnaire> questionnaireList = dao.getAllQuestionnaires();

            // 回答済み情報を追加
            for (Questionnaire questionnaire : questionnaireList) {
                boolean isAnswered = dao.isAnswered(questionnaire.getQuestionnaireId(), user.getUser_id());
                questionnaire.setAnswered(isAnswered);
            }

            request.setAttribute("questionnaireList", questionnaireList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving answer: " + e.getMessage());
            request.setAttribute("errorMessage", "回答の保存中にエラーが発生しました");
        }

        return "QuestionnaireList.action";
    }
}
