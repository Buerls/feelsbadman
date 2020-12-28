
package labor.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;

public class CustomerController {
    @FXML
    private AnchorPane customer_screen;

    @FXML
    private Label customer_name_surname_label;

    @FXML
    private ImageView closeIcon;

    @FXML
    private ImageView closeIcon1;

    @FXML
    private ImageView closeIcon2;

    @FXML
    private Pane computer_parts_pane;

    @FXML
    private Button processor_button;

    @FXML
    private Button motherboard_button;

    @FXML
    private Button ram_button;

    @FXML
    private Button graphics_card_button;

    @FXML
    private Button hard_disk_button;

    @FXML
    private Button ssd_button;

    @FXML
    private Pane computers_pane;

    @FXML
    private Button desktop_button;

    @FXML
    private Button notebook_button;

    @FXML
    private Pane game_pane;

    @FXML
    private Button ps5_button;

    @FXML
    private Button xbox_series_x_button;

    @FXML
    private Button xbox_series_s_button;

    @FXML
    private Pane peripherals_pane;

    @FXML
    private Button monitor_button;

    @FXML
    private Button keyboard_button;

    @FXML
    private Button mouse_button;

    @FXML
    private Button mousepad_button;

    @FXML
    private Button headphone_button;

    @FXML
    private Pane software_pane;

    @FXML
    private Button ms_os_button;

    @FXML
    private Button ms_office_button;


    public static String logged_in_user_id;
    public static String logged_in_user_password;

        @FXML
        void computer_parts_pane_open() { computer_parts_pane.setVisible(true); }

        @FXML
        void computer_parts_pane_close() { computer_parts_pane.setVisible(false); }

        @FXML
        void computer_pane_open() { computers_pane.setVisible(true); }

        @FXML
        void computer_pane_close() { computers_pane.setVisible(false); }

        @FXML
        void game_pane_open() { game_pane.setVisible(true); }

        @FXML
        void game_pane_close() { game_pane.setVisible(false); }

        @FXML
        void peripherals_pane_open() { peripherals_pane.setVisible(true); }

        @FXML
        void peripherals_pane_close() { peripherals_pane.setVisible(false); }

        @FXML
        void software_pane_open() { software_pane.setVisible(true); }

        @FXML
        void software_pane_close() { software_pane.setVisible(false); }

        @FXML
        void change_color_to_1(MouseEvent event){
            Button button = (Button) event.getSource();
            button.setTextFill(Color.LAWNGREEN);
        }
       @FXML
       void change_color_to_2(MouseEvent event){
           Button button = (Button) event.getSource();
           button.setTextFill(Color.WHITE);
       }

       @FXML
       void close_icon_pressed() {
               Stage stage = (Stage) closeIcon.getScene().getWindow();
               stage.close();
       }


    @FXML
    void button_pressed(MouseEvent event){
        Button source = (Button) event.getSource();
        ProductController.product_type = source.getText().toLowerCase().replace(" ","-");
        load_product_scene();
    }

    public void load_product_scene(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/resources/design/product.fxml"));
            Stage urunlerEkrani = new Stage();
            urunlerEkrani.setTitle("Urunler Ekrani");
            urunlerEkrani.setScene(new Scene(root, 1000, 395));
            urunlerEkrani.initStyle(StageStyle.DECORATED);

            // arkadaki ekrana ulaşmayı engelliyor.
            urunlerEkrani.initModality(Modality.APPLICATION_MODAL);
            urunlerEkrani.show();

        }catch (Exception e){
            System.out.println("Urunler ekrani yüklenemedi.");
        }

    }




}



