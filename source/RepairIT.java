import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RepairIT extends Application{
    public static void main(String[] args) {
        Application.launch();
        System.out.println("Hello world!");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root;
        Scene scene = new Scene(null,1920, 1080);
        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }
}