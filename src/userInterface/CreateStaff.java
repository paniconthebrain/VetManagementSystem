package userInterface;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.StaffModel;
import repo.StaffCRUD;

/**
 * JavaFX UI class for registering Staff in the Veterinary Management System.
 */
public class CreateStaff extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI elements
		Label lblTitle, lblStaffId, lblFullName, lblGender, lblContactNo, lblStaffType;
		TextField txtStaffId, txtFullName, txtGender, txtContactNo, txtStaffType;
		Button btnSubmit, btnDelete, btnUpdate, btnSearch, btnClear;

		// ComboBox placeholder (not implemented yet)
		ComboBox<String> comboStaffNames = new ComboBox<>();
		Map<String, Integer> staffType = new HashMap<>(); // NEED TO IMPLEMENT COMBO BOX

		Font font = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font font1 = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);
		primaryStage.show();

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font("Arial", 30));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		// Labels and Text Fields
		lblTitle = new Label("Staff Setup");
		lblTitle.relocate(300, 50);
		lblTitle.setFont(font1);

		int labelX = 300, inputX = 450, startY = 150, spacingY = 50;

		// Staff id Label and TextField
		lblStaffId = new Label("Staff ID:");
		lblStaffId.relocate(labelX, startY);
		lblStaffId.setFont(font);
		txtStaffId = new TextField();
		txtStaffId.relocate(inputX, startY);
		txtStaffId.setPrefSize(200, 30);

		// Staff FullName Label and TextField
		lblFullName = new Label("Full Name:");
		lblFullName.relocate(labelX, startY + spacingY);
		lblFullName.setFont(font);
		txtFullName = new TextField();
		txtFullName.relocate(inputX, startY + spacingY);
		txtFullName.setPrefSize(200, 30);

		// Staff Gender Label and TextField
		lblGender = new Label("Gender:");
		lblGender.relocate(labelX, startY + 2 * spacingY);
		lblGender.setFont(font);
		txtGender = new TextField();
		txtGender.relocate(inputX, startY + 2 * spacingY);
		txtGender.setPrefSize(200, 30);

		lblContactNo = new Label("Contact No:");
		lblContactNo.relocate(labelX, startY + 3 * spacingY);
		lblContactNo.setFont(font);
		txtContactNo = new TextField();
		txtContactNo.relocate(inputX, startY + 3 * spacingY);
		txtContactNo.setPrefSize(200, 30);

		lblStaffType = new Label("Staff Type:");
		lblStaffType.relocate(labelX, startY + 4 * spacingY);
		lblStaffType.setFont(font);
		txtStaffType = new TextField();
		txtStaffType.relocate(inputX, startY + 4 * spacingY);
		txtStaffType.setPrefSize(200, 30);

		// Buttons initialization
		int btnY = startY + 5 * spacingY;
		btnSubmit = new Button("Insert");
		btnSubmit.relocate(labelX, btnY);
		btnSubmit.setPrefSize(100, 30);
		btnSubmit.setStyle(AppSettings.btnPrimary);

		btnUpdate = new Button("Update");
		btnUpdate.relocate(labelX + 110, btnY);
		btnUpdate.setPrefSize(100, 30);
		btnUpdate.setStyle(AppSettings.btnPrimary);

		btnDelete = new Button("Delete");
		btnDelete.relocate(labelX + 220, btnY);
		btnDelete.setPrefSize(100, 30);
		btnDelete.setStyle(AppSettings.btnSecondary);

		btnSearch = new Button("Search");
		btnSearch.relocate(labelX + 330, btnY);
		btnSearch.setPrefSize(100, 30);
		btnSearch.setStyle(AppSettings.btnStage1);

		btnClear = new Button("Clear");
		btnClear.relocate(labelX, 400);
		btnClear.setPrefSize(100, 30);
		btnClear.setStyle(AppSettings.btnStage2);

		// Event handler to clear input fields
		btnClear.setOnAction(e -> {
			txtStaffId.clear();
			txtFullName.clear();
			txtGender.clear();
			txtContactNo.clear();
			txtStaffType.clear();
		});

		// Insert staff into database
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				StaffModel staffModel = new StaffModel();
				staffModel.setFullName(txtFullName.getText());
				staffModel.setGender(txtGender.getText());
				staffModel.setContactNo(txtContactNo.getText());
				staffModel.setStaffType(txtStaffType.getText());

				boolean result = new StaffCRUD().Insert(staffModel);

				Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(result ? "Staff Added Successfully" : "Error Adding Staff");
				alert.setContentText(result ? "Staff details have been added." : "Please try again.");
				alert.showAndWait();

				txtStaffId.clear();
				txtFullName.clear();
				txtGender.clear();
				txtContactNo.clear();
				txtStaffType.clear();
			}
		});
		// Delete staff from database
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					int staffId = Integer.parseInt(txtStaffId.getText());
					boolean result = new StaffCRUD().Delete(staffId);

					Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
					alert.setTitle(result ? "Success" : "Error");
					alert.setHeaderText(result ? "Staff Deleted Successfully" : "Error Deleting Staff");
					alert.setContentText(
							result ? "Staff with ID " + staffId + " has been deleted." : "Please try again.");
					alert.showAndWait();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Staff ID");
					alert.setContentText("Please provide a valid numeric Staff ID.");
					alert.showAndWait();
				}
			}
		});
		// Update staff details
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					int staffId = Integer.parseInt(txtStaffId.getText());
					StaffModel staffModel = new StaffModel();
					staffModel.setStaffId(staffId);
					staffModel.setFullName(txtFullName.getText());
					staffModel.setGender(txtGender.getText());
					staffModel.setContactNo(txtContactNo.getText());
					staffModel.setStaffType(txtStaffType.getText());

					boolean result = new StaffCRUD().Update(staffModel);

					Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
					alert.setTitle(result ? "Success" : "Error");
					alert.setHeaderText(result ? "Staff Updated Successfully" : "Error Updating Staff");
					alert.setContentText(result ? "Staff details have been updated." : "Please try again.");
					alert.showAndWait();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Staff ID");
					alert.setContentText("Please provide a valid numeric Staff ID.");
					alert.showAndWait();
				}
			}
		});
		// Search for a staff by ID
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					int staffId = Integer.parseInt(txtStaffId.getText());
					StaffModel staffModel = new StaffCRUD().search(staffId);

					if (staffModel != null) {
						txtFullName.setText(staffModel.getFullName());
						txtGender.setText(staffModel.getGender());
						txtContactNo.setText(staffModel.getContactNo());
						txtStaffType.setText(staffModel.getStaffType());

						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Staff Found");
						alert.setHeaderText("Staff Details Found");
						alert.setContentText("Staff " + staffModel.getFullName() + " has been found.");
						alert.showAndWait();
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Staff Not Found");
						alert.setContentText("No staff found with ID " + staffId + ".");
						alert.showAndWait();
					}
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Staff ID");
					alert.setContentText("Please provide a valid numeric Staff ID.");
					alert.showAndWait();
				}
			}
		});
		// Another clear handler (duplicate, but enhanced with message)
		btnClear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					// Clear all text fields
					txtStaffId.clear();
					txtFullName.clear();
					txtGender.clear();
					txtContactNo.clear();
					txtStaffType.clear();

					// Show success message
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Form Cleared");
					alert.setContentText("All fields have been cleared successfully.");
					alert.showAndWait();

				} catch (Exception e) {
					// Show error message if clearing fails (unlikely, but just in case)
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Clear Operation Failed");
					alert.setContentText("An error occurred while clearing the form: " + e.getMessage());
					alert.showAndWait();
				}
			}
		});

		// Add all UI elements to the pane
		pane.getChildren().addAll(lblTitle, lblStaffId, txtStaffId, lblFullName, txtFullName, lblGender, txtGender,
				lblContactNo, txtContactNo, lblStaffType, txtStaffType, btnSubmit, btnUpdate, btnDelete, btnSearch,
				sidebar, lblSidebarTitle, btnClear);
	}

	// Entry point of the application
	public static void main(String[] args) {
		launch(args);
	}
}