package mahmoudvic.org.phomunity.pojo;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PhotographyPOJO {
    @Expose
    private String category;
    @Expose
    private List<QuestionsPOJO> questions;

    public PhotographyPOJO(String category, List<QuestionsPOJO> questions) {
        this.category = category;
        this.questions = questions;
    }

    public PhotographyPOJO() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<QuestionsPOJO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsPOJO> questions) {
        this.questions = questions;
    }
}
