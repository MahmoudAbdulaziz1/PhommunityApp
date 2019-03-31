package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

public class FaqPOJO {
    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    public FaqPOJO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
