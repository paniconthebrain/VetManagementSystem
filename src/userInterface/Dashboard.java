package userInterface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.Cursor;

public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Font settings for buttons and labels
        Font buttonFont = new Font("Arial", 16);
        Font titleFont = new Font("Arial", 20);

        // Logged-in user's name (replace with actual username)
        String loggedInUser = "";
        Label lblUserName = new Label("Welcome" + loggedInUser);
        lblUserName.setFont(titleFont);
        lblUserName.setTextFill(Color.WHITE);

        // Top Bar (for user name)
        BorderPane topBar = new BorderPane();
        topBar.setStyle("-fx-background-color: #2c3e50;");
        topBar.setPadding(new Insets(10));
        topBar.setRight(lblUserName);
//        topBar.setLeft(lblUserName);
        

        // Sidebar Menu Buttons
        Button btnAppointment = createStyledButton("Appointment", buttonFont);
        Button btnStaffSetup = createStyledButton("Staff Setup", buttonFont);
        Button btnClientAppointment = createStyledButton("Client Appointment", buttonFont);
        Button btnCompanySetup = createStyledButton("Company Setup", buttonFont);
        Button btnUserSetup = createStyledButton("User Setup", buttonFont);

        // Vertical Box for Sidebar
        VBox sidebar = new VBox(10); // 10px spacing between buttons
        sidebar.setStyle("-fx-background-color: #34495e;");
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200); // Set sidebar width
        sidebar.getChildren().addAll(btnCompanySetup,btnStaffSetup, btnUserSetup,btnAppointment,  btnClientAppointment );

        // Main Content Area (Placeholder for now)
        Label lblContent = new Label("Select an option from the sidebar");
        lblContent.setFont(titleFont);
        lblContent.setAlignment(Pos.CENTER);

        // BorderPane Layout
        BorderPane root = new BorderPane();
        root.setTop(topBar); // Top bar with user name
        root.setLeft(sidebar); // Sidebar with menu buttons
        root.setCenter(lblContent); // Main content area



        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Prevent resizing
        primaryStage.show();

        // Set button actions to open new windows
        btnAppointment.setOnAction(e -> openAppointmentWindow());
        btnStaffSetup.setOnAction(e -> openStaffSetupWindow());
        btnClientAppointment.setOnAction(e -> openClientAppointmentWindow());
        btnCompanySetup.setOnAction(e -> openCompanySetup());
        btnUserSetup.setOnAction(e -> openUserSetup());
//        btnVeterinary.setOnAction(e -> openVeterinaryWindow());
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text, Font font) {
        Button button = new Button(text);
        button.setPrefSize(160, 40); // Set button size
        button.setFont(font);
        button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setCursor(Cursor.HAND);
        button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
        button.setOnMouseExited(e -> button.setEffect(null));
        return button;
    }

    // Methods to open new windows with existing pages
    private void openAppointmentWindow() {
        Stage appointmentStage = new Stage();
        appointmentStage.setTitle("Appointment");

        // Initialize the existing Appointment page
        Appointment appointmentPage = new Appointment();
        try {
            appointmentPage.start(appointmentStage); // Start the existing page in the new window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openStaffSetupWindow() {
        Stage staffSetupStage = new Stage();
        staffSetupStage.setTitle("Staff Setup");

        // Initialize the existing StaffSetup page
        CreateStaff staffSetupPage = new CreateStaff();
        try {
            staffSetupPage.start(staffSetupStage); // Start the existing page in the new window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openCompanySetup() {
        Stage companySetupStage = new Stage();
        companySetupStage.setTitle("Company Setup");

        // Initialize the existing Company page
        CompanySetupPage companySetupPage = new CompanySetupPage();
        try {
        	companySetupPage.start(companySetupStage); // Start the existing page in the new window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void openUserSetup() {
        Stage userSetupStage = new Stage();
        userSetupStage.setTitle("User Setup");

        // Initialize the existing Company page
        UserSetup userSetupPage = new UserSetup();
        try {
        	userSetupPage.start(userSetupStage); // Start the existing page in the new window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void openClientAppointmentWindow() {
        Stage clientAppointmentStage = new Stage();
        clientAppointmentStage.setTitle("Client Appointment");

        // Initialize the existing ClientAppointment page
        Appointment clientAppointmentPage = new Appointment();
        try {
            clientAppointmentPage.start(clientAppointmentStage); // Start the existing page in the new window
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}