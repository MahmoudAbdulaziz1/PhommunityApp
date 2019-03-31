package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LikePOJO implements Parcelable{


    @SerializedName("id")
    private int id;
    @SerializedName("frontend_user_id")
    private int userId;
    @SerializedName("post_id")
    private int postId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public LikePOJO(){}

    protected LikePOJO(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        postId = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<LikePOJO> CREATOR = new Creator<LikePOJO>() {
        @Override
        public LikePOJO createFromParcel(Parcel in) {
            return new LikePOJO(in);
        }

        @Override
        public LikePOJO[] newArray(int size) {
            return new LikePOJO[size];
        }
    };

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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {



        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeInt(postId);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);

    }
}
