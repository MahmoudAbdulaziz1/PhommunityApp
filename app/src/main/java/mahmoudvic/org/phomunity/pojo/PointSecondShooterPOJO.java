package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PointSecondShooterPOJO implements Parcelable{

    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("frontend_user_id")
    private int frontEndUserId;
    @SerializedName("admin_user_id")
    private int adminUserId;
    @SerializedName("notes")
    private String note;
    @SerializedName("action")
    private String action;
    @SerializedName("count")
    private int count;
    @SerializedName("receipt_number")
    private String receiptNumber;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public PointSecondShooterPOJO(){}

    protected PointSecondShooterPOJO(Parcel in) {
        id = in.readInt();
        type = in.readString();
        frontEndUserId = in.readInt();
        adminUserId = in.readInt();
        note = in.readString();
        action = in.readString();
        count = in.readInt();
        receiptNumber = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<PointSecondShooterPOJO> CREATOR = new Creator<PointSecondShooterPOJO>() {
        @Override
        public PointSecondShooterPOJO createFromParcel(Parcel in) {
            return new PointSecondShooterPOJO(in);
        }

        @Override
        public PointSecondShooterPOJO[] newArray(int size) {
            return new PointSecondShooterPOJO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrontEndUserId() {
        return frontEndUserId;
    }

    public void setFrontEndUserId(int frontEndUserId) {
        this.frontEndUserId = frontEndUserId;
    }

    public int getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(int adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
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
        return "PointSecondShooterPOJO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", frontEndUserId=" + frontEndUserId +
                ", adminUserId=" + adminUserId +
                ", note='" + note + '\'' +
                ", action='" + action + '\'' +
                ", count=" + count +
                ", receiptNumber='" + receiptNumber + '\'' +
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
        dest.writeString(type);
        dest.writeInt(frontEndUserId);
        dest.writeInt(adminUserId);
        dest.writeString(note);
        dest.writeString(action);
        dest.writeInt(count);
        dest.writeString(receiptNumber);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);


    }
}
