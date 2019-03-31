package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mahmoudvic.org.phomunity.LabsOptionsPOJO;

public class LabsPOJO {

    @SerializedName("options")
    private List<LabsOptionsPOJO> options;
    @SerializedName("slider")
    private List<ImagePOJO> slides;

    public LabsPOJO(List<LabsOptionsPOJO> options, List<ImagePOJO> slides) {
        this.options = options;
        this.slides = slides;
    }

    public List<LabsOptionsPOJO> getOptions() {
        return options;
    }

    public List<ImagePOJO> getSlides() {
        return slides;
    }
}
