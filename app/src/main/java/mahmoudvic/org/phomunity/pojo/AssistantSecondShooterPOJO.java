package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssistantSecondShooterPOJO {

    @SerializedName("id")
    private int id;
    @SerializedName("frontend_user_id")
    private int frontEndUserId;
    @SerializedName("about")
    private String about;
    @SerializedName("area")
    private String area;
    @SerializedName("rating")
    private int rating;
    @SerializedName("featured")
    private int featured;
    @SerializedName("approved")
    private int approved;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updated_at;
    @SerializedName("frontend_user")
    private UserPOJO userPOJO;
    @SerializedName("samples")
    private List<SamplesSecondShooterPOJO> samplesSecondShooterPOJOS;
    @SerializedName("orders")
    private List<OrderPOJO> orderPOJOS;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrontEndUserId() {
        return frontEndUserId;
    }

    public void setFrontEndUserId(int frontEndUserId) {
        this.frontEndUserId = frontEndUserId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public UserPOJO getUserPOJO() {
        return userPOJO;
    }

    public void setUserPOJO(UserPOJO userPOJO) {
        this.userPOJO = userPOJO;
    }

    public List<SamplesSecondShooterPOJO> getSamplesSecondShooterPOJOS() {
        return samplesSecondShooterPOJOS;
    }

    public void setSamplesSecondShooterPOJOS(List<SamplesSecondShooterPOJO> samplesSecondShooterPOJOS) {
        this.samplesSecondShooterPOJOS = samplesSecondShooterPOJOS;
    }

    public List<OrderPOJO> getOrderPOJOS() {
        return orderPOJOS;
    }

    public void setOrderPOJOS(List<OrderPOJO> orderPOJOS) {
        this.orderPOJOS = orderPOJOS;
    }
}
