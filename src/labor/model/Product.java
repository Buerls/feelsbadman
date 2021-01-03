package labor.model;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import labor.control.WorkerProductSearchController;
import labor.db.DBController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class Product {
    protected String product_name;
    protected int product_id;
    protected String product_price;
    protected String product_photo;
    protected String product_type;
    protected int product_amount;
    protected ImageView product_image;
    //protected Button buy_button;
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
        /*
        this.buy_button = new Button("X");
        buy_button.setOnAction(event -> {
            System.out.println("bu butona basti id:" + this.product_id);
            try {
                DBController.delete_product(this.product_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            WorkerProductSearchController.all_products_array.remove(this);
        });
*/

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
/*
    public Button getBuy_button() {
        return buy_button;
    }

    public void setBuy_button(Button buy_button) {
        this.buy_button = buy_button;
    }*/
}
