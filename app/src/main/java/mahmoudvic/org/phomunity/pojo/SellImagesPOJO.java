package mahmoudvic.org.phomunity.pojo;

public class SellImagesPOJO {

    private int id;
    private int imageable_id;
    private String imageable_type;
    private String original_filename;
    private String image_filename;
    private String thumb_filename;
    private String tag;
    private String created_at;
    private String updated_at;

    public SellImagesPOJO(int id, int imageable_id, String imageable_type, String original_filename,
                          String image_filename, String thumb_filename, String tag, String created_at, String updated_at) {
        this.id = id;
        this.imageable_id = imageable_id;
        this.imageable_type = imageable_type;
        this.original_filename = original_filename;
        this.image_filename = image_filename;
        this.thumb_filename = thumb_filename;
        this.tag = tag;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public SellImagesPOJO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageable_id() {
        return imageable_id;
    }

    public void setImageable_id(int imageable_id) {
        this.imageable_id = imageable_id;
    }

    public String getImageable_type() {
        return imageable_type;
    }

    public void setImageable_type(String imageable_type) {
        this.imageable_type = imageable_type;
    }

    public String getOriginal_filename() {
        return original_filename;
    }

    public void setOriginal_filename(String original_filename) {
        this.original_filename = original_filename;
    }

    public String getImage_filename() {
        return image_filename;
    }

    public void setImage_filename(String image_filename) {
        this.image_filename = image_filename;
    }

    public String getThumb_filename() {
        return thumb_filename;
    }

    public void setThumb_filename(String thumb_filename) {
        this.thumb_filename = thumb_filename;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
}
