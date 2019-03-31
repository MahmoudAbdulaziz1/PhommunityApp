package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShootersPOJO {

    @SerializedName("shooters")
    private List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOS;

    @SerializedName("topShooters")
    private List<AssistantSecondShooterPOJO> topSecondShooterPOJOS;

    public List<AssistantSecondShooterPOJO> getAssistantSecondShooterPOJOS() {
        return assistantSecondShooterPOJOS;
    }

    public void setAssistantSecondShooterPOJOS(List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOS) {
        this.assistantSecondShooterPOJOS = assistantSecondShooterPOJOS;
    }

    public List<AssistantSecondShooterPOJO> getTopSecondShooterPOJOS() {
        return topSecondShooterPOJOS;
    }

    public void setTopSecondShooterPOJOS(List<AssistantSecondShooterPOJO> topSecondShooterPOJOS) {
        this.topSecondShooterPOJOS = topSecondShooterPOJOS;
    }
}
