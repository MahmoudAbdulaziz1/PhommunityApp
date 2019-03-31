package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PivotPOJO implements Parcelable{


    @SerializedName("group_id")
    private int id;
    @SerializedName("frontend_user_id")
    private int userId;
    @SerializedName("approved")
    private int approved;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public  PivotPOJO(){}

    protected PivotPOJO(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        approved = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<PivotPOJO> CREATOR = new Creator<PivotPOJO>() {
        @Override
        public PivotPOJO createFromParcel(Parcel in) {
            return new PivotPOJO(in);
        }

        @Override
        public PivotPOJO[] newArray(int size) {
            return new PivotPOJO[size];
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
        dest.writeInt(approved);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);

    }
}
