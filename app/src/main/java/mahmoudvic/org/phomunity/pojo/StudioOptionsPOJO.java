package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudioOptionsPOJO extends OptionsPOJO {

    @SerializedName("image")
    private ImagePOJO image ;
    @SerializedName("schedules")
    private List<SchedulesPOJO> schedulesPOJOS;


    public StudioOptionsPOJO(int id, String title, String about, String price, List<String> contactNumbers, String createdAt, String updatedAt, ImagePOJO image, List<SchedulesPOJO> schedulesPOJOS) {
        super(id, title, about, price, contactNumbers, createdAt, updatedAt);
        this.image = image;
        this.schedulesPOJOS = schedulesPOJOS;
    }

    public ImagePOJO getImage() {
        return image;
    }

    public List<SchedulesPOJO> getSchedulesPOJOS() {
        return schedulesPOJOS;
    }

    public void setImage(ImagePOJO image) {
        this.image = image;
    }

    public void setSchedulesPOJOS(List<SchedulesPOJO> schedulesPOJOS) {
        this.schedulesPOJOS = schedulesPOJOS;
    }
}
