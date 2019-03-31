package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SamplesSecondShooterPOJO implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("imageable_id")
    private int imageableId;
    @SerializedName("imageable_type")
    private String imageableType;
    @SerializedName("original_filename")
    private String originalFilename;
    @SerializedName("image_filename")
    private String imageFileName;
    @SerializedName("thumb_filename")
    private String thumpFilename;
    @SerializedName("tag")
    private String tag;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

public SamplesSecondShooterPOJO(){}
    protected SamplesSecondShooterPOJO(Parcel in) {
        id = in.readInt();
        imageableId = in.readInt();
        imageableType = in.readString();
        originalFilename = in.readString();
        imageFileName = in.readString();
        thumpFilename = in.readString();
        tag = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<SamplesSecondShooterPOJO> CREATOR = new Creator<SamplesSecondShooterPOJO>() {
        @Override
        public SamplesSecondShooterPOJO createFromParcel(Parcel in) {
            return new SamplesSecondShooterPOJO(in);
        }

        @Override
        public SamplesSecondShooterPOJO[] newArray(int size) {
            return new SamplesSecondShooterPOJO[size];
        }
    };

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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getThumpFilename() {
        return thumpFilename;
    }

    public void setThumpFilename(String thumpFilename) {
        this.thumpFilename = thumpFilename;
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
        return "SamplesSecondShooterPOJO{" +
                "id=" + id +
                ", imageableId=" + imageableId +
                ", imageableType='" + imageableType + '\'' +
                ", originalFilename='" + originalFilename + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                ", thumpFilename='" + thumpFilename + '\'' +
                ", tag='" + tag + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeInt(imageableId);
        dest.writeString(imageableType);
        dest.writeString(originalFilename);
        dest.writeString(imageFileName);
        dest.writeString(thumpFilename);
        dest.writeString(tag);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);



    }
}


