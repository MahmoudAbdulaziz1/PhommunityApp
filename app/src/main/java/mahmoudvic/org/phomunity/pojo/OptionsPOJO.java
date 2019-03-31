package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OptionsPOJO {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("about")
    private String about;
    @SerializedName("price")
    private String price;
    @SerializedName("contact_numbers")
    private List<String> contactNumbers;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public OptionsPOJO(int id, String title, String about, String price, List<String> contactNumbers, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.about = about;
        this.price = price;
        this.contactNumbers = contactNumbers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}