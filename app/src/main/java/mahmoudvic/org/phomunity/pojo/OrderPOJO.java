package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

public class OrderPOJO {

    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("frontend_user_id")
    private int frontEndUser;
    @SerializedName("orderable_id")
    private int orderableID;
    @SerializedName("orderable_type")
    private String orderableType;
    @SerializedName("meta")
    private String meta;
    @SerializedName("notes")
    private String note;
    @SerializedName("new")
    private int newOrder;
    @SerializedName("status")
    private String status;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

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

    public int getFrontEndUser() {
        return frontEndUser;
    }

    public void setFrontEndUser(int frontEndUser) {
        this.frontEndUser = frontEndUser;
    }

    public int getOrderableID() {
        return orderableID;
    }

    public void setOrderableID(int orderableID) {
        this.orderableID = orderableID;
    }

    public String getOrderableType() {
        return orderableType;
    }

    public void setOrderableType(String orderableType) {
        this.orderableType = orderableType;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(int newOrder) {
        this.newOrder = newOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
