package mahmoudvic.org.phomunity.pojo;

public class PointPojo {

    private int id;
    private String type;
    private int frontend_user_id;
    private int admin_user_id;
    private String notes;
    private String action;
    private int count;
    private String receipt_number;
    private String created_at;
    private String updated_at;

    public PointPojo(int id, String type, int frontend_user_id, int admin_user_id,
                     String notes, String action, int count, String receipt_number, String created_at, String updated_at) {
        this.id = id;
        this.type = type;
        this.frontend_user_id = frontend_user_id;
        this.admin_user_id = admin_user_id;
        this.notes = notes;
        this.action = action;
        this.count = count;
        this.receipt_number = receipt_number;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public PointPojo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(String receipt_number) {
        this.receipt_number = receipt_number;
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
