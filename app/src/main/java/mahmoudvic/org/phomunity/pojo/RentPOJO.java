package mahmoudvic.org.phomunity.pojo;

public class RentPOJO {

    private int product_id;
    private int frontend_user_id;
    private String client_name;
    private String client_phone_number;
    private String client_email_address;
    private String notes;
    private String more;
    private String branch;
    private String duration;
    private String rent_date;

    public RentPOJO(int product_id, int frontend_user_id, String client_name, String client_phone_number,
                    String client_email_address, String notes, String more, String branch, String duration, String rent_date) {
        this.product_id = product_id;
        this.frontend_user_id = frontend_user_id;
        this.client_name = client_name;
        this.client_phone_number = client_phone_number;
        this.client_email_address = client_email_address;
        this.notes = notes;
        this.more = more;
        this.branch = branch;
        this.duration = duration;
        this.rent_date = rent_date;
    }

    public RentPOJO() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getFrontend_user_id() {
        return frontend_user_id;
    }

    public void setFrontend_user_id(int frontend_user_id) {
        this.frontend_user_id = frontend_user_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone_number() {
        return client_phone_number;
    }

    public void setClient_phone_number(String client_phone_number) {
        this.client_phone_number = client_phone_number;
    }

    public String getClient_email_address() {
        return client_email_address;
    }

    public void setClient_email_address(String client_email_address) {
        this.client_email_address = client_email_address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }
}
