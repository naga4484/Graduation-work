package bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Questionnaire implements Serializable {
    private String questionnaireId; // アンケートID
    private String title;           // アンケートタイトル
    private String questionnaire;   // アンケート内容
    private int userId;             // ユーザーID
    private Date questionnaireDate; // アンケート作成日
    private boolean isAnswered;     // 回答済みフラグ
    private List<QuestionnaireOption> options; // 選択肢
    private boolean isCreator;      // 作成者であるかのフラグ

    // ゲッターとセッター
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getQuestionnaireDate() {
        return questionnaireDate;
    }

    public void setQuestionnaireDate(Date questionnaireDate) {
        this.questionnaireDate = questionnaireDate;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    public List<QuestionnaireOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionnaireOption> options) {
        this.options = options;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public void setCreator(boolean isCreator) {
        this.isCreator = isCreator;
    }
}
