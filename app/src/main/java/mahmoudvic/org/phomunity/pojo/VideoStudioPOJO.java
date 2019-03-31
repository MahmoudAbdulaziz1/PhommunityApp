package mahmoudvic.org.phomunity.pojo;


import com.google.gson.annotations.SerializedName;

public class VideoStudioPOJO {
    @SerializedName("id")
    private int id;
    @SerializedName("field")
    private String field;
    @SerializedName("value")
    private String value;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;


    public VideoStudioPOJO(int id, String field, String value, String createdAt, String updatedAt) {
        this.id = id;
        this.field = field;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public int getId() {
        return id;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

}