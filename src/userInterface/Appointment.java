package userInterface;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.AppointmentModel;
import model.OwnerModel;
import repo.AppointmentCRUD;
import repo.OwnerCRUD;

/**
 * JavaFX UI class for managing client appointment registration in the
 * Veterinary Management System.
 */
public class Appointment extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI components
		Label lblTitle, lblOwnerId, lblOwnerName, lblAppointmentdate, lblRemarks;
		TextField txtOwnerId, txtOwnerName, txtRemarks;
		DatePicker datePicker;
		Button btnSubmit, btnCancel, btnSearch;

		// Define font styles
		Font font = new Font("Arial", 25);
		Font font1 = new Font("Arial", 30);

		// Create the root pane and scene
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
		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		// Main title of the form
		lblTitle = new Label("Client Appointment");
		lblTitle.relocate(409, 21);
		lblTitle.setFont(font1);

		// Label and text field for owner ID
		lblOwnerId = new Label("Owner ID");
		lblOwnerId.relocate(451, 150);
		lblOwnerId.setFont(font);

		txtOwnerId = new TextField();
		txtOwnerId.relocate(695, 150);
		txtOwnerId.setPrefSize(200, 40);

		// Search button for owner ID
		btnSearch = new Button("Search");
		btnSearch.relocate(910, 150);
		btnSearch.setStyle(AppSettings.btnSecondary);

		// Label and disabled text field for auto-filled owner name
		lblOwnerName = new Label("Owner Name");
		lblOwnerName.relocate(451, 210);
		lblOwnerName.setFont(font);

		txtOwnerName = new TextField();
		txtOwnerName.setDisable(true); // Make this read-only
		txtOwnerName.relocate(695, 210);
		txtOwnerName.setPrefSize(311, 40);

		// Label and date picker for appointment date
		lblAppointmentdate = new Label("Appointment Date");
		lblAppointmentdate.relocate(451, 280);
		lblAppointmentdate.setFont(font);

		datePicker = new DatePicker();
		datePicker.relocate(695, 280);
		datePicker.setPrefSize(311, 40);

		// Label and text field for remarks
		lblRemarks = new Label("Remarks");
		lblRemarks.relocate(451, 350);
		lblRemarks.setFont(font);

		txtRemarks = new TextField();
		txtRemarks.relocate(695, 350);
		txtRemarks.setPrefSize(311, 40);

		// Submit button and its functionality
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(675, 420);
		btnSubmit.setStyle(AppSettings.btnPrimary);

		btnSubmit.setOnAction(event -> {
			try {
				// Get input values from the form
				int ownerId = Integer.parseInt(txtOwnerId.getText());
				LocalDate appointmentDate = datePicker.getValue();
				String remarks = txtRemarks.getText();

				// Validate owner was fetched
				if (txtOwnerName.getText().isEmpty()) {
					showAlert(Alert.AlertType.WARNING, "Missing Owner", "Please search and select a valid Owner.");
					return;
				}

				// Create AppointmentModel object
				AppointmentModel appointment = new AppointmentModel();
				appointment.setOwnerId(Integer.parseInt(txtOwnerId.getText())); // or setOwnerId if your model supports it				
				appointment.setAppointmentDate(appointmentDate);
				appointment.setRemarks(remarks);

				// Call CRUD insert operation
				AppointmentCRUD appointmentCRUD = new AppointmentCRUD();
				boolean result = appointmentCRUD.insertAppointment(appointment);

				// Show an alert based on the result
				Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(null);
				alert.setContentText(result ? "Appointment successfully inserted!" : "Error inserting appointment.");
				alert.showAndWait();

				// Clear the fields if the insertion is successful
				if (result) {
					txtOwnerId.clear();
					txtOwnerName.clear();
					datePicker.setValue(null);
					txtRemarks.clear();
				}

			} catch (NumberFormatException e) {
				showAlert(Alert.AlertType.ERROR, "Invalid Owner ID", "Please enter a valid number for Owner ID.");
			}
		});

		// Cancel button
		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 420);
		btnCancel.setStyle(AppSettings.btnSecondary);

		// Search button functionality
		btnSearch.setOnAction(event -> {
			try {
				int ownerId = Integer.parseInt(txtOwnerId.getText());
				OwnerModel owner = new OwnerCRUD().searchByID(ownerId); // Replace with getOwnerById if that's your method
				if (owner != null && owner.getOwnerId() != 0) {
					txtOwnerName.setText(owner.getFullName());
				} else {
					txtOwnerName.clear();
					showAlert(Alert.AlertType.WARNING, "Not Found", "No owner found with that ID.");
				}
			} catch (NumberFormatException e) {
				showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid numeric Owner ID.");
			}
		});

		// Add components to the pane
		pane.getChildren().add(lblTitle);
		pane.getChildren().addAll(lblOwnerId, txtOwnerId, btnSearch);
		pane.getChildren().addAll(lblOwnerName, txtOwnerName);
		pane.getChildren().addAll(lblAppointmentdate, datePicker);
		pane.getChildren().addAll(lblRemarks, txtRemarks);
		pane.getChildren().add(btnSubmit);
		pane.getChildren().add(btnCancel);
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);
	}

	// Entry point to launch the application
	public static void main(String[] args) {
		launch(args);
	}

	// Utility method to show alerts
	private void showAlert(Alert.AlertType type, String title, String message) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
