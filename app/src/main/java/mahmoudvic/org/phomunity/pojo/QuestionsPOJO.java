package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

public class QuestionsPOJO {
    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private int mark;
    @Expose
    private String category;
    @Expose
    private String created_at;
    @Expose
    private String updated_at;
    @Expose
    private EvaluationImagePOJO image;
    @Expose
    private List<AnswersPOJO> answers;

    public QuestionsPOJO(int id, String title, int mark, String category, String created_at, String updated_at, EvaluationImagePOJO image, List<AnswersPOJO> answers) {
        this.id = id;
        this.title = title;
        this.mark = mark;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.image = image;
        this.answers = answers;
    }

    public QuestionsPOJO() {
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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public EvaluationImagePOJO getImage() {
        return image;
    }

    public void setImage(EvaluationImagePOJO image) {
        this.image = image;
    }

    public List<AnswersPOJO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersPOJO> answers) {
        this.answers = answers;
    }
}
