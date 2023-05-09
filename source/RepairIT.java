import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

        Scene mainScene = createMainScene();
        // Set the created scene as the primary stage scene
        stage.setScene(mainScene);
        stage.show();
    }

    // Helper method to create a customer block
    private VBox createCustomerBlock(Customer customer) {
        VBox customerBlock = new VBox();
        customerBlock.setSpacing(5); // Set spacing between UI components in customer block

        // Create labels to display customer information
        Label nameLabel = new Label(customer.getName());
        Label phoneLabel = new Label(customer.getPhone());
        Label emailLabel = new Label(customer.getEmail());



        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            ((Pane) customerBlock.getParent()).getChildren().remove(customerBlock);
        });

        Button checkIn = new Button("Check In");  // BUTTON TO OPEN CUSTOMERPAGE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        checkIn.setOnAction(event -> {
            Stage primaryStage = (Stage) checkIn.getScene().getWindow();
            primaryStage.setScene(createCustomerScene(customer));
        });

        // Add UI components to customer block
        customerBlock.getChildren().addAll(nameLabel, phoneLabel,emailLabel, removeButton, checkIn);
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
            primaryStage.setScene(createRepairScene());
        });

        // Add UI components to repair block
        repairBlock.getChildren().addAll(computerLabel, computerIDLabel, repairButton, removeButton);
        return repairBlock;
    }

    private Scene createRepairScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();
        //Pane contentPane = new Pane();

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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
        hbox.setPrefHeight(20); // Set the height of the HBox

        // HBox.setMargin(hbox, root.getPadding());
        //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

      //  Label nameLabel = new Label("Name: " + customer.getName());
       // Label phoneLabel = new Label("Phone: " + customer.getPhone());
       // Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
      //  nameLabel.setFont(labelFont);
       // phoneLabel.setFont(labelFont);
       // emailLabel.setFont(labelFont);


      //  hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addComputerButton);

        // Create an HBox for the buttons
        HBox addComputerBox = new HBox();
        addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
        //addCustomerBox.setTranslateX(400); // Set X coordinate
        addComputerBox.setTranslateY(100); // Set Y coordinate
        // Add the button to the HBox
        //addComputerBox.getChildren().add(addComputerButton);

        BorderPane hboxBorder = new BorderPane();
        hboxBorder.setCenter(addComputerBox);
        hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


        // Add UI components and logic for the search scene here
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });
        // sets the size of the back button
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(30);
        // sets location of the back button
        root.setTop(backButton);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(addComputerBox, 10.0);

        stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
        stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
       // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );


        return scene;
    }

      private Scene createCustomerScene(Customer customer) {
          BorderPane root = new BorderPane();
          root.setPadding(new Insets(10));
          Scene scene = new Scene(root, 1000, 600);
          // Create a StackPane for the rectangle and button
          StackPane stackPane = new StackPane();
          // Create a AnchorPane for UI component placement
          AnchorPane anchorPane = new AnchorPane();
          //Pane contentPane = new Pane();

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

          Button addComputerButton = new Button("Add Computer");
          addComputerButton.setOnAction(e -> {
              Stage primaryStage = (Stage) addComputerButton.getScene().getWindow();
              primaryStage.setScene(createAddComputerPage(customer));
          });
          addComputerButton.setPrefWidth(200); // Set the width of the button
          addComputerButton.setPrefHeight(50); // Set the height of the button


          HBox hbox = new HBox(10);
          hbox.setAlignment(Pos.CENTER_LEFT);
          hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
          hbox.setPrefHeight(20); // Set the height of the HBox

          // HBox.setMargin(hbox, root.getPadding());
          //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
          // Display customer information horizontally
          AnchorPane.setTopAnchor(hbox, 70.0);
          AnchorPane.setLeftAnchor(hbox, 0.0);
          AnchorPane.setRightAnchor(hbox, 0.0);
          AnchorPane.setBottomAnchor(hbox, 400.0);

          Label nameLabel = new Label("Name: " + customer.getName());
          Label phoneLabel = new Label("Phone: " + customer.getPhone());
          Label emailLabel = new Label("Email: " + customer.getEmail());


          Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
          nameLabel.setFont(labelFont);
          phoneLabel.setFont(labelFont);
          emailLabel.setFont(labelFont);


          hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addComputerButton);

          // Create an HBox for the buttons
          HBox addComputerBox = new HBox();
          addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
          //addCustomerBox.setTranslateX(400); // Set X coordinate
          addComputerBox.setTranslateY(100); // Set Y coordinate
          // Add the button to the HBox
          //addComputerBox.getChildren().add(addComputerButton);

          BorderPane hboxBorder = new BorderPane();
          hboxBorder.setCenter(addComputerBox);
          hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


          // Add UI components and logic for the search scene here
          Button backButton = new Button("Back");
          backButton.setOnAction(event -> {
              Stage primaryStage = (Stage) backButton.getScene().getWindow();
              primaryStage.setScene(createMainScene());
          });
          // sets the size of the back button
          backButton.setPrefWidth(100);
          backButton.setPrefHeight(30);
          // sets location of the back button
          root.setTop(backButton);
          AnchorPane.setTopAnchor(backButton, 10.0);
          AnchorPane.setLeftAnchor(backButton, 10.0);
          AnchorPane.setTopAnchor(addComputerBox, 10.0);

          stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
          stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
          stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
          stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
          stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
          //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
          stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
          stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

          // Adds UI elements to scene
          anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
          // Places UI elements on top of the rectangle
          stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );


          return scene;
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
            System.out.println("already on Customer");
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) computerSearchButton.getScene().getWindow();
            primaryStage.setScene(searchComputerPage());
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) repairSearchButton.getScene().getWindow();
            primaryStage.setScene(searchRepairTicket());
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
        //  Text phoneNumber = new Text("Phone Number");
        // phoneNumber.setFont(subtextFont);
        Text nameAndAddress = new Text("Name and Address");
        nameAndAddress.setFont(subtextFont);

        //fields to enter info to search for customer with
        TextField searchField1 = new TextField();
        TextField searchField2 = new TextField();

        // Search button made
        Button searchButton = new Button("Search");
        searchButton.setFont(subtextFont);
        searchButton.setOnAction(actionEvent -> {
            String name = searchField1.getText();
            String address = searchField2.getText();

            Customer customer = customerHandler.getCustomerByNameAndAddress(name, address);
            if (customer != null) {
                Label resultLabel = new Label("Customer Found:\nName: " + customer.getName() + "\nPhone: " + customer.getPhone() + "\nAddress: " + customer.getAddress() + "\nEmail: " + customer.getEmail());
                searchQueue.getChildren().add(resultLabel);
            } else {
                Label resultLabel = new Label("Customer Not Found");
                searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
            searchField2.clear();
        });

        GridPane grid = new GridPane();
        // Add the text fields to the grid
        grid.add(new Label("Name:"), 0, 1);
        grid.add(searchField1, 1, 1);
       // grid.add(new Label("Address:"), 0, 2);
        grid.add(new Label("Phone Number:"), 0, 2);
        grid.add(searchField2, 1, 2);

        // Set up for center pane
        searchQueue.setAlignment(Pos.TOP_LEFT);
        searchQueue.getChildren().addAll(grid, searchButton);

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText, searchText, computerSearchButton, repairSearchButton, customerSearchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        // Create sample data for the customer queue (replace with your actual data)

        Customer customer1 = new Customer("John", "Here", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer2 = new Customer("John Smith", "Somewhere", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer3 = new Customer("Joe", "Mama", "12345678",
                "(323)555-1234", "my@mail.com", null, null);

        CustomerHandler addCustomer = getCustomerHandler();
        addCustomer.saveCustomer(customer1);
        addCustomer.saveCustomer(customer2);
        addCustomer.saveCustomer(customer3);

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        // Add customer blocks to the customer queue
        for (Customer customer : customerList) {
            VBox customerBlock = createCustomerBlock(customer);
            customerQueue.getChildren().add(customerBlock);
        }
        ComputerHandler addComputer = getComputerHandler();
        Computer computer = new Computer("123","321", "manufacturer", "Dell","Serial number" , 2023 );
        Computer computer1 = new Computer("123","321", "manufacturer", "Dell","Serial number" , 2023 );
        Computer computer2 = new Computer("123","321", "manufacturer", "Dell","Serial number" , 2023 );

        addComputer.saveComputer(computer);
        addComputer.saveComputer(computer1);
        addComputer.saveComputer(computer2);

        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers = new ArrayList<>();
        computers.add(0, computer);
        computers.add(1, computer1);
        computers.add(2, computer2);

        customer1.setComputers(computers);

        ArrayList<RepairTicket> repairTickets = new ArrayList<>();

        // Create sample data for the repair queue (replace with your actual data)
        List<Computer> repairList = new ArrayList<>();
        repairList.add(computer);
        repairList.add(computer1);
        repairList.add(computer2);
        //repairList.add(new Computer("John", 4));

        /// Add repair blocks to the repair queue
        for (Computer Computer : repairList) {
            VBox customerBlock = createRepairBlock(Computer);
            repairQueue.getChildren().add(customerBlock);
        }

        return scene;
    }


    private Scene createAddComputerPage(Customer customer){  // temporary windows for each page
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();
        //Pane contentPane = new Pane();

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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
        hbox.setPrefHeight(20); // Set the height of the HBox

        // HBox.setMargin(hbox, root.getPadding());
        //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

          Label nameLabel = new Label("Name: " + customer.getName());
         Label phoneLabel = new Label("Phone: " + customer.getPhone());
         Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
          nameLabel.setFont(labelFont);
          phoneLabel.setFont(labelFont);
          emailLabel.setFont(labelFont);


          hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel);

        // Create an HBox for the buttons
        HBox addComputerBox = new HBox();
        addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
        //addCustomerBox.setTranslateX(400); // Set X coordinate
        addComputerBox.setTranslateY(100); // Set Y coordinate
        // Add the button to the HBox
        //addComputerBox.getChildren().add(addComputerButton);

        BorderPane hboxBorder = new BorderPane();
        hboxBorder.setCenter(addComputerBox);
        hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


        // Add UI components and logic for the search scene here
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createCustomerScene(customer));
        });
        // sets the size of the back button
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(30);
        // sets location of the back button
        root.setTop(backButton);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(addComputerBox, 10.0);

        stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
        stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
        // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );


        return scene;
    }
    private Scene createComputerPage(){   // temporary windows for each page
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();
        //Pane contentPane = new Pane();

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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
        hbox.setPrefHeight(20); // Set the height of the HBox

        // HBox.setMargin(hbox, root.getPadding());
        //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

        //  Label nameLabel = new Label("Name: " + customer.getName());
        // Label phoneLabel = new Label("Phone: " + customer.getPhone());
        // Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
        //  nameLabel.setFont(labelFont);
        // phoneLabel.setFont(labelFont);
        // emailLabel.setFont(labelFont);


        //  hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addComputerButton);

        // Create an HBox for the buttons
        HBox addComputerBox = new HBox();
        addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
        //addCustomerBox.setTranslateX(400); // Set X coordinate
        addComputerBox.setTranslateY(100); // Set Y coordinate
        // Add the button to the HBox
        //addComputerBox.getChildren().add(addComputerButton);

        BorderPane hboxBorder = new BorderPane();
        hboxBorder.setCenter(addComputerBox);
        hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


        // Add UI components and logic for the search scene here
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });
        // sets the size of the back button
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(30);
        // sets location of the back button
        root.setTop(backButton);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(addComputerBox, 10.0);

        stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
        stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
        // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );

        return scene;
    }
    private Scene createCreateRepairTicketPage(){  // temporary windows for each page
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();
        //Pane contentPane = new Pane();

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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
        hbox.setPrefHeight(20); // Set the height of the HBox

        // HBox.setMargin(hbox, root.getPadding());
        //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

        //  Label nameLabel = new Label("Name: " + customer.getName());
        // Label phoneLabel = new Label("Phone: " + customer.getPhone());
        // Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
        //  nameLabel.setFont(labelFont);
        // phoneLabel.setFont(labelFont);
        // emailLabel.setFont(labelFont);


        //  hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addComputerButton);

        // Create an HBox for the buttons
        HBox addComputerBox = new HBox();
        addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
        //addCustomerBox.setTranslateX(400); // Set X coordinate
        addComputerBox.setTranslateY(100); // Set Y coordinate
        // Add the button to the HBox
        //addComputerBox.getChildren().add(addComputerButton);

        BorderPane hboxBorder = new BorderPane();
        hboxBorder.setCenter(addComputerBox);
        hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


        // Add UI components and logic for the search scene here
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });
        // sets the size of the back button
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(30);
        // sets location of the back button
        root.setTop(backButton);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(addComputerBox, 10.0);

        stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
        stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
        // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );


        return scene;
    }

    private Scene createRepairTicketOverviewPage(){     // temporary windows for each page
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();
        //Pane contentPane = new Pane();

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

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Add border styling
        hbox.setPrefHeight(20); // Set the height of the HBox

        // HBox.setMargin(hbox, root.getPadding());
        //HBox.setMargin(hbox, new Insets(10, 0, 0, 100)); // Set specific coordinates for the HBox
        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

        //  Label nameLabel = new Label("Name: " + customer.getName());
        // Label phoneLabel = new Label("Phone: " + customer.getPhone());
        // Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
        //  nameLabel.setFont(labelFont);
        // phoneLabel.setFont(labelFont);
        // emailLabel.setFont(labelFont);


        //  hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel, addComputerButton);

        // Create an HBox for the buttons
        HBox addComputerBox = new HBox();
        addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
        //addCustomerBox.setTranslateX(400); // Set X coordinate
        addComputerBox.setTranslateY(100); // Set Y coordinate
        // Add the button to the HBox
        //addComputerBox.getChildren().add(addComputerButton);

        BorderPane hboxBorder = new BorderPane();
        hboxBorder.setCenter(addComputerBox);
        hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties


        // Add UI components and logic for the search scene here
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            Stage primaryStage = (Stage) backButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });
        // sets the size of the back button
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(30);
        // sets location of the back button
        root.setTop(backButton);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        AnchorPane.setTopAnchor(addComputerBox, 10.0);

        stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
        stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
        stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
        stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
        // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );


        return scene;
    }

    private Scene searchComputerPage(){  // to do probably get rid of
        // Create a new pane for the customer scene
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

        Text searchText = new Text("Search - Computer");
        searchText.setFont(textFont);

        // Search Customer button made
        Button customerSearchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        customerSearchButton.setFont(font);
        customerSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) customerSearchButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            System.out.println("Already on computer");
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) repairSearchButton.getScene().getWindow();
            primaryStage.setScene(searchRepairTicket());
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
        //  Text phoneNumber = new Text("Phone Number");
        // phoneNumber.setFont(subtextFont);
        Text nameAndAddress = new Text("Name and Address");
        nameAndAddress.setFont(subtextFont);

        //fields to enter info to search for customer with
        TextField searchField1 = new TextField();
        TextField searchField2 = new TextField();

        // Search button made
        Button searchButton = new Button("Search");
        searchButton.setFont(subtextFont);
        searchButton.setOnAction(actionEvent -> {
            String manufacturer = searchField1.getText();
            String serial = searchField2.getText();

            Computer computer = computerHandler.getComputerBySerialNumber(serial);
            if (computer != null) {
                Label resultLabel = new Label("Computer found");
                searchQueue.getChildren().add(resultLabel);
            } else {
                Label resultLabel = new Label("Computer not found");
                searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
            searchField2.clear();
        });

        GridPane grid = new GridPane();
        // Add the text fields to the grid
        grid.add(new Label("Manufacturer:"), 0, 1);
        grid.add(searchField1, 1, 1);
        grid.add(new Label("Serial Number:"), 0, 2);
        grid.add(searchField2, 1, 2);
        // Set up for center pane
        searchQueue.setAlignment(Pos.TOP_LEFT);
        searchQueue.getChildren().addAll(grid, searchButton);

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText, searchText, computerSearchButton, repairSearchButton, customerSearchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        // Create sample data for the customer queue (replace with your actual data)

        Customer customer1 = new Customer("John", "Here", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer2 = new Customer("John Smith", "Somewhere", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer3 = new Customer("Joe", "Mama", "12345678",
                "(323)555-1234", "my@mail.com", null, null);


        CustomerHandler addCustomer = getCustomerHandler();
        addCustomer.saveCustomer(customer1);
        addCustomer.saveCustomer(customer2);
        addCustomer.saveCustomer(customer3);

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        // Add customer blocks to the customer queue
        for (Customer customer : customerList) {
            VBox customerBlock = createCustomerBlock(customer);
            customerQueue.getChildren().add(customerBlock);
        }

        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers = new ArrayList<>();
        ArrayList<RepairTicket> repairTickets = new ArrayList<>();

        // Create sample data for the repair queue (replace with your actual data)
        List<Computer> repairList = new ArrayList<>();
       // repairList.add(new Computer("John", 1));
       // repairList.add(new Computer("John", 2));
       // repairList.add(new Computer("John", 3));
       // repairList.add(new Computer("John", 4));


        /// Add repair blocks to the repair queue
        for (Computer computer : repairList) {
            VBox customerBlock = createRepairBlock(computer);
            repairQueue.getChildren().add(customerBlock);
        }
        return scene;
    }

    private Scene searchRepairTicket() {  // To do probably get rid of
        // Create a new pane for the customer scene
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

        Text searchText = new Text("Search - Repair Ticket");
        searchText.setFont(textFont);

        // Search Customer button made
        Button customerSearchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        customerSearchButton.setFont(font);
        customerSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) customerSearchButton.getScene().getWindow();
            primaryStage.setScene(createMainScene());
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) computerSearchButton.getScene().getWindow();
            primaryStage.setScene(searchComputerPage());
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            System.out.println("Already on repair");
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
        //  Text phoneNumber = new Text("Phone Number");
        // phoneNumber.setFont(subtextFont);
        Text nameAndAddress = new Text("Name and Address");
        nameAndAddress.setFont(subtextFont);

        //fields to enter info to search for customer with
        TextField searchField1 = new TextField();

        // Search button made
        Button searchButton = new Button("Search");
        searchButton.setFont(subtextFont);
        searchButton.setOnAction(actionEvent -> {
            String ticketID = searchField1.getText();

            RepairTicket repairTicket = repairticketHandler.getRepairTicket(ticketID);
            if (repairTicket != null) {
                Label resultLabel = new Label("Computer found");
                searchQueue.getChildren().add(resultLabel);
            } else {
                Label resultLabel = new Label("Computer not found");
                searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
        });

        GridPane grid = new GridPane();
        // Add the text fields to the grid
        grid.add(new Label("Repair Ticket ID:"), 0, 1);
        grid.add(searchField1, 1, 1);
        // Set up for center pane
        searchQueue.setAlignment(Pos.TOP_LEFT);
        searchQueue.getChildren().addAll(grid, searchButton);

        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText, searchText, computerSearchButton, repairSearchButton, customerSearchButton);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);
        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        // Create sample data for the customer queue (replace with your actual data)

        Customer customer1 = new Customer("John", "Here", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer2 = new Customer("John Smith", "Somewhere", "12345678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer3 = new Customer("Joe", "Mama", "12345678",
                "(323)555-1234", "my@mail.com", null, null);


        CustomerHandler addCustomer = getCustomerHandler();
        addCustomer.saveCustomer(customer1);
        addCustomer.saveCustomer(customer2);
        addCustomer.saveCustomer(customer3);

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);

        // Add customer blocks to the customer queue
        for (Customer customer : customerList) {
            VBox customerBlock = createCustomerBlock(customer);
            customerQueue.getChildren().add(customerBlock);
        }

        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers = new ArrayList<>();
        ArrayList<RepairTicket> repairTickets = new ArrayList<>();

        // Create sample data for the repair queue (replace with your actual data)
        List<Computer> repairList = new ArrayList<>();
       // repairList.add(new Computer("John", 1));
       // repairList.add(new Computer("John", 2));
       // repairList.add(new Computer("John", 3));
       // repairList.add(new Computer("John", 4));


        /// Add repair blocks to the repair queue
        for (Computer computer : repairList) {
            VBox customerBlock = createRepairBlock(computer);
            repairQueue.getChildren().add(customerBlock);
        }

        return scene;

    }

}
