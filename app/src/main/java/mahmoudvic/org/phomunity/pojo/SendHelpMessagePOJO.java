package mahmoudvic.org.phomunity.pojo;

public class SendHelpMessagePOJO {

    private int user_id;
    private String body;

    public SendHelpMessagePOJO() {
    }

    public SendHelpMessagePOJO(int user_id, String body) {
        this.user_id = user_id;
        this.body = body;
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
