package userInterface;

import java.time.LocalDate;

import interfaces.AppSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AppointmentModel;
import repo.AppointmentCRUD;

public class Appointment extends Application implements AppSettings {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblTitle, lblCustomername, lblAppointmentdate, lblRemarks;
        TextField txtCustomername, txtRemarks;
        DatePicker datePicker;
        Button btnSubmit, btnCancel;

        Font font = new Font("Arial", 25);
        Font font1 = new Font("Arial", 30);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(1440);
        primaryStage.setHeight(800);

        primaryStage.show();

        // Sidebar Background
        Rectangle sidebar = new Rectangle(0, 0, 250, 800);
        sidebar.setFill(Color.BLACK);

        // Sidebar Labels
        Label lblSidebarTitle = new Label("ABC CLINIC");
        lblSidebarTitle.setFont(new Font("Arial", 24));
        lblSidebarTitle.setTextFill(Color.WHITE);
        lblSidebarTitle.relocate(50, 50);

        lblTitle = new Label("Client Appointment");
        lblTitle.relocate(409, 21);
        lblTitle.setFont(font1);

        lblCustomername = new Label("Customer Name");
        lblCustomername.relocate(451, 180);
        lblCustomername.setFont(font1);

        txtCustomername = new TextField();
        txtCustomername.relocate(695, 180);
        txtCustomername.setPrefSize(311, 40);

        lblAppointmentdate = new Label("Appointment Date");
        lblAppointmentdate.relocate(451, 244);
        lblAppointmentdate.setFont(font);

        datePicker = new DatePicker();
        datePicker.relocate(695, 244);
        datePicker.setPrefSize(311, 40);

        lblRemarks = new Label("Remarks");
        lblRemarks.relocate(451, 550);
        lblRemarks.setFont(font1);

        txtRemarks = new TextField();
        txtRemarks.relocate(695, 550);
        txtRemarks.setPrefSize(311, 40);

        btnSubmit = new Button("Submit");
        btnSubmit.relocate(675, 700);

        btnSubmit.setOnAction(event -> {
            // Get input values from the form
            String customerName = txtCustomername.getText();
            LocalDate appointmentDate = datePicker.getValue();
            String remarks = txtRemarks.getText();

            // Create AppointmentModel object
            AppointmentModel appointment = new AppointmentModel();
            appointment.setCustomerName(customerName);
            appointment.setAppointmentDate(appointmentDate);
            appointment.setRemarks(remarks);

            // Call CRUD insert operation
            AppointmentCRUD appointmentCRUD = new AppointmentCRUD();
            boolean result = appointmentCRUD.insertAppointment(appointment);

            // Show an alert based on the result
            Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
            alert.setTitle(result ? "Success" : "Error");
            alert.setHeaderText(null);
            alert.setContentText(result ? "Appointment successfully inserted!" : "Error inserting appointment.");
            alert.showAndWait();

            // Clear the fields if the insertion is successful
            if (result) {
                txtCustomername.clear();
                datePicker.setValue(null);
                txtRemarks.clear();
            }
        });

        btnCancel = new Button("Cancel");
        btnCancel.relocate(768, 700);
        btnCancel.setStyle(btnSecondary);

        pane.getChildren().add(lblTitle);
        pane.getChildren().addAll(lblCustomername, txtCustomername);
        pane.getChildren().addAll(lblAppointmentdate, datePicker);
        pane.getChildren().addAll(lblRemarks, txtRemarks);
        pane.getChildren().add(btnSubmit);
        pane.getChildren().add(btnCancel);
        pane.getChildren().add(sidebar);
        pane.getChildren().add(lblSidebarTitle);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
