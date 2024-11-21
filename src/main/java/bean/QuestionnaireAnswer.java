package bean;

import java.io.Serializable;

public class QuestionnaireAnswer implements Serializable {
    private String questionnaireId;
    private String questionnaireContent;
    private int userId;

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireContent() {
        return questionnaireContent;
    }

    public void setQuestionnaireContent(String questionnaireContent) {
        this.questionnaireContent = questionnaireContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
