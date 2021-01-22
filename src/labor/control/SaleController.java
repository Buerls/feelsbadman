package labor.control;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.db.DBController;
import labor.model.Product;
import labor.model.Sale;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SaleController extends WorkerMenuBase implements Initializable {

    @FXML
    private TableView<Sale> table_view;

    @FXML
    private TextField search_sale_text_field;


    ObservableList<Sale> all_sales_obl;


    public void create_table() {
        TableColumn<Sale, Integer> sale_id_column = new TableColumn<Sale, Integer>("sale_id");

        sale_id_column.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("sale_id"));

        TableColumn<Sale, Integer> customer_id_column = new TableColumn<Sale, Integer>("customer_id");

        customer_id_column.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("customer_id"));

        TableColumn<Sale, Integer> product_id_column = new TableColumn<Sale, Integer>("product_id");

        product_id_column.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("product_id"));

        TableColumn<Sale, String> sale_date_column = new TableColumn<Sale, String>("sale_date");

        sale_date_column.setCellValueFactory(new PropertyValueFactory<Sale, String>("sale_date"));

        TableColumn<Sale, Integer> sale_amount_column = new TableColumn<Sale, Integer>("sale_amount");

        sale_amount_column.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("sale_amount"));

        TableColumn<Sale, String> customer_company_column = new TableColumn<Sale, String>("customer_company");

        customer_company_column.setCellValueFactory(new PropertyValueFactory<Sale, String>("customer_company"));

        table_view.getColumns().clear();
        table_view.setItems(all_sales_obl);
        table_view.getColumns().addAll(sale_id_column, customer_id_column, product_id_column, sale_amount_column,sale_date_column, customer_company_column);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            all_sales_obl = DBController.get_sales_all();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        create_table();
        search_product();

    }

    @FXML
    void search_product() {
        table_view.setItems(all_sales_obl);
        FilteredList<Sale> filteredData = new FilteredList<Sale>(all_sales_obl, b -> true);
        search_sale_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Sale -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (Sale.getCustomer_company().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(Sale.getProduct_id()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return Sale.getCustomer_company().toLowerCase().indexOf(lowerCaseFilter) != -1;
            });
        });
        SortedList<Sale> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());
        table_view.setItems(sortedData);
    }
    @Override
    public void back_to_main_menu() {
        Stage stage = (Stage) table_view.getParent().getScene().getWindow();
        stage.close();
        super.back_to_main_menu();
    }

}
