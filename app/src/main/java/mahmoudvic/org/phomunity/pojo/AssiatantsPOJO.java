package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssiatantsPOJO {

    @SerializedName("assistants")
    private List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOS;

    @SerializedName("topAssistants")
    private List<AssistantSecondShooterPOJO> topAssistantSecondShooterPOJOS;

    public List<AssistantSecondShooterPOJO> getAssistantSecondShooterPOJOS() {
        return assistantSecondShooterPOJOS;
    }

    public void setAssistantSecondShooterPOJOS(List<AssistantSecondShooterPOJO> assistantSecondShooterPOJOS) {
        this.assistantSecondShooterPOJOS = assistantSecondShooterPOJOS;
    }

    public List<AssistantSecondShooterPOJO> getTopAssistantSecondShooterPOJOS() {
        return topAssistantSecondShooterPOJOS;
    }

    public void setTopAssistantSecondShooterPOJOS(List<AssistantSecondShooterPOJO> topAssistantSecondShooterPOJOS) {
        this.topAssistantSecondShooterPOJOS = topAssistantSecondShooterPOJOS;
    }
}
