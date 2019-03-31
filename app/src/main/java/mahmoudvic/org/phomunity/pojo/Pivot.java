package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.SerializedName;

public class Pivot {

         private int frontend_user_id;
         private int notification_id;
         @SerializedName("new")
         private int news;

    public Pivot(int frontend_user_id, int notification_id, int news) {
        this.frontend_user_id = frontend_user_id;
        this.notification_id = notification_id;
        this.news = news;
    }

    public Pivot() {
    }

    public int getFrontend_user_id() {
        return frontend_user_id;
    }

    public void setFrontend_user_id(int frontend_user_id) {
        this.frontend_user_id = frontend_user_id;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(int notification_id) {
        this.notification_id = notification_id;
    }

    public int getNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }
}
