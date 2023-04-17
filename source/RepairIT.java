import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RepairIT extends Application {

    private static final CustomerHandler customerHandler = new CustomerHandler();
    public static void main(String[] args) {
        Application.launch();
    }

    public CustomerHandler getCustomerHandler() {
        return customerHandler;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root,500, 600);
        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }
}