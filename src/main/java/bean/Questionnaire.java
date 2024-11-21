package bean;

import java.io.Serializable;
import java.sql.Date;

public class Questionnaire implements Serializable {
    private String questionnaireId;
    private String title;
    private String questionnaire;
    private int userId;
    private Date questionnaireDate; // 新しく追加

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

    public Date getQuestionnaireDate() { // getter
        return questionnaireDate;
    }

    public void setQuestionnaireDate(Date questionnaireDate) { // setter
        this.questionnaireDate = questionnaireDate;
    }
}
