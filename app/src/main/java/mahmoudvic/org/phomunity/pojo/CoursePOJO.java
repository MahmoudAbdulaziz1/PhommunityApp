package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoursePOJO {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("from")
    private String startDate;
    @SerializedName("to")
    private String endDate;
    @SerializedName("instructor_name")
    private String instructorName;
    @SerializedName("instructor_bio")
    private String instructorBio;
    @SerializedName("days")
    private List<String> days;
    @SerializedName("faq")
    private List<FaqPOJO> faqList;
    @SerializedName("video_url")
    private String videoUrl;
    @SerializedName("phone_numbers")
    private List<String> phonesNumber;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("images")
    private List<ImagePOJO> images;
    @SerializedName("instructor_image")
    private ImagePOJO instructorImage;

    public CoursePOJO() {
    }

    public CoursePOJO(int id, String name, String description, String startDate, String endDate, String instructorName, String instructorBio, List<String> days, List<FaqPOJO> faqList, String videoUrl, List<String> phonesNumber, String createdAt, String updatedAt, List<ImagePOJO> images, ImagePOJO instructorImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructorName = instructorName;
        this.instructorBio = instructorBio;
        this.days = days;
        this.faqList = faqList;
        this.videoUrl = videoUrl;
        this.phonesNumber = phonesNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.images = images;
        this.instructorImage = instructorImage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getInstructorBio() {
        return instructorBio;
    }

    public List<String> getDays() {
        return days;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public List<String> getPhonesNumber() {
        return phonesNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public List<FaqPOJO> getFaqList() {
        return faqList;
    }

    public List<ImagePOJO> getImages() {
        return images;
    }

    public ImagePOJO getInstructorImage() {
        return instructorImage;
    }
}
