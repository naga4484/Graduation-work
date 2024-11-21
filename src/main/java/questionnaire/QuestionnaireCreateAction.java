package questionnaire;

import java.util.ArrayList;
import java.util.List;

import bean.Questionnaire;
import bean.QuestionnaireAnswer;
import bean.User_id;
import dao.QuestionnaireDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class QuestionnaireCreateAction extends Action {
    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // デバッグログ：クラスが実行されているか確認
        System.out.println("QuestionnaireCreateAction executed.");

        HttpSession session = request.getSession();

        // ユーザー情報をセッションから取得
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            // ユーザーがログインしていない場合、エラーを返す
            request.setAttribute("error", "ログインが必要です");
            return "../account/login.jsp";
        }

        // フォームデータを取得
        String title = request.getParameter("title");
        String question = request.getParameter("question");
        List<String> answers = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            String answer = request.getParameter("answer" + i);
            if (answer != null && !answer.trim().isEmpty()) {
                answers.add(answer);
            }
        }

        // 入力バリデーション
        if (title == null || title.trim().isEmpty() ||
            question == null || question.trim().isEmpty() ||
            answers.size() < 2) {
            // 必須項目が未入力の場合、エラーメッセージを設定
            request.setAttribute("error", "全ての項目を正しく入力してください。");
            return "questionnaire_create.jsp";
        }
        
//        String userIdString = (String) session.getAttribute("userId");
        User_id user_id = (User_id)session.getAttribute("user");
//        int userIdInt = Integer.parseInt(userIdString);
        System.out.println("USER_ID to insert: " + user_id.getUser_id());


        // アンケートデータを準備
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setQuestionnaireId(generateQuestionnaireId()); // ランダムIDを生成
        questionnaire.setTitle(title);
        questionnaire.setQuestionnaire(question);
        questionnaire.setUserId(user_id.getUser_id()); // セッションからユーザーIDを取得

        // 回答データを準備
        List<QuestionnaireAnswer> questionnaireAnswers = new ArrayList<>();
        for (String answer : answers) {
            QuestionnaireAnswer answerBean = new QuestionnaireAnswer();
            answerBean.setQuestionnaireId(questionnaire.getQuestionnaireId());
            answerBean.setQuestionnaireContent(answer);
            answerBean.setUserId(questionnaire.getUserId());
            questionnaireAnswers.add(answerBean);
        }

        // データベースに保存
        QuestionnaireDAO dao = new QuestionnaireDAO();
        try {
            dao.saveQuestionnaire(questionnaire, questionnaireAnswers);
            request.setAttribute("message", "アンケートを作成しました。");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "アンケートの保存中にエラーが発生しました。");
            return "questionnaire_create.jsp";
        }

        // 成功時の遷移先
        return "questionnaire_create.jsp";
    }

    private String generateQuestionnaireId() {
        int randomId = (int) (Math.random() * 9000) + 1000; // 4桁のランダムな数値を生成
        return "Q" + randomId; // 最大5文字のID
    }


}
