package labor.control;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.db.DBController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerMainController implements Initializable {

    @FXML
    private Pane worker_main_pane;

    @FXML
    private Label user_id_label;

    @FXML
    private Button recent_sales_button;

    @FXML
    private Button add_product_button;

    @FXML
    private Button administration_button;

    @FXML
    private Button product_operations_button;

    @FXML
    private ImageView logout_imageview;

    public static String user_id;
    public static String user_password;
    public ArrayList<String> worker_info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            worker_info = DBController.get_worker_info(user_id, user_password);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(worker_info.get(0));
        user_id_label.setText(worker_info.get(0) + " " + worker_info.get(1));
    }

    public void switch_to_login() {
        Stage stage = (Stage) worker_main_pane.getScene().getWindow();
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

        public void switch_to_add_product () {
            Stage stage = (Stage) worker_main_pane.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/design/add_product.fxml"));
                Stage add_product = new Stage();
                add_product.setTitle("Add product");
                add_product.setScene(new Scene(root, 400, 600));
                add_product.setX(510);
                add_product.setY(250);
                add_product.initStyle(StageStyle.UNDECORATED);
                add_product.show();

            } catch (Exception e) {
                System.out.println("Add Product ekrani yüklenemedi.");

            }

        }

        public void switch_to_product_search () {
            Stage stage = (Stage) worker_main_pane.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/design/product_search.fxml"));
                Stage product_search = new Stage();
                product_search.setTitle("Product Search");
                product_search.setScene(new Scene(root, 1200, 600));
                product_search.setX(510);
                product_search.setY(250);
                product_search.initStyle(StageStyle.UNDECORATED);
                product_search.show();

            } catch (Exception e) {
                System.out.println("Product Search ekrani yüklenemedi.");

            }


        }

        public void switch_to_recent_sales () {
            Stage stage = (Stage) worker_main_pane.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/design/recent_sales.fxml"));
                Stage recent_sales = new Stage();
                recent_sales.setTitle("Recent Sales");
                recent_sales.setScene(new Scene(root, 1200, 600));
                recent_sales.setX(510);
                recent_sales.setY(250);
                recent_sales.initStyle(StageStyle.UNDECORATED);
                recent_sales.show();

            } catch (Exception e) {
                System.out.println("Recent Sales ekrani yüklenemedi.");

            }


        }

        public void switch_to_administration () {
            Stage stage = (Stage) worker_main_pane.getScene().getWindow();
            stage.close();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/resources/design/administration.fxml"));
                Stage add_product = new Stage();
                add_product.setTitle("Administration");
                add_product.setScene(new Scene(root, 1200, 600));
                add_product.setX(510);
                add_product.setY(250);
                add_product.initStyle(StageStyle.UNDECORATED);
                add_product.show();

            } catch (Exception e) {
                System.out.println("Administration ekrani yüklenemedi.");

            }



    }
}


