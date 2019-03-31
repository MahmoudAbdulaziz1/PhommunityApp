package mahmoudvic.org.phomunity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mahmoudvic.org.phomunity.pojo.ImagePOJO;

public class LabsOptionsPOJO {
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
    @SerializedName("available")
    private int available;
    @SerializedName("images")
    private List<ImagePOJO> images;

    public LabsOptionsPOJO(int id, String title, String about, String price, List<String> contactNumbers, String createdAt, String updatedAt, int available, List<ImagePOJO> images) {
        this.id = id;
        this.title = title;
        this.about = about;
        this.price = price;
        this.contactNumbers = contactNumbers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.available = available;
        this.images = images;
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

    public int getAvailable() {
        return available;
    }

    public List<ImagePOJO> getImages() {
        return images;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setImages(List<ImagePOJO> images) {
        this.images = images;
    }
}
