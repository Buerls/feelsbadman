package labor.control;

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
import labor.model.Worker;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController extends WorkerMenuBase implements Initializable {

    @FXML
    private TableView<Worker> table_view;

    @FXML
    private TextField search_worker_text;

    @FXML
    private ImageView back_button;

    @FXML
    private TextField worker_username_text;

    @FXML
    private TextField worker_passwd_text;

    @FXML
    private TextField worker_name_text;

    @FXML
    private TextField worker_surname_text;

    @FXML
    private TextField worker_authority_text;

    @FXML
    private Button add_button;

    @FXML
    private Button update_button;

    @FXML
    private Button delete_button;

    @FXML
    private TextField worker_id_text;

    Worker selected_worker;
    ObservableList<Worker> all_worker_obl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            all_worker_obl = DBController.get_worker_all();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        create_table();
        search_worker();


    }

    public void create_table() {
        TableColumn<Worker, Integer> worker_id_column = new TableColumn<Worker, Integer>("worker_id");

        worker_id_column.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("user_id"));

        TableColumn<Worker, String> worker_name_cloumn = new TableColumn<Worker, String>("worker_name");

        worker_name_cloumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));

        TableColumn<Worker, String> worker_surname_column = new TableColumn<Worker, String>("worker_surname");

        worker_surname_column.setCellValueFactory(new PropertyValueFactory<Worker, String>("surname"));

        TableColumn<Worker, String> worker_user_id_column = new TableColumn<Worker, String>("worker_user_id");

        worker_user_id_column.setCellValueFactory(new PropertyValueFactory<Worker, String>("user_id"));

        TableColumn<Worker, String> worker_user_password_column = new TableColumn<Worker, String>("worker_user_password");

        worker_user_password_column.setCellValueFactory(new PropertyValueFactory<Worker, String>("user_password"));

        TableColumn<Worker, String> worker_authority_column = new TableColumn<Worker, String>("worker_authority");

        worker_authority_column.setCellValueFactory(new PropertyValueFactory<Worker, String>("assignment"));

        table_view.getColumns().clear();
        table_view.setItems(all_worker_obl);
        table_view.getColumns().addAll(worker_id_column, worker_name_cloumn, worker_surname_column, worker_user_id_column, worker_user_password_column, worker_authority_column);
    }

    @FXML
    void search_worker() {
        table_view.setItems(all_worker_obl);
        FilteredList<Worker> filteredData = new FilteredList<Worker>(all_worker_obl, b -> true);
        search_worker_text.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Worker -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // Does not match.
                if (Worker.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else
                    return Worker.getName().toLowerCase().indexOf(lowerCaseFilter) != -1; // Filter matches password
            });
        });
        SortedList<Worker> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_view.comparatorProperty());
        table_view.setItems(sortedData);
    }

    @FXML
    void update_worker() throws SQLException {
        try {
            selected_worker.setName(worker_name_text.getText());
            selected_worker.setSurname(worker_surname_text.getText());
            selected_worker.setUser_id(worker_username_text.getText());
            selected_worker.setUser_password(worker_passwd_text.getText());
            selected_worker.setAssignment(worker_authority_text.getText());
            selected_worker.setId(Integer.parseInt(worker_id_text.getText()));
            DBController.update_worker(selected_worker);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        refresh_table();
        search_worker();
    }

    @FXML
    void delete_worker() throws SQLException {
        try {
            DBController.delete_worker(Integer.parseInt(worker_id_text.getText()));
            all_worker_obl.remove(selected_worker);
            refresh_table();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        search_worker();
    }


    @FXML
    void add_worker() {
        try {
            Worker worker = new Worker(
                    worker_name_text.getText(),
                    worker_surname_text.getText(),
                    worker_username_text.getText(),
                    worker_passwd_text.getText(),
                    Integer.parseInt(worker_id_text.getText()),
                    worker_authority_text.getText());
            DBController.add_new_worker(worker);
            all_worker_obl.add(worker);
        } catch (Exception e) {
            System.out.println(e.toString());
            error(e.toString());
        }
        refresh_table();
        search_worker();
    }

    @FXML
    void getSelected(MouseEvent event) {
        try {
            selected_worker = table_view.getSelectionModel().getSelectedItem();
            worker_name_text.setText(selected_worker.getName());
            worker_id_text.setText(String.valueOf(selected_worker.getId()));
            worker_surname_text.setText(selected_worker.getSurname());
            worker_authority_text.setText(selected_worker.getAssignment());
            worker_username_text.setText(selected_worker.getUser_id());
            worker_passwd_text.setText(selected_worker.getUser_password());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void back_to_main_menu() {
        Stage stage = (Stage) table_view.getParent().getScene().getWindow();
        stage.close();
        super.back_to_main_menu();
    }

    public void refresh_table() {
        table_view.setItems(all_worker_obl);
        table_view.refresh();
    }

    public void error(String hata) {
        JOptionPane.showMessageDialog(null,
                "ERROR !",
                hata,
                JOptionPane.ERROR_MESSAGE);
    }


}