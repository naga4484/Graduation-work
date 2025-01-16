package bean;

import java.io.Serializable;

public class QuestionnaireOption implements Serializable {
    private String questionnaireId; // アンケートID
    private int optionNumber;       // 選択肢番号
    private String optionContent;   // 選択肢内容
    private int count;              // 回答数

    // ゲッターとセッター
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
