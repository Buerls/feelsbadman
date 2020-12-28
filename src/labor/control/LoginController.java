package labor.control;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import labor.db.DBController;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private StackPane login_StackPane;

    @FXML
    private AnchorPane main_AnchorPane;

    @FXML
    private Button customer_button;

    @FXML
    private Button worker_button;

    @FXML
    private Pane customer_signUp_pane;

    @FXML
    private TextField customer_sign_id;

    @FXML
    private TextField customer_sign_password;

    @FXML
    private TextField customer_sign_name;

    @FXML
    private TextField customer_sign_surname;

    @FXML
    private TextField customer_sign_address;

    @FXML
    private TextField customer_sign_payment;

    @FXML
    private Button customer_sign_save_button;

    @FXML
    private Button customer_sign_back_button;

    @FXML
    private Pane login_Pane;

    @FXML
    private Label login_label;

    @FXML
    private TextField customer_user_id_Field;

    @FXML
    private PasswordField customer_user_password_Field;

    @FXML
    private Button login_button;

    @FXML
    private Label wrong_entry_Text;

    @FXML
    private Button customer_signUp_button;

    String login_type = "customer";
    String[] customer_columns = {"customer_user_id=", "customer_user_password="};
    String[] worker_columns = {"worker_user_id=", "worker_user_password="};

    @FXML
    void changePosLeft() {
        customer_button.setDisable(true);
        worker_button.setDisable(false);
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1), login_Pane);
        translate.setToX(login_Pane.getLayoutX());
        translate.play();
        login_label.setText("MÜŞTERİ");
        login_type = "customer";
        customer_signUp_button.setVisible(true);
        wrong_entry_Text.setVisible(false);
    }

    @FXML
    void changePosRight() {
        worker_button.setDisable(true);
        customer_button.setDisable(false);
        TranslateTransition translate = new TranslateTransition(Duration.seconds(1), login_Pane);
        translate.setToX(login_Pane.getLayoutX() + (main_AnchorPane.getPrefWidth() - login_Pane.getPrefWidth()));
        translate.play();
        login_label.setText("ÇALIŞAN");
        login_type = "worker";
        customer_signUp_button.setVisible(false);
        wrong_entry_Text.setVisible(false);
    }

    @FXML
    public void login() throws SQLException,IOException {
        String user_id = customer_user_id_Field.getText();
        String user_password = customer_user_password_Field.getText();
        if (login_type.equals("customer")) {
            if (DBController.login_auth(user_id,user_password,login_type)){
                wrong_entry_Text.setVisible(false);
                switch_to_customer_login();
            } else {
                wrong_entry_Text.setVisible(true);
           }
        } else{
            if (DBController.login_auth(user_id,user_password,login_type)){
                wrong_entry_Text.setVisible(false);
                //switch_to_customer_login();
            } else {
                wrong_entry_Text.setVisible(true);
            }

        }





    }

    private void switch_to_customer_login() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/design/customer.fxml"));

        Scene scene2 = login_button.getScene();

        root.translateYProperty().set(scene2.getHeight());
        login_StackPane.getChildren().add(root);


        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1 -> {
            login_StackPane.getChildren().remove(main_AnchorPane);
        });
        timeline.play();
    }


}
