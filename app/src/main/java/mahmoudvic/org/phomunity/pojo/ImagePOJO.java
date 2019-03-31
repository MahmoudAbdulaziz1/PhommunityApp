package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImagePOJO{

    @SerializedName("id")
    private int id;
    @SerializedName("imageable_id")
    private int imageableId;
    @SerializedName("imageable_type")
    private String imageableType;
    @SerializedName("original_filename")
    private String originalFilename;
    @SerializedName("image_filename")
    private String imageFilename;
    @SerializedName("thumb_filename")
    private String thumbFilename;
    @SerializedName("tag")
    private String tag;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public ImagePOJO(){}

    protected ImagePOJO(Parcel in) {
        id = in.readInt();
        imageableId = in.readInt();
        imageableType = in.readString();
        originalFilename = in.readString();
        imageFilename = in.readString();
        thumbFilename = in.readString();
        tag = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageableId() {
        return imageableId;
    }

    public void setImageableId(int imageableId) {
        this.imageableId = imageableId;
    }

    public String getImageableType() {
        return imageableType;
    }

    public void setImageableType(String imageableType) {
        this.imageableType = imageableType;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getThumbFilename() {
        return thumbFilename;
    }

    public void setThumbFilename(String thumbFilename) {
        this.thumbFilename = thumbFilename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
    public String toString() {
        return "ImagePOJO{" +
                "id=" + id +
                ", imageableId=" + imageableId +
                ", imageableType='" + imageableType + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", imageFilename='" + imageFilename + '\'' +
                ", thumbFilename='" + thumbFilename + '\'' +
                ", tag='" + tag + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }


}
