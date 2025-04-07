package userInterface;

import java.util.HashMap;
import java.util.Map;

import interfaces.AppSettings;

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

public class CreateStaff extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblTitle, lblStaffId, lblFullName, lblGender, lblContactNo, lblStaffType;
		TextField txtStaffId, txtFullName, txtContactNo;
		Button btnSubmit, btnDelete, btnUpdate, btnSearch, btnClear;

		ComboBox<String> comboGender, comboStaffType;

		Font font = new Font("Arial", 18);
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
		lblTitle.setFont(font);

		int labelX = 300, inputX = 450, startY = 100, spacingY = 50;

		lblStaffId = new Label("Staff ID:");
		lblStaffId.relocate(labelX, startY);
		lblStaffId.setFont(font);
		txtStaffId = new TextField();
		txtStaffId.relocate(inputX, startY);
		txtStaffId.setPrefSize(200, 30);

		lblFullName = new Label("Full Name:");
		lblFullName.relocate(labelX, startY + spacingY);
		lblFullName.setFont(font);
		txtFullName = new TextField();
		txtFullName.relocate(inputX, startY + spacingY);
		txtFullName.setPrefSize(200, 30);

		lblGender = new Label("Gender:");
		lblGender.relocate(labelX, startY + 2 * spacingY);
		lblGender.setFont(font);
		comboGender = new ComboBox<>();
		comboGender.getItems().addAll("Male", "Female", "Other");
		comboGender.relocate(inputX, startY + 2 * spacingY);
		comboGender.setPrefSize(200, 30);

		lblContactNo = new Label("Contact No:");
		lblContactNo.relocate(labelX, startY + 3 * spacingY);
		lblContactNo.setFont(font);
		txtContactNo = new TextField();
		txtContactNo.relocate(inputX, startY + 3 * spacingY);
		txtContactNo.setPrefSize(200, 30);

		// Add input validation for contact number
		txtContactNo.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				txtContactNo.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if (newValue.length() > 10) {
				txtContactNo.setText(oldValue);
			}
		});

		lblStaffType = new Label("Staff Type:");
		lblStaffType.relocate(labelX, startY + 4 * spacingY);
		lblStaffType.setFont(font);
		comboStaffType = new ComboBox<>();
		comboStaffType.getItems().addAll("Manager", "Technician", "Assistant");
		comboStaffType.relocate(inputX, startY + 4 * spacingY);
		comboStaffType.setPrefSize(200, 30);

		// Buttons
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


		btnSubmit.setStyle(btnPrimary);
		btnUpdate = new Button("Update");
		btnUpdate.relocate(labelX + 110, btnY);
		btnUpdate.setPrefSize(100, 30);
		btnUpdate.setStyle(btnPrimary);
		btnDelete = new Button("Delete");
		btnDelete.relocate(labelX + 220, btnY);
		btnDelete.setPrefSize(100, 30);
		btnDelete.setStyle(btnSecondary);
		btnSearch = new Button("Search");
		btnSearch.relocate(labelX + 330, btnY);
		btnSearch.setPrefSize(100, 30);
		btnSearch.setStyle(btnStage1);

		btnClear = new Button("Clear");
		btnClear.relocate(labelX, btnY + spacingY);
		btnClear.setPrefSize(100, 30);
		btnClear.setStyle(AppSettings.btnStage2);

		// Event Handlers for Buttons
		btnClear.setOnAction(e -> {
			txtStaffId.clear();
			txtFullName.clear();
			comboGender.getSelectionModel().clearSelection();
			txtContactNo.clear();
			comboStaffType.getSelectionModel().clearSelection();
		});

		btnSubmit.setOnAction(e -> {
			try {
				// First check if required fields are empty
				if (txtFullName.getText().trim().isEmpty() || comboGender.getValue() == null
						|| txtContactNo.getText().trim().isEmpty() || comboStaffType.getValue() == null) {

					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Missing Information");
					alert.setHeaderText("Required fields are empty");
					alert.setContentText("Please fill in all fields");
					alert.showAndWait();
					return;
				}

				// Create and validate staff model
				StaffModel staffModel = new StaffModel();
				staffModel.setFullName(txtFullName.getText().trim());
				staffModel.setGender(comboGender.getValue());
				staffModel.setContactNo(txtContactNo.getText().trim());
				staffModel.setStaffType(comboStaffType.getValue());

				// Insert into database
				boolean result = new StaffCRUD().Insert(staffModel);

				Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(result ? "Staff Inserted Successfully" : "Error Inserting Staff");
				alert.setContentText(result ? "Staff details have been saved." : "Please try again.");
				alert.showAndWait();

				// Clear fields after successful insertion
				if (result) {
					btnClear.fire();
				}

			} catch (IllegalArgumentException ex) {
				// Handle validation errors from StaffModel
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Validation Error");
				alert.setHeaderText("Invalid Input");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
			} catch (Exception ex) {
				// Handle other exceptions
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("An error occurred");
				alert.setContentText("Please check your input and try again.");
				alert.showAndWait();
				ex.printStackTrace();
			}
		});

		// Add elements to the pane
		pane.getChildren().addAll(lblTitle, lblStaffId, txtStaffId, lblFullName, txtFullName, lblGender, comboGender,
				lblContactNo, txtContactNo, lblStaffType, comboStaffType, btnSubmit, btnUpdate, btnDelete, btnSearch,
				sidebar, lblSidebarTitle, btnClear);
	}

	public static void main(String[] args) {
		launch(args);
	}
}