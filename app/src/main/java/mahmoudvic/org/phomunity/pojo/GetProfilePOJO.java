package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfilePOJO {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("camera_brand")
    @Expose
    private String cameraBrand;
    @SerializedName("camera_model")
    @Expose
    private String cameraModel;
    @SerializedName("interested_in")
    @Expose
    private String interestedIn;
    @SerializedName("current_points")
    @Expose
    private int current_points;

    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    private List<PointPojo> points;
    @SerializedName("photo")
    @Expose
    private ImagePOJO photo;

    public GetProfilePOJO(int id, String firstName, String lastName, String email, String password, String mobile, String level, String cameraBrand, String cameraModel, String interestedIn,
                          int current_points, int active, String created_at, String updated_at, List<PointPojo> points, ImagePOJO photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.level = level;
        this.cameraBrand = cameraBrand;
        this.cameraModel = cameraModel;
        this.interestedIn = interestedIn;
        this.current_points = current_points;
        this.active = active;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.points = points;
        this.photo = photo;
    }

    public GetProfilePOJO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getCurrent_points() {
        return current_points;
    }

    public void setCurrent_points(int current_points) {
        this.current_points = current_points;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public List<PointPojo> getPoints() {
        return points;
    }

    public void setPoints(List<PointPojo> points) {
        this.points = points;
    }

    public ImagePOJO getPhoto() {
        return photo;
    }

    public void setPhoto(ImagePOJO photo) {
        this.photo = photo;
    }
}
