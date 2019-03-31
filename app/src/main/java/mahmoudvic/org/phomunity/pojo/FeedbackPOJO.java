package mahmoudvic.org.phomunity.pojo;

public class FeedbackPOJO {

    private int user_id;
    private String body;

    public FeedbackPOJO(int user_id, String body) {
        this.user_id = user_id;
        this.body = body;
    }

    public FeedbackPOJO() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
