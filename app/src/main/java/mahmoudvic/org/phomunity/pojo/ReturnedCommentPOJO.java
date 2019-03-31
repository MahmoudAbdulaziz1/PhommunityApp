package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnedCommentPOJO {

    @SerializedName("comment")
    private List<CommentPOJO> commentPOJOS;

    public List<CommentPOJO> getCommentPOJOS() {
        return commentPOJOS;
    }

    public void setCommentPOJOS(List<CommentPOJO> commentPOJOS) {
        this.commentPOJOS = commentPOJOS;
    }
}
