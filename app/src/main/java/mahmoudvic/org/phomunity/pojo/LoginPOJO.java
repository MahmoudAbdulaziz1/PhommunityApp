package mahmoudvic.org.phomunity.pojo;

public class LoginPOJO {

    private int responseCode;;
    private String email;
    private String password;

    public LoginPOJO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginPOJO(int login_id, String email, String password) {
        this.responseCode = login_id;
        this.email = email;
        this.password = password;
    }

    public int getLogin_id() {
        return responseCode;
    }

    public void setLogin_id(int login_id) {
        this.responseCode = login_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
