import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();


        // Create a rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK); // Set border color
        rectangle.setStrokeWidth(2); // Set border width

        // Width and height of rectangle relative size to the window
        rectangle.widthProperty().bind(root.widthProperty().subtract(root.getPadding().getLeft() + root.getPadding().getRight()));
        rectangle.heightProperty().bind(root.heightProperty().subtract(root.getPadding().getTop() + root.getPadding().getBottom()));
        // Adds rectangle as the base layer
        stackPane.getChildren().add(rectangle);

        // Create a text node
        Text titleText = new Text("RepairIT");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 40); // Replace with desired font, weight, and size
        titleText.setFont(textFont);

        // Create a button
        Button searchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        searchButton.setFont(font);

        // sets coordinates for the search button
        AnchorPane.setTopAnchor(searchButton, 20.0); // Set top anchor for Button 1
        AnchorPane.setRightAnchor(searchButton, 10.0); // Set left anchor for Button 1
        // sets coordinates for text
        AnchorPane.setTopAnchor(titleText, 10.0); // Set top anchor for Button 2
        AnchorPane.setLeftAnchor(titleText, 10.0); // Set right anchor for Button 2

        anchorPane.getChildren().addAll(searchButton, titleText);

        stackPane.getChildren().add(anchorPane);

        // Bind the size of the button to the size of the rectangle
        searchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        searchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);
        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }
}
