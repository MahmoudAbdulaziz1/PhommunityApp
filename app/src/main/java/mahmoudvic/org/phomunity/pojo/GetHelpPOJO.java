package mahmoudvic.org.phomunity.pojo;

public class GetHelpPOJO {

    private int id;
    private int frontend_user_id;
    private int admin_user_id;
    private String body;
    private int new_col;
    private String created_at;
    private String updated_at;

    public GetHelpPOJO() {
    }

    public GetHelpPOJO(int id, int frontend_user_id, int admin_user_id, String body, int new_col, String created_at, String updated_at) {
        this.id = id;
        this.frontend_user_id = frontend_user_id;
        this.admin_user_id = admin_user_id;
        this.body = body;
        this.new_col = new_col;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrontend_user_id() {
        return frontend_user_id;
    }

    public void setFrontend_user_id(int frontend_user_id) {
        this.frontend_user_id = frontend_user_id;
    }

    public int getAdmin_user_id() {
        return admin_user_id;
    }

    public void setAdmin_user_id(int admin_user_id) {
        this.admin_user_id = admin_user_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getNew_col() {
        return new_col;
    }

    public void setNew_col(int new_col) {
        this.new_col = new_col;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
