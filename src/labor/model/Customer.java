package labor.model;

public class Customer extends User {
    private String company;



    public Customer (String name, String surname, String user_id, int id, String user_password, String company) {
        super(name,surname,user_id,user_password,id);
        this.company = company;
        

    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }



}
