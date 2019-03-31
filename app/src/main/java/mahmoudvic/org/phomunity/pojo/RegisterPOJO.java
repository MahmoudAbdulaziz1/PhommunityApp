package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterPOJO {

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
    @SerializedName("photo")
    @Expose
    private String photo;


    public RegisterPOJO(String firstName, String lastName,
                        String email, String password, String mobile, String level, String
                                cameraBrand, String cameraModel, String interestedIn, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.level = level;
        this.cameraBrand = cameraBrand;
        this.cameraModel = cameraModel;
        this.interestedIn = interestedIn;
        this.photo = photo;
    }
//
    public RegisterPOJO(int id, String firstName, String lastName, String email, String password, String mobile,
                        String level, String cameraBrand, String cameraModel, String interestedIn) {
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
       // this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
