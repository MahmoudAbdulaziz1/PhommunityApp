package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnedPostPOJO {
    @SerializedName("post")
    private List<PostPOJO> postPOJOS;

    public List<PostPOJO> getPostPOJOS() {
        return postPOJOS;
    }

    public void setPostPOJOS(List<PostPOJO> postPOJOS) {
        this.postPOJOS = postPOJOS;
    }
}