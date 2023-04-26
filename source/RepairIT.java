import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RepairIT extends Application {

    private static final CustomerHandler customerHandler = new CustomerHandler();
    private static final RepairTicketHandler repairticketHandler = new RepairTicketHandler();
    private static final ComputerHandler computerHandler = new ComputerHandler();

    public static void main(String[] args) {
        Application.launch();
    }

    public CustomerHandler getCustomerHandler() {
        return customerHandler;
    }

    public RepairTicketHandler getRepairticketHandler() {
        return repairticketHandler;
    }

    public ComputerHandler getComputerHandler() {
        return computerHandler;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);

        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();

        // Create a rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK); // Set border color
        rectangle.setStrokeWidth(2); // Set border width

        rectangle.widthProperty().bind(root.widthProperty().subtract(root.getPadding().getLeft() + root.getPadding().getRight()));
        rectangle.heightProperty().bind(root.heightProperty().subtract(root.getPadding().getTop() + root.getPadding().getBottom()));

        // Create a text node
        Text text = new Text("RepairIT");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 40); // Replace with desired font, weight, and size
        text.setFont(textFont);
        // Create a button
        Button button = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        button.setFont(font);

        // Add the rectangle and button to the stack pane
        stackPane.getChildren().addAll(rectangle,text, button);

        // Set the alignment property of the stack pane to position the text in the top center
        stackPane.setAlignment(text, Pos.TOP_CENTER);

        // Set the alignment property of the stack pane to position the button in the top right
        stackPane.setAlignment(button, Pos.TOP_RIGHT);

        // Bind the size of the button to the size of the rectangle
        button.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        button.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);

        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }
}
