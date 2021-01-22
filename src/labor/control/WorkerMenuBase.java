package labor.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public abstract class  WorkerMenuBase {
    public void back_to_main_menu() {
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

}
