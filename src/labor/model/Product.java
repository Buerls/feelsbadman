package labor.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Product {
    protected String product_name;
    protected int product_id;
    protected String product_price;
    protected String product_photo;
    protected String product_type;
    protected int product_amount;
    protected ImageView product_image;
    public static int product_MAX_id;


    public Product(String product_name, int product_id, String product_price, String product_photo, String product_type, int product_amount) {
        Image image = new Image(product_photo);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(170);
        imageView.setFitWidth(170);
        this.product_name = product_name;
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_photo = product_photo;
        this.product_type = product_type;
        this.product_amount = product_amount;
        this.product_image = imageView;


    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_photo() {
        return product_photo;
    }

    public void setProduct_photo(String product_photo) {
        this.product_photo = product_photo;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(int product_amount) {
        this.product_amount = product_amount;
    }

    public ImageView getProduct_image() {
        return product_image;
    }

    public void setProduct_image(ImageView product_image) {
        this.product_image = product_image;
    }

}
