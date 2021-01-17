package labor.model;

public class Sale {
    private int sale_id;
    private int customer_id;
    private int product_id;
    private String sale_date;
    private int sale_amount;
    private String customer_company;


    public Sale(int sale_id, int customer_id, int product_id, String sale_date, int sale_amount, String customer_company) {
        this.sale_id = sale_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.sale_date = sale_date;
        this.sale_amount = sale_amount;
        this.customer_company = customer_company;
    }

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }

    public int getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(int sale_amount) {
        this.sale_amount = sale_amount;
    }

    public String getCustomer_company() {
        return customer_company;
    }

    public void setCustomer_company(String customer_company) {
        this.customer_company = customer_company;
    }
}
