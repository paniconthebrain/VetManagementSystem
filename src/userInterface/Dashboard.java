package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Font settings for buttons
        Font buttonFont = new Font("Arial", 20);

        // Menu Buttons
        Button btnAppointment = new Button("Appointment");
        btnAppointment.relocate(100, 100);
        btnAppointment.setPrefSize(250, 50);
        btnAppointment.setFont(buttonFont);

        Button btnStaffSetup = new Button("Staff Setup");
        btnStaffSetup.relocate(100, 180);
        btnStaffSetup.setPrefSize(250, 50);
        btnStaffSetup.setFont(buttonFont);

        Button btnClientAppointment = new Button("Client Appointment");
        btnClientAppointment.relocate(100, 260);
        btnClientAppointment.setPrefSize(250, 50);
        btnClientAppointment.setFont(buttonFont);

        Button btnLibrary = new Button("Library");
        btnLibrary.relocate(100, 340);
        btnLibrary.setPrefSize(250, 50);
        btnLibrary.setFont(buttonFont);

        Button btnVeterinary = new Button("Veterinary Management");
        btnVeterinary.relocate(100, 420);
        btnVeterinary.setPrefSize(250, 50);
        btnVeterinary.setFont(buttonFont);

        // Pane and scene
        Pane pane = new Pane();
        pane.getChildren().addAll(btnAppointment, btnStaffSetup, btnClientAppointment, btnLibrary, btnVeterinary);

        Scene scene = new Scene(pane, 800, 600);

        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set button actions to open new pages
        btnAppointment.setOnAction(e -> openAppointmentPage(primaryStage));
        btnStaffSetup.setOnAction(e -> openStaffSetupPage(primaryStage));
        btnClientAppointment.setOnAction(e -> openClientAppointmentPage(primaryStage));
//        btnLibrary.setOnAction(e -> openLibraryPage(primaryStage));
//        btnVeterinary.setOnAction(e -> openVeterinaryPage(primaryStage));
    }

    // Methods to navigate to respective pages

    private void openAppointmentPage(Stage primaryStage) {
        Appointment appointmentPage = new Appointment();
        try {
            appointmentPage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openStaffSetupPage(Stage primaryStage) {
        CreateStaff createStaff= new CreateStaff();
        try {
        	createStaff.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openClientAppointmentPage(Stage primaryStage) {
        Appointment appointmentPage = new Appointment();
        try {
            appointmentPage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
