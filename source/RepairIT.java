import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.scene.control.TextField;
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

    public static CustomerHandler getCustomerHandler() {
        return customerHandler;
    }

    public static RepairTicketHandler getRepairticketHandler() {
        return repairticketHandler;
    }

    public static ComputerHandler getComputerHandler() {
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

        // Create nodes for the 3 queues
        Text customerText = new Text("Customer Queue");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 20); // Replace with desired font, weight, and size
        customerText.setFont(textFont);

        Text repairText = new Text("Repair Queue");
        repairText.setFont(textFont);

        Text searchText = new Text("Search - Customer");
        searchText.setFont(textFont);

        // Search Customer button made
        Button customerSearchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        customerSearchButton.setFont(font);
        customerSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // sets coordinates for customer Queue text
        AnchorPane.setTopAnchor(customerText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setLeftAnchor(customerText, 70.0); // Set right anchor for Button 2
        // sets coordinates for Repair Queue Text
        AnchorPane.setTopAnchor(repairText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setRightAnchor(repairText, 85.0); // Set right anchor for Button 2
        // sets coordinates for Search - Customer
        AnchorPane.setTopAnchor(searchText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setRightAnchor(searchText, 400.0); // Set right anchor for Button 2

        StackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        // Bind the size of the button to the size of the rectangle
        customerSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        customerSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));
        StackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        // Bind the size of the button to the size of the rectangle
        computerSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        computerSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));
        StackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        // Bind the size of the button to the size of the rectangle
        repairSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        repairSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);

        // Create a VBox for the customer queue
        VBox customerQueue = new VBox();
        customerQueue.setSpacing(10); // Set spacing between customer blocks
        // Create a VBox for the repair queue
        VBox repairQueue = new VBox();
        repairQueue.setSpacing(10); // Set spacing between repair blocks
        // Create a VBox for the search queue
        VBox searchQueue = new VBox();
        searchQueue.setSpacing(10); // Set spacing between repair blocks

        // Create a ScrollPane to hold the customer queue and set it as left of the root BorderPane
        ScrollPane customerScrollPane = new ScrollPane(customerQueue);
        customerScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        customerScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        //customerScrollPane.setPrefSize(300, 400);
        // sets coordinates for the customerScrollPane
        AnchorPane.setTopAnchor(customerScrollPane, 100.0); // Set top anchor for customerScrollPane
        AnchorPane.setLeftAnchor(customerScrollPane, 10.0); // Set left anchor for customerScrollPane

        // Create a ScrollPane to hold the repair queue and set it as right of the root BorderPane
        ScrollPane repairScrollPane = new ScrollPane(repairQueue);
        repairScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        repairScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        // Formats the Repair Queue
        AnchorPane.setTopAnchor(repairScrollPane, 100.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(repairScrollPane, 10.0); // Set right anchor for repairScrollPane

        // Create a ScrollPane to hold the search queue at the center of the BorderPane
        ScrollPane searchScrollPane = new ScrollPane(searchQueue);
        searchScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        searchScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        // Formats the Search Queue
        AnchorPane.setTopAnchor(searchScrollPane, 100.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(searchScrollPane, 340.0); // Set right anchor for repairScrollPane

        // Text for center pane
        Font subtextFont = Font.font("Arial", FontWeight.NORMAL, 12);
        Text phoneNumber = new Text("Phone Number");
        phoneNumber.setFont(subtextFont);
        Text nameAndAddress = new Text("Name and Address");
        nameAndAddress.setFont(subtextFont);

        //fields to enter info to search for customer with
        TextField searchField1 = new TextField();
        TextField searchField2 = new TextField();

        // Search button made
        Button searchButton = new Button("Search");
        searchButton.setFont(subtextFont);
        searchButton.setOnAction(actionEvent -> {
            System.out.println("search button clicked");
        });

        // Set up for center pane
        searchQueue.setAlignment(Pos.TOP_LEFT);
        searchQueue.getChildren().addAll(phoneNumber, searchField1, nameAndAddress, searchField2, searchButton);
        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText,
                searchText, computerSearchButton, repairSearchButton, customerSearchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();


        /*
         * CURRENTLY HIDING THIS BECAUSE I AM FIXING THE VALUES
         */
        customerList.add(new Customer("John Smith", "Somewhere", "12345678",
                "(323)555-1234", "my@mail.com", null, null));
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
        repairList.add(new Computer("John", 1));
        repairList.add(new Computer("John", 2));
        repairList.add(new Computer("John", 3));
        repairList.add(new Computer("John", 4));

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
        Label nameLabel = new Label(customer.getName());
        Label phoneLabel = new Label(customer.getPhone());

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            ((Pane) customerBlock.getParent()).getChildren().remove(customerBlock);
        });

        // Create a check-in button for the customer
        Button checkInButton = new Button("Check In");
        checkInButton.setOnAction(event -> {
            // Handle check-in action here (e.g., remove customer from customer queue, add to repair queue)
            System.out.println("Checking in customer: " + customer.getName() + " " + customer.getPhone());
            checkInButton.setText("Checked In");
            checkInButton.setDisable(true);

        });

        Button viewCustomer = new Button("View Customer");  // BUTTON TO OPEN CUSTOMERPAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        viewCustomer.setOnAction(event -> {
            Stage primaryStage = (Stage) viewCustomer.getScene().getWindow();
            primaryStage.setScene(createCustomerScene());
            //ViewCustomerWindow viewCustomerWindow = new ViewCustomerWindow();
        });

        // Add UI components to customer block
        customerBlock.getChildren().addAll(nameLabel, phoneLabel, checkInButton, removeButton, viewCustomer);
        return customerBlock;
    }

    // Helper method to create a repair block
    private VBox createRepairBlock(Computer computer) {
        VBox repairBlock = new VBox();
        repairBlock.setSpacing(5); // Set spacing between UI components in repair block

        // Create labels to display repair ticket information
        Label computerLabel = new Label("Computer: " + computer.getModel());
        Label computerIDLabel = new Label("Year: " + computer.getYear());

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            ((Pane) repairBlock.getParent()).getChildren().remove(repairBlock);
        });

        // Create a repair button for the repair ticket
        Button repairButton = new Button("Repair");
        repairButton.setOnAction(event -> {

            Stage primaryStage = (Stage) repairButton.getScene().getWindow();
            primaryStage.setScene(createRepairScene(computer));
        });

        // Add UI components to repair block
        repairBlock.getChildren().addAll(computerLabel, computerIDLabel, repairButton, removeButton);

        return repairBlock;
    }


    private Scene createRepairScene(Computer computer) {
        // Create a new pane for the customer scene
        Pane repairPane = new Pane();
        repairPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(repairPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });

        repairPane.getChildren().add(backButton);
        return customerScene;
    }


    private Scene createCustomerScene() {
        // Create a new pane for the customer scene
        Pane customerPane = new Pane();
        customerPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(customerPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });

        customerPane.getChildren().add(backButton);
        return customerScene;
    }

    private Scene createMainScene() {
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

        // Create nodes for the 3 queues
        Text customerText = new Text("Customer Queue");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 20); // Replace with desired font, weight, and size
        customerText.setFont(textFont);

        Text repairText = new Text("Repair Queue");
        repairText.setFont(textFont);

        Text searchText = new Text("Search - Customer");
        searchText.setFont(textFont);

        // Search Customer button made
        Button customerSearchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        customerSearchButton.setFont(font);
        customerSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            System.out.println("button clicked");
        });

        // sets coordinates for customer Queue text
        AnchorPane.setTopAnchor(customerText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setLeftAnchor(customerText, 70.0); // Set right anchor for Button 2
        // sets coordinates for Repair Queue Text
        AnchorPane.setTopAnchor(repairText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setRightAnchor(repairText, 85.0); // Set right anchor for Button 2
        // sets coordinates for Search - Customer
        AnchorPane.setTopAnchor(searchText, 75.0); // Set top anchor for CUSTOMER Queue Text
        AnchorPane.setRightAnchor(searchText, 400.0); // Set right anchor for Button 2

        StackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        // Bind the size of the button to the size of the rectangle
        customerSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        customerSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));
        StackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        // Bind the size of the button to the size of the rectangle
        computerSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        computerSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));
        StackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        // Bind the size of the button to the size of the rectangle
        repairSearchButton.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.2));
        repairSearchButton.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.1));

        // Add the stack pane to the root BorderPane
        root.setCenter(stackPane);

        // Create a VBox for the customer queue
        VBox customerQueue = new VBox();
        customerQueue.setSpacing(10); // Set spacing between customer blocks
        // Create a VBox for the repair queue
        VBox repairQueue = new VBox();
        repairQueue.setSpacing(10); // Set spacing between repair blocks
        // Create a VBox for the search queue
        VBox searchQueue = new VBox();
        searchQueue.setSpacing(10); // Set spacing between repair blocks

        // Create a ScrollPane to hold the customer queue and set it as left of the root BorderPane
        ScrollPane customerScrollPane = new ScrollPane(customerQueue);
        customerScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        customerScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        //customerScrollPane.setPrefSize(300, 400);
        // sets coordinates for the customerScrollPane
        AnchorPane.setTopAnchor(customerScrollPane, 100.0); // Set top anchor for customerScrollPane
        AnchorPane.setLeftAnchor(customerScrollPane, 10.0); // Set left anchor for customerScrollPane

        // Create a ScrollPane to hold the repair queue and set it as right of the root BorderPane
        ScrollPane repairScrollPane = new ScrollPane(repairQueue);
        repairScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        repairScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        // Formats the Repair Queue
        AnchorPane.setTopAnchor(repairScrollPane, 100.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(repairScrollPane, 10.0); // Set right anchor for repairScrollPane

        // Create a ScrollPane to hold the search queue at the center of the BorderPane
        ScrollPane searchScrollPane = new ScrollPane(searchQueue);
        searchScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        searchScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        // Formats the Search Queue
        AnchorPane.setTopAnchor(searchScrollPane, 100.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(searchScrollPane, 340.0); // Set right anchor for repairScrollPane

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText, searchText, computerSearchButton, repairSearchButton, customerSearchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        /*
         * CURRENTLY HIDING THIS BECAUSE I AM FIXING THE VALUES
         */
        customerList.add(new Customer("John", 2, "Somewhere"));
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
        repairList.add(new Computer("John", 1));
        repairList.add(new Computer("John", 2));
        repairList.add(new Computer("John", 3));
        repairList.add(new Computer("John", 4));


        /// Add repair blocks to the repair queue
        for (Computer computer : repairList) {
            VBox customerBlock = createRepairBlock(computer);
            repairQueue.getChildren().add(customerBlock);
        }


        return scene;
    }


    private Scene createAddComputerPage(){  // temporary windows for each page
        // Create a new pane for the customer scene
        Pane customerPane = new Pane();
        customerPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(customerPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        return customerScene;

    }

    private Scene createComputerPage(){   // temporary windows for each page
        // Create a new pane for the customer scene
        Pane customerPane = new Pane();
        customerPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(customerPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        return customerScene;
    }

    private Scene createCreateRepairTicketPage(){  // temporary windows for each page
        // Create a new pane for the customer scene
        Pane customerPane = new Pane();
        customerPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(customerPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        return customerScene;
    }

    private Scene createRepairTicketOverviewPage(){     // temporary windows for each page
        // Create a new pane for the customer scene
        Pane customerPane = new Pane();
        customerPane.setPadding(new Insets(10));
        Scene customerScene = new Scene(customerPane, 1000, 600);

        // Add UI components and logic for the customer scene here

        return customerScene;
    }


}
