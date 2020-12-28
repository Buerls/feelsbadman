package labor.model;

public class Customer extends User {
    private String adress;
    private String payment_info;


    public Customer (String name, String surname, String user_id,int id,String user_password,String adress, String payment_info) {
        super(name,surname,user_id,user_password,id);
        this.adress = adress;
        this.payment_info = payment_info;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(String payment_info) {
        this.payment_info = payment_info;
    }


}
