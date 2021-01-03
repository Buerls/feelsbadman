package labor.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.db.DBController;
import labor.model.Product;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerProductSearchController implements Initializable {
    @FXML
    private TextField product_name_text;

    @FXML
    private TextField product_image_text;

    @FXML
    private TextField product_price_text;

    @FXML
    private TextField product_amount_text;

    @FXML
    private TextField product_type_text;

    @FXML
    private Button add_button;

    @FXML
    private Button update_button;

    @FXML
    private Button delete_button;

    @FXML
    private Button refresh_button;

    @FXML
    private TableView<Product> table_view;

    @FXML
    private TextField product_id_text_field;

    @FXML
    private TextField product_id_text;

    @FXML
    private ImageView back_button;

    public Product selected_product;

    ObservableList<Product> all_product_obl;

    public static ArrayList<Product> all_products_array;

    public ObservableList<Product> get_all_product() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(all_products_array);
        return products;
    }


    public void create_table() {
        TableColumn<Product, ImageView> imageColumn = new TableColumn<Product, ImageView>("Resim");
        imageColumn.setMaxWidth(170);
        imageColumn.setCellValueFactory(new PropertyValueFactory<Product, ImageView>("product_image"));

        TableColumn<Product, String> name_column = new TableColumn<Product, String>("İsim");
        name_column.setMaxWidth(350);
        name_column.setCellValueFactory(new PropertyValueFactory<Product, String>("product_name"));

        TableColumn<Product, String> price_column = new TableColumn<Product, String>("Fiyat");
        price_column.setMaxWidth(90);
        price_column.setCellValueFactory(new PropertyValueFactory<Product, String>("product_price"));

        TableColumn<Product, Integer> amount_column = new TableColumn<Product, Integer>("Adet");
        amount_column.setMaxWidth(90);
        amount_column.setCellValueFactory(new PropertyValueFactory<Product, Integer>("product_amount"));

        TableColumn<Product, String> operation_column = new TableColumn<Product, String>("Türü");
        operation_column.setMaxWidth(100);
        operation_column.setCellValueFactory(new PropertyValueFactory<Product, String>("product_type"));

        table_view.getColumns().clear();
        table_view.setItems(get_all_product());
        table_view.getColumns().addAll(imageColumn, name_column, price_column, amount_column, operation_column);
    }


    @FXML
    void search_product() {
        all_product_obl = get_all_product();
        table_view.setItems(all_product_obl);
        FilteredList<Product> filteredData = new FilteredList<>(all_product_obl, b -> true);
        product_id_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Does not match.
                if (product.getProduct_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else
                    return product.getProduct_type().toLowerCase().indexOf(lowerCaseFilter) != -1; // Filter matches password
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());
        table_view.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_table();
        search_product();
    }

    public void back_to_main_menu() {
        Stage stage = (Stage) table_view.getParent().getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/design/worker_main.fxml"));
            Stage worker_main = new Stage();
            worker_main.setTitle("Worker Ekrani");
            worker_main.setScene(new Scene(root, 400, 600));
            worker_main.setX(510);
            worker_main.setY(250);
            worker_main.initStyle(StageStyle.UNDECORATED);
            worker_main.show();

        } catch (Exception e) {
            System.out.println("Worker ekrani yüklenemedi.");
        }


    }


    ////////***************
    @FXML
    void getSelected(MouseEvent event) {
        try {
            selected_product = table_view.getSelectionModel().getSelectedItem();
            product_name_text.setText(selected_product.getProduct_name());
            product_image_text.setText(selected_product.getProduct_photo());
            product_price_text.setText(selected_product.getProduct_price());
            product_type_text.setText(selected_product.getProduct_type());
            product_amount_text.setText(String.valueOf(selected_product.getProduct_amount()));
            product_id_text.setText(String.valueOf(selected_product.getProduct_id()));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void add_product() {
        try {
            Product product = new Product(
                    product_name_text.getText(),
                    Integer.parseInt(product_id_text.getText()),
                    product_price_text.getText(),
                    product_image_text.getText(),
                    product_type_text.getText(),
                    Integer.parseInt(product_amount_text.getText()));
            DBController.add_new_product(product);
            all_products_array.add(product);
        } catch (Exception e) {
            System.out.println(e.toString());
            error(e.toString());
        }
        refresh_table();
    }

    @FXML
    void update_product() throws SQLException {
        try {
            selected_product.setProduct_amount(Integer.parseInt(product_amount_text.getText()));
            selected_product.setProduct_photo(product_image_text.getText());
            selected_product.setProduct_name(product_name_text.getText());
            selected_product.setProduct_price(product_price_text.getText());
            selected_product.setProduct_type(product_type_text.getText());
            DBController.update_product(selected_product);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        refresh_table();


    }

    @FXML
    void delete_product() throws SQLException {
        try {
            DBController.delete_product(selected_product.getProduct_id());
            all_products_array.remove(selected_product);
            refresh_table();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void refresh_table() {
        table_view.setItems(get_all_product());
        table_view.refresh();
    }

    public void error(String hata) {
        JOptionPane.showMessageDialog(null,
                "ERROR !",
                hata,
                JOptionPane.ERROR_MESSAGE);
    }


}
