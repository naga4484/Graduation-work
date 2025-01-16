package questionnaire;

import java.util.List;

import bean.Questionnaire;
import bean.QuestionnaireOption;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class QuestionnaireParticipateDetailAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String questionnaireId = request.getParameter("questionnaireId");

        // questionnaireIdが存在しない場合のエラーハンドリング
        if (questionnaireId == null || questionnaireId.trim().isEmpty()) {
            System.out.println("Error: questionnaireId is null or empty.");
            request.setAttribute("errorMessage", "アンケートIDが指定されていません");
            return "../questionnaire/questionnaire_list.jsp";
        }

        try {
            QuestionnaireDAO dao = new QuestionnaireDAO();
            
            // アンケートタイトルと内容を取得
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setQuestionnaireId(questionnaireId);
            questionnaire.setTitle(dao.getQuestionnaireTitle(questionnaireId));
            questionnaire.setQuestionnaire(dao.getQuestionnaireContent(questionnaireId));

            // アンケートの選択肢を取得
            List<QuestionnaireOption> options = dao.getQuestionnaireOptions(questionnaireId);
            questionnaire.setOptions(options);

            // デバッグログ
            System.out.println("Questionnaire ID: " + questionnaire.getQuestionnaireId());
            System.out.println("Questionnaire Title: " + questionnaire.getTitle());
            System.out.println("Questionnaire Content: " + questionnaire.getQuestionnaire());
            for (QuestionnaireOption option : options) {
                System.out.println("Option " + option.getOptionNumber() + ": " + option.getOptionContent());
            }

            // リクエストスコープにデータを設定
            request.setAttribute("questionnaire", questionnaire);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching questionnaire details: " + e.getMessage());
            request.setAttribute("errorMessage", "アンケート詳細の取得中にエラーが発生しました");
        }

        return "../questionnaire/questionnaire_answer.jsp";
    }
}
