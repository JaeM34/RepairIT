import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.*;

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
        Label nameLabel = new Label("Name :" + customer.getName());
        Label phoneLabel = new Label("Phone Number: " + customer.getPhone());
        Label emailLabel = new Label("Email: " + customer.getEmail());


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
        customerBlock.getChildren().addAll(nameLabel,phoneLabel,emailLabel,removeButton, checkIn);
        return customerBlock;
    }

    // Helper method to create a repair block
    private VBox createRepairBlock(Customer customer, Computer computer ,RepairTicket repairTicket) {
        VBox repairBlock = new VBox();
        repairBlock.setSpacing(5); // Set spacing between UI components in repair block

        // Create labels to display repair ticket information
       // Label computerLabel = new Label("Name: " +  customer.getName());
        Label computerIDLabel = new Label("TicketID: " + repairTicket.getId());
        Label repairTicketCustomerIDLabel = new Label("Customer ID: " + repairTicket.getCustomerID());
        Label repairComputerIDLabel = new Label("Computer ID: " + repairTicket.getComputerID());
        Label repairTicketIssuesLabel = new Label("Issues: " + repairTicket.GetIssue());
        Label repairTicketStatusLabel = new Label("Status: " + repairTicket.GetStatus());

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(event -> {
            ((Pane) repairBlock.getParent()).getChildren().remove(repairBlock);
        });

        // Create a repair button for the repair ticket
        Button repairButton = new Button("Repair");
        repairButton.setOnAction(event -> {

            Stage primaryStage = (Stage) repairButton.getScene().getWindow();
            primaryStage.setScene(createRepairTicketOverviewPage(repairTicket));
        });

        // Add UI components to repair block
        repairBlock.getChildren().addAll(computerIDLabel,repairTicketCustomerIDLabel,repairComputerIDLabel,repairTicketIssuesLabel,repairTicketStatusLabel, repairButton, removeButton);
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

          // Add UI components and logic for the search scene here
          Button backButton = new Button("Back");
          backButton.setOnAction(event -> {
              Stage primaryStage = (Stage) backButton.getScene().getWindow();
              primaryStage.setScene(createMainScene());
          });
          // sets the size of the back button
          backButton.setPrefWidth(100);
          backButton.setPrefHeight(30);


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
          VBox addComputerBox = new VBox();
          addComputerBox.setAlignment(Pos.CENTER_RIGHT);// Create an HBox for the buttons
          addComputerBox.setTranslateY(100); // Set Y coordinate
          // Add the button to the HBox
          BorderPane hboxBorder = new BorderPane();
          hboxBorder.setCenter(addComputerBox);
          hboxBorder.setStyle("-fx-border-color: black; -fx-border-width: 2px;"); // Set border properties

          // Create a VBox for computer information
          VBox computerInfoBox = new VBox(10);
          computerInfoBox.setPadding(new Insets(10));

          AnchorPane.setTopAnchor(computerInfoBox, 250.0);
          AnchorPane.setLeftAnchor(computerInfoBox, 0.0);
          AnchorPane.setRightAnchor(computerInfoBox, 0.0);
          AnchorPane.setBottomAnchor(computerInfoBox, 0.0);

          // Display computer information
          ArrayList<Computer> computers = customer.getComputers();
          if (computers != null ) {
              Label computerLabel = new Label("Computers:");
              // Add computer information labels to the VBox
              computerInfoBox.getChildren().add(computerLabel);

              for (Computer computer : computers) {
                  HBox computerBox = new HBox(5); // Create a VBox for each computer
                  Label manufacturerLabel = new Label("Manufacturer: " + computer.getManufacturer());
                  Label modelLabel = new Label("Model: " + computer.getModel());
                  Label yearLabel = new Label("Year: " + computer.getYear());
                  Label serialNumberLabel = new Label("Serial Number: " + computer.getSerialNumber());

                  // Set the font size for the computer labels
                  Font computerFont = Font.font("Arial", FontWeight.BOLD, 12); // Replace 12 with the desired font size
                  manufacturerLabel.setFont(computerFont);
                  modelLabel.setFont(computerFont);
                  yearLabel.setFont(computerFont);
                  serialNumberLabel.setFont(computerFont);
                    // Create a Select button for each computer
                  Button selectButton = new Button("Select");
                  selectButton.setOnAction(event -> {
                      // Handle the select button click event here
                      Stage primaryStage = (Stage) selectButton.getScene().getWindow();
                      primaryStage.setScene((createComputerPage(customer, computer)));

                  });
                  // Add computer information labels to the computerBox
                  computerBox.getChildren().addAll(manufacturerLabel, modelLabel, yearLabel, serialNumberLabel, selectButton);
                  // Add computer information labels to the VBox
                  computerInfoBox.getChildren().addAll(computerBox);
              }
          }
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
          anchorPane.getChildren().addAll(hbox,computerInfoBox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox);
          // Places UI elements on top of the rectangle
          stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );

          return scene;
          }
    private Scene createMainScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: white;"); // Replace #F9F9F9 with your desired color
       Scene scene = new Scene(root, 1000,600);
        // Create a StackPane for the rectangle and button
        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: white ;"); // Replace #F9F9F9 with your desired color
        // Create a AnchorPane for UI component placement
        AnchorPane anchorPane = new AnchorPane();

        // Create a rectangle
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.WHITE);

        rectangle.setStroke(Color.WHITE); // Set border color
        rectangle.setStrokeWidth(2); // Set border width

        // Width and height of rectangle relative size to the window
        rectangle.widthProperty().bind(root.widthProperty().subtract(root.getPadding().getLeft() + root.getPadding().getRight()));
        rectangle.heightProperty().bind(root.heightProperty().subtract(root.getPadding().getTop() + root.getPadding().getBottom()));
        // Adds rectangle as the base layer
        stackPane.getChildren().addAll(rectangle);

        // Create nodes for the 3 queues
        Text customerText = new Text("Customer Queue");
        Font textFont = Font.font("Arial", FontWeight.BOLD, 20); // Replace with desired font, weight, and size
        customerText.setFont(textFont);

        Text repairText = new Text("Repair Queue");
        repairText.setFont(textFont);

        Text searchText = new Text("Search - Customer");
        searchText.setFont(textFont);

        //fields to enter info to search for customer with
        TextField searchField1 = new TextField();
        TextField searchField2 = new TextField();

        // Text for center pane
        Font subtextFont = Font.font("Arial", FontWeight.NORMAL, 12);

        // Create a VBox for the customer queue
        VBox customerQueue = new VBox();
        customerQueue.setSpacing(10); // Set spacing between customer blocks
        // Create a VBox for the repair queue
        VBox repairQueue = new VBox();
        repairQueue.setSpacing(10); // Set spacing between repair blocks
        // Create a VBox for the search queue
        VBox searchQueue = new VBox();
        searchQueue.setSpacing(10); // Set spacing between repair blocks
        ScrollPane searchScrollPane = new ScrollPane(searchQueue);

       // Search buttons made for each operation possible
        Button cuSearchButton = new Button("Search");
        cuSearchButton.setFont(subtextFont);
        cuSearchButton.setOnAction(actionEvent -> {
            String name = searchField1.getText();
            String address = searchField2.getText();

            Customer customer = customerHandler.getCustomerByNameAndAddress(name, address);
            if (customer.getName() != null && !name.isEmpty() && !address.isEmpty()) {
                Label resultLabel = new Label("Customer Found:\nName: " + customer.getName() + "\nPhone: " + customer.getPhone() + "\nAddress: " + customer.getAddress() + "\nEmail: " + customer.getEmail());
                searchQueue.getChildren().add(resultLabel);
            } else {
                // Create the pop-up window
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setTitle("Add Customer");

                // Create text fields for input
                TextField nameField = new TextField();
                TextField addressField = new TextField();
                TextField customerIDField = new TextField();
                TextField phoneField = new TextField();
                TextField emailField = new TextField();

                // Create a button to save the customer
                Button saveButton = new Button("Save");
                saveButton.setOnAction(saveEvent -> {

                    CustomerHandler addCustomers = new CustomerHandler();

                    // Retrieve the input values
                    String namefield = nameField.getText();
                    String address1 = addressField.getText();
                    String customerID = customerIDField.getText();
                    String phone = phoneField.getText();
                    String email = emailField.getText();

                    // Create the customer
                    Customer customer1 = new Customer(namefield, address1, customerID, phone, email, null, null);
                    addCustomers.saveCustomer(customer1);
                    // Close the pop-up window
                    popupStage.close();
                });
                // Create a layout for the pop-up window
                VBox layout = new VBox(10);
                layout.getChildren().addAll(
                        new Label("Name:"),
                        nameField,
                        new Label("Address:"),
                        addressField,
                        new Label("Customer ID:"),
                        customerIDField,
                        new Label("Phone:"),
                        phoneField,
                        new Label("Email:"),
                        emailField,
                        saveButton
                );
                layout.setAlignment(Pos.CENTER);
                // Set the layout for the pop-up window
                Scene popupScene = new Scene(layout, 700, 400);
                popupStage.setScene(popupScene);
                popupStage.showAndWait();

                //Label resultLabel = new Label("Customer Not Found");
               // searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
            searchField2.clear();
        });

        Button coSearchButton = new Button("Search");
        coSearchButton.setFont(subtextFont);
        coSearchButton.setOnAction(actionEvent -> {
            String serial = searchField2.getText();

            Computer computer = computerHandler.getComputerBySerialNumber(serial);
            if (computer.getSerialNumber() != null && !serial.isEmpty()) {
                Label resultLabel = new Label("Computer Found:\nComputer: " + computer.getComputerID() + "\nSerial Number: " + computer.getSerialNumber() + "\nModel: " + computer.getModel() + "\nYear: " + computer.getYear());
                searchQueue.getChildren().add(resultLabel);
            } else {
                Label resultLabel = new Label("Computer not found");
                searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
            searchField2.clear();
        });

        Button rSearchButton = new Button("Search");
        rSearchButton.setFont(subtextFont);
        rSearchButton.setOnAction(actionEvent -> {
            String ticketID = searchField1.getText();

            RepairTicket repairTicket = repairticketHandler.getRepairTicket(ticketID);
            if (repairTicket.getId() != null && !ticketID.isEmpty()) {
                Label resultLabel = new Label("Ticket Found:\nTicket: " + repairTicket.getId() + "\nIssue: " + repairTicket.GetIssue() + "\nStatus: " + repairTicket.GetStatus());
                searchQueue.getChildren().add(resultLabel);
            } else {
                Label resultLabel = new Label("Ticket not found");
                searchQueue.getChildren().add(resultLabel);
            }
            // Clear fields
            searchField1.clear();
        });

        GridPane grid = new GridPane();
        // Add the text fields to the grid
        grid.add(new Label("Name:"), 0, 1);
        grid.add(searchField1, 1, 1);
        // grid.add(new Label("Address:"), 0, 2);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(searchField2, 1, 2);

        // Search Customer button made
        Button customerSearchButton = new Button("Search Customer");
        Font font = Font.font("Arial", FontWeight.BOLD, 16);
        customerSearchButton.setFont(font);
        customerSearchButton.setOnAction(actionEvent -> {
            //clear scrollPane and set to settings for said button
            searchQueue.getChildren().clear();
            searchText.setText("Search - Customer");

            // Add the text fields to the grid
            GridPane newGrid = new GridPane();
            newGrid.add(new Label("Name:"), 0, 1);
            newGrid.add(searchField1, 1, 1);
            // grid.add(new Label("Address:"), 0, 2);
            newGrid.add(new Label("Address:"), 0, 2);
            newGrid.add(searchField2, 1, 2);

            // new center pane elements added
            searchQueue.getChildren().addAll(newGrid, cuSearchButton);
        });

        // Search Computer button made
        Button computerSearchButton = new Button("Search Computer");
        computerSearchButton.setFont(font);
        computerSearchButton.setOnAction(actionEvent -> {
            searchQueue.getChildren().clear();
            searchText.setText("Search - Computer");
            // Add the text fields to the grid
            GridPane newGrid = new GridPane();
            newGrid.add(new Label("Serial Number:"), 0, 2);
            newGrid.add(searchField2, 1, 2);

            // new center pane elements added
            searchQueue.getChildren().addAll(newGrid, coSearchButton);
        });

        // Search RepairTicket button made
        Button repairSearchButton = new Button("Search RepairTicket");
        repairSearchButton.setFont(font);
        repairSearchButton.setOnAction(actionEvent -> {
            searchQueue.getChildren().clear();
            searchText.setText("Search - Repair Ticket");
            GridPane newGrid = new GridPane();
            // Add the text fields to the grid
            newGrid.add(new Label("Repair Ticket ID:"), 0, 1);
            newGrid.add(searchField1, 1, 1);

            // new center pane elements added
            searchQueue.getChildren().addAll(newGrid, rSearchButton);
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
        searchScrollPane.prefWidthProperty().bind(rectangle.widthProperty().multiply(0.3));
        searchScrollPane.prefHeightProperty().bind(rectangle.heightProperty().multiply(0.65));
        // Formats the Search Queue
        AnchorPane.setTopAnchor(searchScrollPane, 100.0); // Set top anchor for repairScrollPane
        AnchorPane.setRightAnchor(searchScrollPane, 340.0); // Set right anchor for repairScrollPane

        //  Text phoneNumber = new Text("Phone Number");
        // phoneNumber.setFont(subtextFont);
        Text nameAndAddress = new Text("Name and Address");
        nameAndAddress.setFont(subtextFont);

        // Set up for center pane
        searchQueue.setAlignment(Pos.TOP_LEFT);

        searchQueue.getChildren().addAll(grid, cuSearchButton);

        stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));
        // Create sample data for the customer queue (replace with your actual data)
        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = new Customer("John", "Here", "1234568",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer2 = new Customer("John Smith", "Somewhere", "1235678",
                "(323)555-1234", "my@mail.com", null, null);
        Customer customer3 = new Customer("Joe", "Mama", "123456",
                "(323)555-1234", "my@mail.com", null, null);

        ComputerHandler addComputer = getComputerHandler();
        Computer computer = new Computer("123","1234568", "manufacturer", "Dell","Serial number" , 2023 );
        Computer computer1 = new Computer("321","1234568", "manufacturer", "Dell","Serial number" , 2023 );
        Computer computer2 = new Computer("789","1234568", "manufacturer", "Dell","Serial number" , 2023);
        Computer computer3 = new Computer("12345","1235678", "manufacturer", "HP","Serial number" , 2023 );
        Computer computer4 = new Computer("67890","1235678", "manufacturer", "HP","Serial number" , 2023);
        Computer computer5 = new Computer("111213","1235678", "manufacturer", "HP","Serial number" , 2023 );
        Computer computer6 = new Computer("212223","1234567", "manufacturer", "APPLE","Serial number" , 2023  );
        Computer computer7 = new Computer("232526","1234567", "manufacturer", "LENOVO","Serial number" , 2023 );
        Computer computer8 = new Computer("272829","1234567", "manufacturer", "SAMSUNG","Serial number" , 2023 );

        RepairTicketHandler addRepairTicket = new RepairTicketHandler();
        RepairTicket ticket1 = new RepairTicket("123","CustomerID","ComputerID", "Issues", "Status");
        RepairTicket ticket2 = new RepairTicket("321","CustomerID","ComputerID", "Issue", "Status");
        RepairTicket ticket3 = new RepairTicket("6433","CustomerID","ComputerID", "Issue", "Status");
        RepairTicket ticket4 = new RepairTicket("4214","CustomerID","ComputerID", "Issue", "Status");
        RepairTicket ticket5 = new RepairTicket("64344","CustomerID","ComputerID", "Issue", "Status");
        RepairTicket ticket6 = new RepairTicket("4241513","CustomerID","ComputerID", "Issue", "Status");


        // not working currently
        addRepairTicket.saveRepairTicket(ticket1);
        addRepairTicket.saveRepairTicket(ticket2);
        addRepairTicket.saveRepairTicket(ticket3);
        addRepairTicket.saveRepairTicket(ticket4);
        addRepairTicket.saveRepairTicket(ticket5);
        addRepairTicket.saveRepairTicket(ticket6);

     //   addRepairTicket.getRepairTicketsOnComputer("123");
      //  addRepairTicket.getRepairTicketsOnComputer("321");
        //addRepairTicket.getRepairTicketsOnComputer("6433");
      //  addRepairTicket.getRepairTicketsOnComputer("4214");


        ArrayList<RepairTicket> repairTickets = new ArrayList<>();
         repairTickets.add(0,ticket1);
        repairTickets.add(1,ticket2);
        repairTickets.add(2,ticket3);

        ArrayList<RepairTicket> repairTickets1 = new ArrayList<>();
        repairTickets1.add(0,ticket2);

        ArrayList<RepairTicket> repairTickets2 = new ArrayList<>();
        repairTickets2.add(0,ticket3);

        customer1.setRepairTickets(repairTickets);
        customer2.setRepairTickets(repairTickets1);
        customer3.setRepairTickets(repairTickets2);


        computer.setRepairTickets(repairTickets);
        computer2.setRepairTickets(repairTickets);
        computer3.setRepairTickets(repairTickets);
        computer4.setRepairTickets(repairTickets);
        computer5.setRepairTickets(repairTickets);
        computer6.setRepairTickets(repairTickets);
        computer7.setRepairTickets(repairTickets);
        computer8.setRepairTickets(repairTickets);
        computer1.setRepairTickets(repairTickets);

        // Addss computer to the database
        addComputer.saveComputer(computer);
        addComputer.saveComputer(computer1);
        addComputer.saveComputer(computer2);
        addComputer.saveComputer(computer3);
        addComputer.saveComputer(computer4);
        addComputer.saveComputer(computer5);
        addComputer.saveComputer(computer6);
        addComputer.saveComputer(computer7);
        addComputer.saveComputer(computer8); // Addss computer to the database

        customer1 = getCustomerHandler().getCustomerByID(customer1.getCustomerID());
        customer2 = getCustomerHandler().getCustomerByID(customer2.getCustomerID());
        customer3 = getCustomerHandler().getCustomerByID(customer3.getCustomerID());


        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers = new ArrayList<>();
        computers.add(0, computer);
        computers.add(1, computer1);
        computers.add(2, computer2);

        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers1 = new ArrayList<>();
        computers1.add(0, computer2);
        computers1.add(1, computer3);
        computers1.add(2, computer4);

        // Create empty lists for computers and repair tickets
        ArrayList<Computer> computers2 = new ArrayList<>();
        computers2.add(0, computer3);
        computers2.add(1, computer4);
        computers2.add(2, computer5);

        customer1.setComputers(computers1);
        customer2.setComputers(computers2);
        customer3.setComputers(computers);

        customer1.setRepairTickets(repairTickets);
        computer2.setRepairTickets(repairTickets);
        computer3.setRepairTickets(repairTickets);

        computer.setRepairTickets(repairTickets);

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

          // Create sample data for the repair queue (replace with your actual data)
        List<RepairTicket> repairList = new ArrayList<>();
        repairList.add(ticket1);
        repairList.add(ticket2);
        repairList.add(ticket3);
        repairList.add(ticket4);
        repairList.add(ticket5);
        repairList.add(ticket6);

        /// Add repair blocks to the repair queue
        for (RepairTicket repairTicket1 : repairList) {
            VBox customerBlock = createRepairBlock(customer1, computer, repairTicket1);
            repairQueue.getChildren().add(customerBlock);
        }


        // Adds UI elements to scene
        anchorPane.getChildren().addAll(customerScrollPane, repairScrollPane, searchScrollPane, customerText, repairText, searchText, computerSearchButton, repairSearchButton, customerSearchButton); // Add all the elements to the root group// Add all the elements to the root group
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane, repairSearchButton, computerSearchButton, customerSearchButton);

        return scene;
    }
    private Scene createAddComputerPage(Customer customer) {  // temporary windows for each page

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: white;"); // Replace #F9F9F9 with your desired color

        // Text input boxes for computer details
        TextField computerIdInput = new TextField();
        TextField manufacturerInput = new TextField();
        TextField modelInput = new TextField();
        TextField serialNumberInput = new TextField();
        TextField yearInput = new TextField();

        // Create a dialog box to input computer details
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Computer");
        dialog.setHeaderText("Enter computer details:");

        // Set the dialog content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.addRow(0, new Label("Computer ID:"), computerIdInput);
        grid.addRow(1, new Label("Manufacturer:"), manufacturerInput);
        grid.addRow(2, new Label("Model:"), modelInput);
        grid.addRow(3, new Label("Serial Number:"), serialNumberInput);
        grid.addRow(4, new Label("Year:"), yearInput);
        dialog.getDialogPane().setContent(grid);

        // Add buttons to the dialog
        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        // Wait for the user to click a button
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == addButton) {
            // Get the entered values
            String computerId = computerIdInput.getText();
            String manufacturer = manufacturerInput.getText();
            String model = modelInput.getText();
            String serialNumber = serialNumberInput.getText();
            int year = Integer.parseInt(yearInput.getText());

            ComputerHandler addComputer = new ComputerHandler();
            // Create a computer object with the input details
            Computer newComputer = new Computer(computerId, customer.getCustomerID(), manufacturer, model, serialNumber, year);

            //add the new computer to the customer
            customer.getComputers().add(newComputer);
            addComputer.saveComputer(newComputer);
            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Computer '" + newComputer.getComputerID() + "' added to '" + customer.getName() + "'.");
            alert.showAndWait();
        }
            // Create a giant "Back" button
            Button giantBackButton = new Button("Back");
            giantBackButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            giantBackButton.setOnAction(event -> {
                Scene createCustomerScene = createCustomerScene(customer);
                Stage primaryStage = (Stage) giantBackButton.getScene().getWindow();
                primaryStage.setScene(createCustomerScene);
            });

            // Set the giant "Back" button at the center of the root pane
            root.setCenter(giantBackButton);

        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    private Scene createComputerPage(Customer customer, Computer computer) {
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

        Button addTicketButton = new Button("Add Ticket");
        addTicketButton.setFont(font);
        addTicketButton.setOnAction(actionEvent -> {
            Stage primaryStage = (Stage) addTicketButton.getScene().getWindow();
            primaryStage.setScene(createCreateRepairTicketPage(customer, computer));
        });

        // sets the size of the add ticket button
        addTicketButton.setPrefWidth(200);
        addTicketButton.setPrefHeight(30);

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


        // Display customer information horizontally
        AnchorPane.setTopAnchor(hbox, 70.0);
        AnchorPane.setLeftAnchor(hbox, 0.0);
        AnchorPane.setRightAnchor(hbox, 0.0);
        AnchorPane.setBottomAnchor(hbox, 400.0);

        Label nameLabel = new Label("Name: " + customer.getName());
        Label phoneLabel = new Label("Phone: " + customer.getPhone());
        Label emailLabel = new Label("Email: " + customer.getEmail());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 13); // Specify the font family, weight, and size
        nameLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);

        hbox.getChildren().addAll(nameLabel, phoneLabel, emailLabel);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

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

        // Display selected computer information
        if (computer != null) {
            //VBox computerInfoBox = new VBox(10); // Create a VBox to hold the computer information
            hbox.setPadding(new Insets(20));
            Label manufacturerLabel = new Label("Manufacturer: " + computer.getManufacturer());
            Label modelLabel = new Label("Model: " + computer.getModel());
            Label yearLabel = new Label("Year: " + computer.getYear());
            Label serialNumberLabel = new Label("Serial Number: " + computer.getSerialNumber());

            manufacturerLabel.setFont(labelFont);
            modelLabel.setFont(labelFont);
            yearLabel.setFont(labelFont);
            serialNumberLabel.setFont(labelFont);
            // Add the computer information labels to the VBox
            hbox.getChildren().addAll( manufacturerLabel, modelLabel, yearLabel, serialNumberLabel);
        }


        // Create a VBox for computer information
        VBox computerInfoBox = new VBox(10);
        computerInfoBox.setPadding(new Insets(10));

        AnchorPane.setTopAnchor(computerInfoBox, 250.0);
        AnchorPane.setLeftAnchor(computerInfoBox, 0.0);
        AnchorPane.setRightAnchor(computerInfoBox, 0.0);
        AnchorPane.setBottomAnchor(computerInfoBox, 0.0);

        // Display RepairTicket information
        ArrayList<RepairTicket> repairTickets = computer.getRepairTickets();

        if (repairTickets != null && !repairTickets.isEmpty() ) {
            Label computerLabel = new Label("Repair Tickets:");
            // Add computer information labels to the VBox
            computerInfoBox.getChildren().add(computerLabel);
            // }
            for (RepairTicket repairTickets1 : repairTickets) {
                HBox computerBox = new HBox(5); // Create a VBox for each computer
                // With the corrected lines
                Label manufacturerLabel = new Label("ID: " + repairTickets1.getId());
                Label modelLabel = new Label("Issue: " + repairTickets1.GetIssue());
               // Label serialNumberLabel = new Label("Computer ID: " + repairTickets1.getComputerID());
                // Set the font size for the computer labels
                Font computerFont = Font.font("Arial", FontWeight.BOLD, 12); // Replace 12 with the desired font size
                manufacturerLabel.setFont(computerFont);
                modelLabel.setFont(computerFont);
                // Create a Select button for each computer
                Button selectButton = new Button("Edit");
                selectButton.setOnAction(event -> {
                    // Handle the select button click event here
                     Stage primaryStage = (Stage) selectButton.getScene().getWindow();
                     primaryStage.setScene((createEditRepairTicketScene( customer, computer, repairTickets1)));

                });
                // Add computer information labels to the computerBox
                computerBox.getChildren().addAll(manufacturerLabel, modelLabel, selectButton);
                // Add computer information labels to the VBox
                computerInfoBox.getChildren().addAll(computerBox);
            }

        }
            // sets location of the back button
            root.setTop(backButton);
            AnchorPane.setTopAnchor(backButton, 10.0);
            AnchorPane.setLeftAnchor(backButton, 10.0);
            AnchorPane.setTopAnchor(addComputerBox, 10.0);

            // sets location of the back button
            root.setTop(addTicketButton);
            AnchorPane.setTopAnchor(addTicketButton, 200.0);
            AnchorPane.setRightAnchor(addTicketButton, 10.0);

            stackPane.setAlignment(anchorPane, Pos.TOP_LEFT);
            stackPane.setAlignment(repairSearchButton, Pos.TOP_RIGHT);
            stackPane.setAlignment(computerSearchButton, Pos.TOP_CENTER);
            stackPane.setAlignment(customerSearchButton, Pos.TOP_CENTER);
            stackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
            stackPane.setAlignment(addTicketButton, Pos.CENTER_RIGHT);
            //stackPane.setAlignment(addCustomerButton, Pos.BOTTOM_CENTER);
            // stackPane.setAlignment(addComputerButton, Pos.BOTTOM_RIGHT);
            stackPane.setMargin(customerSearchButton, new Insets(0, 0, 0, 392));

            // Adds UI elements to scene
            anchorPane.getChildren().addAll(addTicketButton, hbox, computerSearchButton, repairSearchButton, customerSearchButton, backButton, hboxBorder, addComputerBox, computerInfoBox);
            // Places UI elements on top of the rectangle
            stackPane.getChildren().addAll(anchorPane, computerSearchButton, repairSearchButton, customerSearchButton);
        return scene;
        }

        private Scene createCreateRepairTicketPage(Customer customer, Computer computer){  // temporary windows for each page
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: white;"); // Replace #F9F9F9 with your desired color
            // Text input box for repair ticket issues
            TextArea repairTicketIssues = new TextArea();

            // Create a dialog box to input repair ticket details
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Create Repair Ticket");
            dialog.setHeaderText("Enter the issue:");

            // Set the dialog content
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.addRow(0, new Label("Repair Ticket Issues:"), repairTicketIssues);
            dialog.getDialogPane().setContent(grid);

            // Add buttons to the dialog
            ButtonType addButton = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

            // Wait for the user to click a button
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == addButton) {
                // Get the entered values
                String issues = repairTicketIssues.getText();

                RepairTicketHandler addRepairTicket = new RepairTicketHandler();
                // Create a repair ticket object with the input details
                RepairTicket newRepairTicket = new RepairTicket();
                newRepairTicket.EditIssue(issues);
                // Add the new repair ticket to the computer
                computer.addRepairTicket(newRepairTicket);
                addRepairTicket.saveRepairTicket(newRepairTicket);

                // Show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Repair Ticket added to the computer.");
                alert.showAndWait();
            }
        // Create a giant "Back" button
        Button giantBackButton = new Button("Back");
        giantBackButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        giantBackButton.setOnAction(event -> {
            Stage primaryStage = (Stage) giantBackButton.getScene().getWindow();
            primaryStage.setScene(createComputerPage(customer, computer));
        });
        // Set the giant "Back" button at the center of the root pane
        root.setCenter(giantBackButton);

        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    private Scene createRepairTicketOverviewPage(RepairTicket repairTicket){     // temporary windows for each page
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

          Label IDLabel = new Label("ID: " + repairTicket.getId());
         Label IssuesLabel = new Label("Issues: " + repairTicket.GetIssue());
         Label computerIDLabel = new Label("Computer ID: " + repairTicket.getComputerID());
        Label emailLabel = new Label("Computer ID: " + repairTicket.getCustomerID());

        Font labelFont = Font.font("Arial", FontWeight.BOLD, 14); // Specify the font family, weight, and size
          IDLabel.setFont(labelFont);
         IssuesLabel.setFont(labelFont);
         computerIDLabel.setFont(labelFont);
         emailLabel.setFont(labelFont);


          hbox.getChildren().addAll(IDLabel, IssuesLabel, computerIDLabel, emailLabel);

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

        // Create a VBox for computer information
        VBox repairTicketInfoBox = new VBox(10);
        repairTicketInfoBox.setPadding(new Insets(10));

        AnchorPane.setTopAnchor(repairTicketInfoBox, 250.0);
        AnchorPane.setLeftAnchor(repairTicketInfoBox, 0.0);
        AnchorPane.setRightAnchor(repairTicketInfoBox, 0.0);
        AnchorPane.setBottomAnchor(repairTicketInfoBox, 0.0);

        repairTicketInfoBox.getChildren().addAll(IDLabel, IssuesLabel);


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
        anchorPane.getChildren().addAll(hbox,computerSearchButton, repairSearchButton, customerSearchButton,backButton,hboxBorder,addComputerBox, repairTicketInfoBox);
        // Places UI elements on top of the rectangle
        stackPane.getChildren().addAll(anchorPane,computerSearchButton, repairSearchButton, customerSearchButton );

        return scene;
    }

    private Scene createEditRepairTicketScene(Customer customer, Computer computer, RepairTicket repairTicket) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 1000, 600);

        // Add UI components and logic for the edit repair ticket scene here

        // Example code for displaying computer information
        Label computerInfoLabel = new Label("Computer Information:");
        Label manufacturerLabel = new Label("Manufacturer: " + computer.getManufacturer());
        Label modelLabel = new Label("Model: " + computer.getModel());
        Label yearLabel = new Label("Year: " + computer.getYear());
        Label serialNumberLabel = new Label("Serial Number: " + computer.getSerialNumber());

        // Example code for displaying repair ticket information
        Label idLabel = new Label("ID: " + repairTicket.getId());
        Label issueLabel = new Label("Issue: " + repairTicket.GetIssue());

        // Example code for editing the repair ticket issue
        TextField issueTextField = new TextField(repairTicket.GetIssue());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            // Handle the save button click event here
            String updatedIssue = issueTextField.getText();
            repairTicket.EditIssue(updatedIssue);
            // You can add further logic here to save the updated issue

            // Return to the previous scene after saving
            Stage primaryStage = (Stage) saveButton.getScene().getWindow();
            primaryStage.setScene(createComputerPage(customer, computer));
        });

        // Create a VBox for computer information
        VBox computerInfoBox = new VBox(10);
        computerInfoBox.setAlignment(Pos.CENTER);
        computerInfoBox.getChildren().addAll(computerInfoLabel, manufacturerLabel, modelLabel, yearLabel, serialNumberLabel);

        // Add UI elements to the root BorderPane
        VBox contentBox = new VBox(10);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(computerInfoBox, idLabel, issueLabel, issueTextField, saveButton);
        root.setCenter(contentBox);

        return scene;
    }


}
