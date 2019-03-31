package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TipPOJO{


    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("video_url")
    private String videoURL;
    @SerializedName("notifications")
    private int notification;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("images")
    private List<ImagePOJO> imagePOJOList;

    public TipPOJO(){}

//    protected TipPOJO(Parcel in) {
//        id = in.readInt();
//        title = in.readString();
//        description = in.readString();
//        videoURL = in.readString();
//        notification = in.readInt();
//        createdAt = in.readString();
//        updatedAt = in.readString();
//        imagePOJOList = in.createTypedArrayList(ImagePOJO.CREATOR);
//    }

//    public static final Creator<TipPOJO> CREATOR = new Creator<TipPOJO>() {
//        @Override
//        public TipPOJO createFromParcel(Parcel in) {
//            return new TipPOJO(in);
//        }
//
//        @Override
//        public TipPOJO[] newArray(int size) {
//            return new TipPOJO[size];
//        }
//    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
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

    public List<ImagePOJO> getImagePOJOList() {
        return imagePOJOList;
    }

    public void setImagePOJOList(List<ImagePOJO> imagePOJOList) {
        this.imagePOJOList = imagePOJOList;
    }

//    @Override
//    public int describeContents() {
//        return  hashCode();
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        dest.writeInt(id);
//        dest.writeString(title);
//        dest.writeString(description);
//        dest.writeString(videoURL);
//        dest.writeInt(notification);
//        dest.writeString(createdAt);
//        dest.writeString(updatedAt);
//        dest.writeList(imagePOJOList);
//
//
//    }
}
