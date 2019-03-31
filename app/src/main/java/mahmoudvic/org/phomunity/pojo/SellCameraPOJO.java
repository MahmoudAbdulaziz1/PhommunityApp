package mahmoudvic.org.phomunity.pojo;

public class SellCameraPOJO {
    private int product_id;
    private String client_name;
    private String client_phone_number;
    private String client_email_address;
    private String notes;

    public SellCameraPOJO(int product_id, String client_name, String client_phone_number, String client_email_address, String notes) {
        this.product_id = product_id;
        this.client_name = client_name;
        this.client_phone_number = client_phone_number;
        this.client_email_address = client_email_address;
        this.notes = notes;
    }

    public SellCameraPOJO() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
}
