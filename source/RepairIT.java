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

        // Create a StackPane for the rectangles and button
        StackPane stackPane = new StackPane();

        // Create a rectangle for the top left corner
        Rectangle topLeftRectangle = new Rectangle();
        topLeftRectangle.setFill(Color.WHITE); // Set fill color
        topLeftRectangle.setStroke(Color.BLACK); // Set border color
        topLeftRectangle.setStrokeWidth(2); // Set border width

        // Bind the size of the top left rectangle to the size of the root BorderPane
        topLeftRectangle.widthProperty().bind(root.widthProperty().multiply(0.2));
        topLeftRectangle.heightProperty().bind(root.heightProperty().multiply(0.1));

        // Set the alignment property of the top left rectangle to position it in the top left corner
        stackPane.setAlignment(topLeftRectangle, Pos.TOP_LEFT);

        // Create a Text node for the "RepairIT" text
        Text repairItText = new Text("RepairIT");
        Font font = Font.font("Arial", FontWeight.BOLD, 16); // Replace with desired font, weight, and size
        repairItText.setFont(font);
        repairItText.setFill(Color.BLACK); // Set text color

        // Create the main rectangle
        Rectangle mainRectangle = new Rectangle();
        mainRectangle.setFill(Color.WHITE);
        mainRectangle.setStroke(Color.BLACK); // Set border color
        mainRectangle.setStrokeWidth(2); // Set border width

        // Bind the size of the main rectangle to the size of the root BorderPane
        mainRectangle.widthProperty().bind(root.widthProperty());
        mainRectangle.heightProperty().bind(root.heightProperty());

        // Create a button
        Button button = new Button("Search Customer");
        button.setFont(font);

        button.setOnAction(event -> {
            System.out.println("Clicked");
        });

        // Add the main rectangle as the background of the stack pane
        stackPane.getChildren().add(mainRectangle);

        // Add the rectangles and button as children of the stack pane
        stackPane.getChildren().addAll(button);

        // Add the top left rectangle and text as children of the stack pane
        stackPane.getChildren().addAll(topLeftRectangle,repairItText);
        // Set the alignment property of the stack pane to position the button in the top right
        stackPane.setAlignment(button, Pos.TOP_RIGHT);

        // Bind the size of the button to the size of the main rectangle
        button.prefWidthProperty().bind(mainRectangle.widthProperty().multiply(0.2));
        button.prefHeightProperty().bind(mainRectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);

        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }

}
