import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
        Text customerText = new Text("Customer Queue");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 20); // Replace with desired font, weight, and size
        customerText.setFont(textFont);

        Text repairText = new Text("Repair Queue");
        repairText.setFont(textFont);

        // Create a button
        Button searchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        searchButton.setFont(font);
        searchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // sets coordinates for customer Queue text
        AnchorPane.setTopAnchor(customerText, 10.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setLeftAnchor(customerText, 100.0); // Set right anchor for Button 2
        // sets coordinates for Repair Queue Text
        AnchorPane.setTopAnchor(repairText, 10.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setRightAnchor(repairText, 100.0); // Set right anchor for Button 2


        StackPane.setAlignment(searchButton, Pos.TOP_CENTER);
        // Bind the size of the button to the size of the rectangle
        searchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        searchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);

        // Create a VBox for the customer queue
        VBox customerQueue = new VBox();
        customerQueue.setSpacing(10); // Set spacing between customer blocks
        // Create a VBox for the repair queue
        VBox repairQueue = new VBox();
        repairQueue.setSpacing(10); // Set spacing between repair blocks

        // Create a ScrollPane to hold the customer queue and set it as left of the root BorderPane
        ScrollPane customerScrollPane = new ScrollPane(customerQueue);
        customerScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        customerScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        //customerScrollPane.setPrefSize(300, 400);
        // sets coordinates for the customerScrollPane
        AnchorPane.setTopAnchor(customerScrollPane, 70.0); // Set top anchor for customerScrollPane
        AnchorPane.setLeftAnchor(customerScrollPane, 10.0); // Set left anchor for customerScrollPane

        // Create a ScrollPane to hold the repair queue and set it as right of the root BorderPane
        ScrollPane repairScrollPane = new ScrollPane(repairQueue);
        repairScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        repairScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));

        // Formats the Repair Queue
        AnchorPane.setTopAnchor(repairScrollPane, 70.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(repairScrollPane, 10.0); // Set right anchor for repairScrollPane

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane,customerText, repairText, searchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,searchButton);
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        /*
         * CURRENTLY HIDING THIS BECAUSE I AM FIXING THE VALUES
         */
        //customerList.add(new Customer("John", "Doe","ID" , 123 , "je" ,"2" , "repairtickets"));
        //customerList.add(new Customer("John", "Doe","ID" , 123 , "je" ,"2" , "repairtickets"));
        //customerList.add(new Customer("John", "Doe","id" , 123 , "je" ,"2" , "repairtickets"));
        //customerList.add(new Customer("John", "Doe","ID" , 123 , "je" ,"2", "repairtickcts"));

        // Add customer blocks to the customer queue
        for (Customer customer : customerList) {
            VBox customerBlock = createCustomerBlock(customer);
            customerQueue.getChildren().add(customerBlock);
        }

        // Create sample data for the repair queue (replace with your actual data)
        List<Computer> repairList = new ArrayList<>();
        //repairList.add(new Computer("John", 1));
        //repairList.add(new Computer("John", 2));
        //repairList.add(new Computer("John", 3));
        //repairList.add(new Computer("John", 4));


        /// Add repair blocks to the repair queue
        for (Computer computer : repairList) {
            VBox customerBlock = createRepairBlock(computer);
            repairQueue.getChildren().add(customerBlock);
        }

        stage.setTitle("RepairIT");
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to create a customer block
    private VBox createCustomerBlock(Customer customer) {
        VBox customerBlock = new VBox();
        customerBlock.setSpacing(5); // Set spacing between UI components in customer block

        // Create labels to display customer information
        Label nameLabel = new Label("Name: " + customer.getName() + " " + customer.getName());
        Label emailLabel = new Label("Email: " + customer.getEmail());
        Label phoneLabel = new Label("Phone: " + customer.getPhone());
        Label addressLabel = new Label("Address: " + customer.getAddress());
        Label computersLabel = new Label("Computer's Label: " + customer.getComputers());
        Label repairAllTicketsLabel = new Label("Repair Tickets: " + customer.getRepairTickets());

       // Button removeButton = new Button("Remove");
       // removeButton.setOnAction(event -> {
        //    customerBlock.getChildren().remove(customerBlock);
       // });

        // Create a check-in button for the customer
        Button checkInButton = new Button("Check In");
        checkInButton.setOnAction(event -> {
            // Handle check-in action here (e.g., remove customer from customer queue, add to repair queue)
            System.out.println("Checking in customer: " + customer.getName() + " " + customer.getPhone());
            checkInButton.setText("Checked In");
            checkInButton.setDisable(true);
        });

        // Add UI components to customer block
        customerBlock.getChildren().addAll(nameLabel, emailLabel, phoneLabel,addressLabel, computersLabel, repairAllTicketsLabel,  checkInButton);

        return customerBlock;
    }

    // Helper method to create a repair block
    private VBox createRepairBlock(Computer computer) {
        VBox repairBlock = new VBox();
        repairBlock.setSpacing(5); // Set spacing between UI components in repair block

        // Create labels to display repair ticket information
        Label computerLabel = new Label("Computer: " + computer.getModel());
        Label computerIDLabel = new Label("Year: " + computer.getYear());

        // Create a repair button for the repair ticket
        Button repairButton = new Button("Repair");
        repairButton.setOnAction(event -> {
         //    Handle repair action here (e.g., remove repair ticket from repair queue, mark as repaired)
            System.out.println("Repairing computer: " + computer.getYear() + ", Ticket #" + computer.getYear());
        });

        // Add UI components to repair block
        repairBlock.getChildren().addAll(computerLabel, computerIDLabel, repairButton);

        return repairBlock;
    }

}