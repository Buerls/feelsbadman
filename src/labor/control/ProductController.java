package labor.control;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import labor.db.DBController;
import labor.model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private AnchorPane products_main_pane;

    public static String product_type;

    public ArrayList<Product> product;

    private TableView<Product> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            product = DBController.get_products(product_type);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        create_table();
    }


    public ObservableList<Product> getProduct() {
            ObservableList<Product> products = FXCollections.observableArrayList();
            products.addAll(product);
            return products;
    }

    public void create_table() {
            TableColumn<Product, ImageView> imageColumn = new TableColumn<Product, ImageView>("Resim");
            imageColumn.setMinWidth(170);
            imageColumn.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("product_image"));

            TableColumn<Product, String> name_column = new TableColumn<Product, String>("İsim");
            name_column.setMinWidth(500);
            name_column.setCellValueFactory(new PropertyValueFactory<Product, String>("product_name"));

            TableColumn<Product, String> price_column = new TableColumn<Product, String>("Fiyat");
            price_column.setMinWidth(110);
            price_column.setCellValueFactory(new PropertyValueFactory<Product, String>("product_price"));

            TableColumn<Product, Integer> amount_column = new TableColumn<Product, Integer>("Adet");
            amount_column.setMinWidth(110);
            amount_column.setCellValueFactory(new PropertyValueFactory<Product, Integer>("product_amount"));

            TableColumn<Product, Button> operation_column = new TableColumn<Product, Button>("İşlem");
            operation_column.setMinWidth(110);
            operation_column.setCellValueFactory(new PropertyValueFactory<Product, Button>("buy_button"));


            table = new TableView<>();
            table.setItems(getProduct());
            table.getColumns().addAll(imageColumn, name_column, price_column, amount_column, operation_column);


            products_main_pane.getChildren().add(table);
        }






}
