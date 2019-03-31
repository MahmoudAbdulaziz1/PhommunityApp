package mahmoudvic.org.phomunity.pojo;

public class EvaluationPostPOJO {

    private String mark;
    private String feedback;

    public EvaluationPostPOJO(String mark, String feedback) {
        this.mark = mark;
        this.feedback = feedback;
    }

    public EvaluationPostPOJO() {

    }

    public String getMarks() {
        return mark;
    }

    public void setMarks(String mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
