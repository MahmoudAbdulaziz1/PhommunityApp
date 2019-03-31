package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupPOJO{

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("notes")
    private String notes;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("posts")
    private List<PostPOJO> postPOJOS;
    @SerializedName("users")
    private List<UserPOJO> userPOJOS;


    public GroupPOJO(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public List<UserPOJO> getUserPOJOS() {
        return userPOJOS;
    }

    public void setUserPOJOS(List<UserPOJO> userPOJOS) {
        this.userPOJOS = userPOJOS;
    }

    public List<PostPOJO> getPostPOJOS() {
        return postPOJOS;
    }

    public void setPostPOJOS(List<PostPOJO> postPOJOS) {
        this.postPOJOS = postPOJOS;
    }


    @Override
    public String toString() {
        return "GroupPOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", userPOJOS=" + userPOJOS +
                ", postPOJOS=" + postPOJOS +
                '}';
    }


}
