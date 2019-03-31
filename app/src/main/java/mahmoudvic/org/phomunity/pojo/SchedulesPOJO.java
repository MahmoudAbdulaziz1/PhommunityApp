package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

public class SchedulesPOJO {
    @SerializedName("id")
    private int id;
    @SerializedName("option_id")
    private int optionId;
    @SerializedName("date")
    private String date;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public SchedulesPOJO(int id, int optionId, String date, String from, String to, String createdAt, String updatedAt) {
        this.id = id;
        this.optionId = optionId;
        this.date = date;
        this.from = from;
        this.to = to;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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