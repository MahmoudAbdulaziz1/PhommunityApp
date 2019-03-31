package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class LoginRsponse {

    private float status;
    private RegisterPOJO user;
    private List<PointPojo> points;

    public LoginRsponse(float status, RegisterPOJO user) {
        this.status = status;
        this.user = user;
    }

    public LoginRsponse(float status, RegisterPOJO user, List<PointPojo> points) {
        this.status = status;
        this.user = user;
        this.points = points;
    }

    public LoginRsponse() {
    }

    public float getStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }

    public RegisterPOJO getUser() {
        return user;
    }

    public void setUser(RegisterPOJO user) {
        this.user = user;
    }

    public List<PointPojo> getPoints() {
        return points;
    }

    public void setPoints(List<PointPojo> points) {
        this.points = points;
    }
}
