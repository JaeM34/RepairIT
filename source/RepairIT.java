import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        //StackPane stackPane = new StackPane();


        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(800); // Set the width of the rectangle
        rectangle.setHeight(500); // Set the height of the rectangle
        rectangle.setFill(Color.WHITE); // Set the fill color of the rectangle
        rectangle.setStroke(Color.BLACK); // Set the stroke color of the rectangle
        rectangle.setStrokeWidth(1); // Set the stroke width of the rectangle
        rectangle.setX((scene.getWidth() - rectangle.getWidth()) / 2); // Set the X coordinate of the rectangle to center it horizontally
        rectangle.setY((scene.getHeight() - rectangle.getHeight()) / 2); // Set the Y coordinate of the rectangle to center it vertically

        //root.getChildren().add(rectangle);
        Button searchButton = new Button("Search Customer");
        searchButton.setPrefWidth(200); // Set preferred width to 200 pixels
        searchButton.setPrefHeight(50);
        searchButton.setFont(Font.font("Arial", 20));
        searchButton.setOnAction(event -> {
            // Handle button click event
            System.out.println("Search button clicked!");
            // Implement your search functionality here
        });

        Rectangle innerRectangle = new Rectangle();
        innerRectangle.setWidth(200);
        innerRectangle.setHeight(50);
        innerRectangle.setFill(Color.WHITE);
        innerRectangle.setStroke(Color.BLACK);
        innerRectangle.setStrokeWidth(1);
        innerRectangle.setX(rectangle.getX() + 100);
        innerRectangle.setY(rectangle.getY() + 40);

        Text repairITText = new Text("RepairIT");
        repairITText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        repairITText.setX(innerRectangle.getX() + 50);
        repairITText.setY(innerRectangle.getY() + 30);

        Rectangle innerBottomRectangle = new Rectangle();
        innerBottomRectangle.setWidth(rectangle.getWidth() / 2 );
        innerBottomRectangle.setHeight(rectangle.getHeight() - innerRectangle.getHeight() - 39);
        innerBottomRectangle.setFill(Color.WHITE);
        innerBottomRectangle.setStroke(Color.BLACK);
        innerBottomRectangle.setStrokeWidth(1);
        innerBottomRectangle.setX(rectangle.getX());
        innerBottomRectangle.setY(innerRectangle.getY() + innerRectangle.getHeight());


        root.getChildren().add(searchButton);

        Pane pane = new Pane();

        pane.getChildren().addAll(rectangle, searchButton, innerRectangle,repairITText, innerBottomRectangle);

        searchButton.layoutXProperty().bind(rectangle.layoutXProperty().add(rectangle.getWidth()).subtract(searchButton.getWidth() + 150));
        searchButton.layoutYProperty().bind(rectangle.layoutYProperty().add(90));

        root.setCenter(pane);

        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }
}


