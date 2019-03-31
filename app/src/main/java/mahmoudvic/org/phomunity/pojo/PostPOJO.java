package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostPOJO {


    @SerializedName("id")
    private int id;
    @SerializedName("frontend_user_id")
    private int userId;
    @SerializedName("group_id")
    private int groupId;
    @SerializedName("body")
    private String body;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("frontend_user")
    private UserPOJO userPOJO;
    @SerializedName("images")
    private List<ImagePOJO> imagePOJOS;
    @SerializedName("comments")
    private List<CommentPOJO> commentPOJOS;
    @SerializedName("likes")
    private List<LikePOJO> likePOJOS;

    public PostPOJO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public UserPOJO getUserPOJO() {
        return userPOJO;
    }

    public void setUserPOJO(UserPOJO userPOJO) {
        this.userPOJO = userPOJO;
    }

    public List<ImagePOJO> getImagePOJOS() {
        return imagePOJOS;
    }

    public void setImagePOJOS(List<ImagePOJO> imagePOJOS) {
        this.imagePOJOS = imagePOJOS;
    }

    public List<CommentPOJO> getCommentPOJOS() {
        return commentPOJOS;
    }

    public void setCommentPOJOS(List<CommentPOJO> commentPOJOS) {
        this.commentPOJOS = commentPOJOS;
    }

    public List<LikePOJO> getLikePOJOS() {
        return likePOJOS;
    }

    public void setLikePOJOS(List<LikePOJO> likePOJOS) {
        this.likePOJOS = likePOJOS;
    }


}
