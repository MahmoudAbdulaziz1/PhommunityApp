package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserPOJO  {


    @SerializedName("id")
    private int id;
    @SerializedName("first_name")
    private String firstname;
    @SerializedName("last_name")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("level")
    private String level;
    @SerializedName("camera_brand")
    private String cameraBrand;
    @SerializedName("camera_model")
    private String cameraModel;
    @SerializedName("interested_in")
    private String interestedIn;
    @SerializedName("current_points")
    private int currentPoints;
    @SerializedName("active")
    private int active;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("pivot")
    private PivotPOJO pivotPOJO;
    @SerializedName("photo")
    private ImagePOJO shooterPhotoPOJO;
    @SerializedName("points")
    private List<PointSecondShooterPOJO> pointSecondShooterPOJOS;

    public UserPOJO(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCameraBrand() {
        return cameraBrand;
    }

    public void setCameraBrand(String cameraBrand) {
        this.cameraBrand = cameraBrand;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(String interestedIn) {
        this.interestedIn = interestedIn;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public ImagePOJO getShooterPhotoPOJO() {
        return shooterPhotoPOJO;
    }

    public void setShooterPhotoPOJO(ImagePOJO shooterPhotoPOJO) {
        this.shooterPhotoPOJO = shooterPhotoPOJO;
    }

    public List<PointSecondShooterPOJO> getPointSecondShooterPOJOS() {
        return pointSecondShooterPOJOS;
    }

    public void setPointSecondShooterPOJOS(List<PointSecondShooterPOJO> pointSecondShooterPOJOS) {
        this.pointSecondShooterPOJOS = pointSecondShooterPOJOS;
    }

    public PivotPOJO getPivotPOJO() {
        return pivotPOJO;
    }

    public void setPivotPOJO(PivotPOJO pivotPOJO) {
        this.pivotPOJO = pivotPOJO;
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", level='" + level + '\'' +
                ", cameraBrand='" + cameraBrand + '\'' +
                ", cameraModel='" + cameraModel + '\'' +
                ", interestedIn='" + interestedIn + '\'' +
                ", currentPoints=" + currentPoints +
                ", active=" + active +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", shooterPhotoPOJO=" + shooterPhotoPOJO +
                ", pointSecondShooterPOJOS=" + pointSecondShooterPOJOS +
                '}';
    }


}
