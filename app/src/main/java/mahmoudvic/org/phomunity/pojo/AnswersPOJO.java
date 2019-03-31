package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.Expose;

public class AnswersPOJO {

    @Expose
    private boolean right;
    @Expose
    private String body;

    public AnswersPOJO(boolean right, String body) {
        this.right = right;
        this.body = body;
    }

    public AnswersPOJO() {
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
