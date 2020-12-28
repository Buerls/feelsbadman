
package labor.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
    private Pane software_button;

    @FXML
    private Button ms_os_button;

    @FXML
    private Button ms_office_button;

    @FXML
    void bilgisayarParcalariPaneAc(MouseEvent event) {

    }

    @FXML
    void bilgisayarParcalariPaneKapat(MouseEvent event) {

    }

    @FXML
    void bilgisayarlarPaneAc(MouseEvent event) {

    }

    @FXML
    void bilgisayarlarPaneKapat(MouseEvent event) {

    }

    @FXML
    void cevreBirimleriPaneAc(MouseEvent event) {

    }

    @FXML
    void cevreBirimleriPaneKapat(MouseEvent event) {

    }

    @FXML
    void closeIconBasildi(MouseEvent event) {

    }

    @FXML
    void m_anaKartBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_anaKartBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_anaKartBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_ekranKartiBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_ekranKartiBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_ekranKartiBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_harddiskBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_harddiskBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_harddiskBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_islemciBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_islemciBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_islemciBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_klavyeBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_klavyeBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_klavyeBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_kulaklikBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_kulaklikBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_kulaklikBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_masaustuBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_masaustuBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_masaustuBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_monitorBtnBasildi(MouseEvent event) {

    }

    @FXML
    void m_monitorBtnRenkDegis1(MouseEvent event) {

    }

    @FXML
    void m_monitorBtnRenkDegis2(MouseEvent event) {

    }

    @FXML
    void m_mouseBtnBasildi(MouseEvent event) {

    }
    public static String logged_in_user_id;
    public static String logged_in_user_password;


    @FXML
    void button_pressed(MouseEvent event){
        Button source = (Button) event.getSource();
        System.out.println(source.getText());
    }

}