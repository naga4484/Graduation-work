package questionnaire;

import java.util.List;

import bean.Questionnaire;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class QuestionnaireListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // DAOを使用してアンケート一覧を取得
            QuestionnaireDAO dao = new QuestionnaireDAO();
            List<Questionnaire> questionnaireList = dao.getAllQuestionnaires();

            // デバッグ: データをコンソールに出力
            if (questionnaireList != null && !questionnaireList.isEmpty()) {
                System.out.println("アンケートデータ取得成功:");
                for (Questionnaire q : questionnaireList) {
                    System.out.println("ID: " + q.getQuestionnaireId() + ", タイトル: " + q.getTitle());
                }
            } else {
                System.out.println("アンケートデータが存在しません。");
                request.setAttribute("error", "現在はアンケートが存在しません");
            }

            // 取得したアンケートをリクエストスコープに保存
            request.setAttribute("questionnaireList", questionnaireList);

        } catch (Exception e) {
            e.printStackTrace();
            // エラー情報をリクエストスコープに保存
            request.setAttribute("error", "現在はアンケートが存在しません");
            System.out.println("エラー: " + e.getMessage());
        }

        // JSP にフォワード
        return "../questionnaire/questionnaire_list.jsp";
    }
}
