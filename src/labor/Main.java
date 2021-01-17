package labor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import labor.control.WorkerProductSearchController;
import labor.db.DBController;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/design/login.fxml"));
        try {
            WorkerProductSearchController.all_products_array = DBController.get_products_all();
            
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        primaryStage.setTitle("Stok Takip Otomasyonu");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
