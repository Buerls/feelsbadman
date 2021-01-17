

package labor.control;
import labor.db.DBController;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.db.DBController;
import labor.model.Product;


import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TableView<Product> table_view;

    @FXML
    private TextField search_textfield;

    @FXML
    private ImageView logout_button;

    @FXML
    private TextField product_amount_textfield;

    @FXML
    private Button buy_button;

    @FXML
    private Text product_price;

    @FXML
    private Label product_name;

    @FXML
    private Text sum;

    @FXML
    private Text name;


    public static String customer_name;
    public static String customer_company;
    public static int customer_id;
    
    public int product_id;


    public Product selected_product;

    ObservableList<Product> all_product_obl;

    ArrayList<Product> all_products_array;

    @FXML
    public void switch_to_login() {
        Stage stage = (Stage) table_view.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/design/login.fxml"));
            Stage login = new Stage();
            login.setTitle("Login");
            login.setScene(new Scene(root, 400, 600));
            login.initStyle(StageStyle.UNDECORATED);
            login.show();

        } catch (Exception e) {
            System.out.println("Login ekrani yüklenemedi.");

        }
    }

    @FXML
    void getSelected(MouseEvent event) {
        try {
            selected_product = table_view.getSelectionModel().getSelectedItem();
            product_id = selected_product.getProduct_id();
            product_name.setText(selected_product.getProduct_name());
            product_price.setText(selected_product.getProduct_price());
            product_amount_textfield.setPromptText(String.valueOf(selected_product.getProduct_amount()));
            int amount = selected_product.getProduct_amount();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @FXML
    void search_product() {
        all_product_obl = get_all_product();
        table_view.setItems(all_product_obl);
        FilteredList<Product> filteredData = new FilteredList<>(all_product_obl, b -> true);
        search_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(customer_name);
        all_products_array = WorkerProductSearchController.all_products_array;
        create_table();
        search_product();
        set_sum();
    }

    @FXML
    void set_sum() {

            product_amount_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
                try{
                    if (newValue.equals("")) {
                    newValue = "0";
                }
                    Integer.parseInt(newValue);
                    Integer.parseInt(product_amount_textfield.getPromptText());

                    if (Integer.parseInt(newValue) > Integer.parseInt(product_amount_textfield.getPromptText())) {
                        error("Stok-Talep Uyuşmazlığı", "Talep ettiğiniz sayıda ürün bulunamadı.");
                        product_amount_textfield.setText("0");
                    } else {
                        int total = Integer.parseInt(newValue) * Integer.parseInt(product_price.getText().replace(".", ""));
                        sum.setText(String.valueOf(total));
                    }
                }catch(Exception e){
                    error("Girdi Hatası","Ürün miktarı değerini kontrol ediniz");
                    product_amount_textfield.setText("0");
                }



            });



    }



    public void error(String title, String message) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public void sale(){

    try{
        LocalDateTime date_now = LocalDateTime.now();
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String sale_date = date_now.format(date_formatter);
        if (selected_product.getProduct_amount()<Integer.parseInt(product_amount_textfield.getText())){
            error("Yetersiz Ürün Miktarı","Almak istediğiniz kadar ürün elimizde bulunmamaktadır");
        }else if (product_amount_textfield.getText().equals("0")){
            error("Geçersiz İşlem","Hiç ürün almıyorsunuz !!");
        }
        else{
            DBController.sale(customer_id,product_id,Integer.parseInt(product_amount_textfield.getText()),sale_date,customer_company);
            selected_product.setProduct_amount(selected_product.getProduct_amount() - Integer.parseInt(product_amount_textfield.getText()));
            DBController.update_product(selected_product);
            table_view.refresh();
            product_amount_textfield.setText(product_amount_textfield.getText());
        }

    }catch(Exception e){
    System.out.println("hata: "+e);
    }
    }


}