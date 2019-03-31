package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

public class ReturnedLikePOJO {

    @SerializedName("count")
    private int count;
    @SerializedName("action")
    private String action;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
