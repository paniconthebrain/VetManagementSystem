package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.StaffModel;
import repo.StaffCRUD;

public class CreateStaff extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label lblTitle, lblStaffId, lblFullName, lblGender, lblContactNo, lblStaffType;
		TextField txtStaffId, txtFullName, txtContactNo;
		Button btnSubmit, btnDelete, btnUpdate, btnSearch, btnClear, btnClose;
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
		comboStaffType.getItems().addAll("Manager", "Vet Professional", "Staff");
		comboStaffType.relocate(inputX, startY + 4 * spacingY);
		comboStaffType.setPrefSize(200, 30);

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
		btnClear.relocate(labelX, btnY + spacingY);
		btnClear.setPrefSize(100, 30);
		btnClear.setStyle(AppSettings.btnStage2);

		btnClose = new Button("Close");
		btnClose.relocate(labelX + 110, btnY + spacingY);
		btnClose.setPrefSize(100, 30);
		btnClose.setStyle(AppSettings.btnSecondary);

		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!txtStaffId.getText().isEmpty()) {
					try {
						int staffId = Integer.parseInt(txtStaffId.getText());
						StaffCRUD staffCRUD = new StaffCRUD();
						StaffModel staffModel = staffCRUD.search(staffId);

						if (staffModel != null && staffModel.getFullName() != null) {
							txtFullName.setText(staffModel.getFullName());
							txtContactNo.setText(staffModel.getContactNo());
							comboGender.setValue(staffModel.getGender());
							comboStaffType.setValue(staffModel.getStaffType());
						} else {
							showAlert(AlertType.WARNING, "Search Result", "Staff Not Found",
									"Staff Not Found in History");
						}

					} catch (NumberFormatException e) {
						showAlert(AlertType.WARNING, "Invalid Input", "Invalid Staff ID Format", e.getMessage());
					}
				} else {
					showAlert(AlertType.WARNING, "Empty Field", "Staff ID is Required",
							"Please enter a Staff ID to search.");
				}
			}
		});

		btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();

			}
		});

		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!txtStaffId.getText().isEmpty()) {
					try {
						int staffId = Integer.parseInt(txtStaffId.getText());
						StaffCRUD staffCRUD = new StaffCRUD();
						boolean deleted = staffCRUD.Delete(staffId);

						if (deleted) {
							showAlert(AlertType.INFORMATION, "Success", null, "Staff deleted successfully.");
							txtStaffId.clear();
							txtFullName.clear();
							txtContactNo.clear();
							comboGender.setValue(null);
							comboStaffType.setValue(null);
						} else {
							showAlert(AlertType.WARNING, "Failed", null, "Staff ID not found. Deletion failed.");
						}
					} catch (NumberFormatException e) {
						showAlert(AlertType.WARNING, "Invalid Input", null, "Invalid Staff ID format.");
					}
				} else {
					showAlert(AlertType.WARNING, "Empty Field", null, "Staff ID cannot be empty.");
				}
			}
		});

		btnClear.setOnAction(e -> {
			txtStaffId.clear();
			txtFullName.clear();
			txtContactNo.clear();
			comboGender.getSelectionModel().clearSelection();
			comboStaffType.getSelectionModel().clearSelection();
		});

		btnSubmit.setOnAction(e -> {
			try {
				if (txtFullName.getText().trim().isEmpty() || comboGender.getValue() == null
						|| txtContactNo.getText().trim().isEmpty() || comboStaffType.getValue() == null) {
					showAlert(AlertType.WARNING, "Missing Information", "Required fields are empty",
							"Please fill in all fields");
					return;
				}

				StaffModel staffModel = new StaffModel();
				staffModel.setFullName(txtFullName.getText().trim());
				staffModel.setGender(comboGender.getValue());
				staffModel.setContactNo(txtContactNo.getText().trim());
				staffModel.setStaffType(comboStaffType.getValue());

				boolean result = new StaffCRUD().Insert(staffModel);

				if (result) {
					showAlert(AlertType.INFORMATION, "Success", "Staff Inserted", "Staff details have been saved.");
					btnClear.fire();
				} else {
					showAlert(AlertType.ERROR, "Error", "Insertion Failed", "Please try again.");
				}

			} catch (IllegalArgumentException ex) {
				showAlert(AlertType.ERROR, "Validation Error", "Invalid Input", ex.getMessage());
			} catch (Exception ex) {
				showAlert(AlertType.ERROR, "Error", "Unexpected Error", "Please check your input and try again.");
				ex.printStackTrace();
			}
		});

		pane.getChildren().addAll(lblTitle, lblStaffId, txtStaffId, lblFullName, txtFullName, lblGender, comboGender,
				lblContactNo, txtContactNo, lblStaffType, comboStaffType, btnSubmit, btnUpdate, btnDelete, btnSearch,
				sidebar, lblSidebarTitle, btnClear,btnClose);
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
