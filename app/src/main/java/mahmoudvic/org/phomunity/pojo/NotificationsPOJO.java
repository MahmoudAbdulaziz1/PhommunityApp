package mahmoudvic.org.phomunity.pojo;

public class NotificationsPOJO {


    private int id;
    private String title;
    private String message;
    private String link;
    private String created_at;
    private String updated_at;
    private Pivot pivot;

    public NotificationsPOJO(int id, String title, String message, String link, String created_at, String updated_at, Pivot pivot) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.link = link;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pivot = pivot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
}
