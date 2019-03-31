package mahmoudvic.org.phomunity.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventPOJO{

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("about")
    private String about;
    @SerializedName("date_from")
    private String dateFrom;
    @SerializedName("date_to")
    private String dateTo;
    @SerializedName("time_from")
    private String timeFrom;
    @SerializedName("time_to")
    private String timeTo;
    @SerializedName("location")
    private String location;
    @SerializedName("address")
    private String address;
    @SerializedName("video_url")
    private String videoUrl;
    @SerializedName("phone_numbers")
    private List<String> phonesNumber;
    @SerializedName("document")
    private String document;
    @SerializedName("instructor_name")
    private String instructorName;
    @SerializedName("instructor_bio")
    private String instructorBio;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("instructor_image")
    private SamplesSecondShooterPOJO instructorImage;
    @SerializedName("images")
    private List<ImagePOJO> samplesSecondShooterPOJOS;

    public  EventPOJO(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhonesNumber() {
        return phonesNumber;
    }

    public void setPhonesNumber(List<String> phonesNumber) {
        this.phonesNumber = phonesNumber;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorBio() {
        return instructorBio;
    }

    public void setInstructorBio(String instructorBio) {
        this.instructorBio = instructorBio;
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

    public List<ImagePOJO> getSamplesSecondShooterPOJOS() {
        return samplesSecondShooterPOJOS;
    }

    public void setSamplesSecondShooterPOJOS(List<ImagePOJO> samplesSecondShooterPOJOS) {
        this.samplesSecondShooterPOJOS = samplesSecondShooterPOJOS;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public SamplesSecondShooterPOJO getInstructorImage() {
        return instructorImage;
    }

    public void setInstructorImage(SamplesSecondShooterPOJO instructorImage) {
        this.instructorImage = instructorImage;
    }

    @Override
    public String toString() {
        return "EventPOJO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", phonesNumber=" + phonesNumber +
                ", document='" + document + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorBio='" + instructorBio + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", samplesSecondShooterPOJOS=" + samplesSecondShooterPOJOS +
                '}';
    }


}
