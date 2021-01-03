package labor.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import labor.db.DBController;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private AnchorPane login_anchorpane;


    @FXML
    private SplitMenuButton user_login_type_smb;

    @FXML
    private Button login_button;

    @FXML
    private Hyperlink register_hyperlink;

    @FXML
    private TextField user_id_textfield;

    @FXML
    private PasswordField user_password_textfield;

    @FXML
    private MenuItem customer_choice;

    @FXML
    private MenuItem worker_choice;

    //  AAAAAAAAAAAAAAAA

    @FXML
    void login() {
        String user_id = user_id_textfield.getText();
        String user_password = user_password_textfield.getText();
        String user_login_type = user_login_type_smb.getText().toLowerCase();
        try {
            if (DBController.login_auth(user_id, user_password, user_login_type)){
                switch (user_login_type) {
                    case "müşteri":
                        CustomerController.user_id = user_id;
                        CustomerController.user_password = user_password;
                        switch_to_customer_login();
                        break;
                    case "çalışan":
                        WorkerMainController.user_id = user_id;
                        WorkerMainController.user_password = user_password;
                        switch_to_worker_login();
                        break;
                    default:
                        break;
                }
            }
            else {
                error("Giriş Yapılamadı!");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void set_customer() {
        user_login_type_smb.setText("Müşteri");
    }

    @FXML
    void set_worker() {
        user_login_type_smb.setText("Çalışan");
    }
    @FXML
    void exit(){
        System.exit(0);
    }


    @FXML
    void switch_to_register() throws IOException {


        Stage stage = (Stage) login_anchorpane.getScene().getWindow();
        stage.close();


        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/design/register.fxml"));
            Stage worker_main = new Stage();
            worker_main.setTitle("Register Ekrani");
            worker_main.setScene(new Scene(root, 400, 600));
            worker_main.setX(510);
            worker_main.setY(250);
            worker_main.initStyle(StageStyle.UNDECORATED);
            worker_main.show();

        } catch (Exception e) {
            System.out.println("Register ekrani yüklenemedi.");
        }


    }




/*

    @FXML
    public void login() throws SQLException, IOException {
        String user_id = customer_user_id_Field.getText();
        String user_password = customer_user_password_Field.getText();
        if (login_type.equals("customer")) {
            if (DBController.login_auth(user_id, user_password, login_type)) {
                wrong_entry_Text.setVisible(false);
                CustomerController.user_id = user_id;
                CustomerController.user_password = user_password;
                switch_to_customer_login();
            } else {
                wrong_entry_Text.setVisible(true);
            }
        } else {
            if (DBController.login_auth(user_id, user_password, login_type)) {
                wrong_entry_Text.setVisible(false);
                WorkerMainController.user_id= user_id;
                WorkerMainController.user_password= user_password;
                switch_to_worker_login();
            } else {
                wrong_entry_Text.setVisible(true);
            }

        }


    }
 */

    private void switch_to_customer_login() throws IOException {
        Stage stage = (Stage) login_anchorpane.getScene().getWindow();
               stage.close();


               try {
                   Parent root = FXMLLoader.load(getClass().getResource("/resources/design/customer.fxml"));
                   Stage worker_main = new Stage();
                   worker_main.setTitle("Customer Ekrani");
                   worker_main.setScene(new Scene(root, 1200, 600));
                   worker_main.setX(510);
                   worker_main.setY(250);
                   worker_main.initStyle(StageStyle.UNDECORATED);
                   worker_main.show();

               } catch (Exception e) {
                   System.out.println("Customer ekrani yüklenemedi.");
               }





    }

    private void switch_to_worker_login() throws IOException {


        Stage stage = (Stage) login_anchorpane.getScene().getWindow();
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

    public void error(String hata) {
        JOptionPane.showMessageDialog(null,
                "Kullanıcı adı veya şifre yanlış.",
                hata,
                JOptionPane.ERROR_MESSAGE);
    }
}
