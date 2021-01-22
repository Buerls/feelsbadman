package labor.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.db.DBController;
import labor.model.Customer;

import javax.swing.*;

public class RegisterController {
    @FXML
    private TextField user_company_textfield;

    @FXML
    private AnchorPane register_anchorpane;


    @FXML
    private TextField user_id_textfield;

    @FXML
    private PasswordField user_password_textfield;

    @FXML
    private TextField user_surname_textfield;

    @FXML
    private TextField user_name_textfield;

    @FXML
    public void register() {


        try {
            if (user_name_textfield.getText().equals("") || user_surname_textfield.getText().equals("") || user_id_textfield.getText().equals("") || user_password_textfield.getText().equals("") || user_company_textfield.getText().equals("")) {
                throw new Exception("Alanları boş bırakmayınız");
            }
            Customer customer = new Customer(
                    user_name_textfield.getText().strip(),
                    user_surname_textfield.getText().strip(),
                    user_id_textfield.getText().strip(),
                    1,
                    user_password_textfield.getText().strip(),
                    user_company_textfield.getText().strip());

            DBController.add_new_customer(customer);
            success();
        } catch (Exception e) {
            String error = e.toString();
            if (error.equals("java.lang.Exception: Seçtiğiniz isimle kayıtlı müşteri bulunmaktadır")) {
                error("Seçtiğiniz isimle kayıtlı müşteri bulunmaktadır");
            } else if (error.equals("java.lang.Exception: Alanları boş bırakmayınız")) {
                error("Alanları boş bırakmayınız");
            } else {
                error(error);
            }
        }
    }

    public void back() {
        Stage stage = (Stage) register_anchorpane.getScene().getWindow();
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

    public void error(String hata) {
        JOptionPane.showMessageDialog(null,
                hata,
                "Hata!",
                JOptionPane.ERROR_MESSAGE);
    }

    public void success() {
        JOptionPane.showMessageDialog(null,
                "Kayıt Tamamlandı.",
                "Bilgi",
                JOptionPane.INFORMATION_MESSAGE);

    }
}