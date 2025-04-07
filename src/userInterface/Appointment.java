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
import library.AppSettings;
import model.AppointmentModel;
import repo.AppointmentCRUD;

public class Appointment extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // UI Components
        Label lblTitle, lblCustomername, lblAppointmentdate, lblRemarks;
        TextField txtCustomername, txtRemarks;
        DatePicker datePicker;
        Button btnSubmit, btnCancel;

        // Font Setup
        Font titleFont = new Font("Arial", 30);
        Font labelFont = new Font("Arial", 18);
        Font buttonFont = new Font("Arial", 14);

        // Layout Setup
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(subPageWidth);
        primaryStage.setHeight(subPageHeight);
        primaryStage.setTitle("Appointment Management");
        primaryStage.show();

        // Sidebar Setup
        Rectangle sidebar = new Rectangle(0, 0, 250, 800);
        sidebar.setFill(Color.BLACK);
        
        Label lblSidebarTitle = new Label(companyName);
        lblSidebarTitle.setFont(new Font("Arial", 30));
        lblSidebarTitle.setTextFill(Color.WHITE);
        lblSidebarTitle.setMaxWidth(200);
        lblSidebarTitle.relocate(50, 50);
        lblSidebarTitle.setWrapText(true);

        // Form Layout Parameters
        int labelX = 300;
        int inputX = 450;
        int startY = 100;
        int spacingY = 50;
        int fieldWidth = 200;
        int fieldHeight = 30;

        // Form Title
        lblTitle = new Label("Client Appointment");
        lblTitle.setFont(titleFont);
        lblTitle.relocate(labelX, 50);

        // Customer Name Field
        lblCustomername = new Label("Customer Name:");
        lblCustomername.setFont(labelFont);
        lblCustomername.relocate(labelX, startY);
        
        txtCustomername = new TextField();
        txtCustomername.setFont(labelFont);
        txtCustomername.relocate(inputX, startY);
        txtCustomername.setPrefSize(fieldWidth, fieldHeight);

        // Appointment Date Field
        lblAppointmentdate = new Label("Appointment Date:");
        lblAppointmentdate.setFont(labelFont);
        lblAppointmentdate.relocate(labelX, startY + spacingY);
        
        datePicker = new DatePicker();
        datePicker.setStyle("-fx-font-size: 14px;");
        datePicker.relocate(inputX, startY + spacingY);
        datePicker.setPrefSize(fieldWidth, fieldHeight);

        // Remarks Field
        lblRemarks = new Label("Remarks:");
        lblRemarks.setFont(labelFont);
        lblRemarks.relocate(labelX, startY + 2 * spacingY);
        
        txtRemarks = new TextField();
        txtRemarks.setFont(labelFont);
        txtRemarks.relocate(inputX, startY + 2 * spacingY);
        txtRemarks.setPrefSize(fieldWidth, fieldHeight);

        // Buttons Setup
        int btnY = startY + 3 * spacingY + 50;
        int btnWidth = 100;
        int btnHeight = 30;
        int btnSpacing = 110;

        btnSubmit = new Button("Submit");
        btnSubmit.setFont(buttonFont);
        btnSubmit.relocate(labelX, btnY);
        btnSubmit.setPrefSize(btnWidth, btnHeight);
        btnSubmit.setStyle(btnPrimary);
        
        btnCancel = new Button("Cancel");
        btnCancel.setFont(buttonFont);
        btnCancel.relocate(labelX + btnSpacing, btnY);
        btnCancel.setPrefSize(btnWidth, btnHeight);
        btnCancel.setStyle(btnSecondary);

        // Event Handlers
        btnSubmit.setOnAction(event -> handleAppointmentSubmission(
            txtCustomername, 
            datePicker, 
            txtRemarks
        ));

        btnCancel.setOnAction(e -> {
            txtCustomername.clear();
            datePicker.setValue(null);
            txtRemarks.clear();
        });

        // Add all components to pane
        pane.getChildren().addAll(
            sidebar, lblSidebarTitle,
            lblTitle, 
            lblCustomername, txtCustomername,
            lblAppointmentdate, datePicker,
            lblRemarks, txtRemarks,
            btnSubmit, btnCancel
        );
    }

    private void handleAppointmentSubmission(TextField nameField, 
                                          DatePicker datePicker, 
                                          TextField remarksField) {
        try {
            // Validate required fields
            if (nameField.getText().trim().isEmpty() || datePicker.getValue() == null) {
                showAlert(AlertType.WARNING, 
                         "Missing Information", 
                         "Required fields are empty", 
                         "Please fill in customer name and appointment date");
                return;
            }

            // Validate future date
            if (datePicker.getValue().isBefore(LocalDate.now())) {
                showAlert(AlertType.WARNING,
                         "Invalid Date",
                         "Date cannot be in the past",
                         "Please select a current or future date");
                return;
            }

            // Create and save appointment
            AppointmentModel appointment = new AppointmentModel();
            appointment.setCustomerName(nameField.getText().trim());
            appointment.setAppointmentDate(datePicker.getValue());
            appointment.setRemarks(remarksField.getText().trim());

            boolean result = new AppointmentCRUD().insertAppointment(appointment);

            if (result) {
                showAlert(AlertType.INFORMATION,
                         "Success",
                         "Appointment Booked",
                         "Appointment successfully scheduled!");
                nameField.clear();
                datePicker.setValue(null);
                remarksField.clear();
            } else {
                showAlert(AlertType.ERROR,
                         "Error",
                         "Operation Failed",
                         "Could not save the appointment. Please try again.");
            }


        btnCancel = new Button("Cancel");
        btnCancel.relocate(768, 700);
        btnCancel.setStyle(AppSettings.btnSecondary);

        pane.getChildren().add(lblTitle);
        pane.getChildren().addAll(lblCustomername, txtCustomername);
        pane.getChildren().addAll(lblAppointmentdate, datePicker);
        pane.getChildren().addAll(lblRemarks, txtRemarks);
        pane.getChildren().add(btnSubmit);
        pane.getChildren().add(btnCancel);
        pane.getChildren().add(sidebar);
        pane.getChildren().add(lblSidebarTitle);

        } catch (Exception ex) {
            showAlert(AlertType.ERROR,
                     "Error",
                     "An error occurred",
                     ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void showAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}