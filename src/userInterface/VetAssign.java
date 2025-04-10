package userInterface;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.OwnerModel;
import model.StaffModel;
import model.VetAssignmentModel;
import repo.OwnerCRUD;
import repo.StaffCRUD;
import repo.VetAssignmentCRUD;

/**
 * JavaFX application for assigning a veterinary staff to a pet owner. The form
 * allows searching owner details by ID, selecting a staff from dropdown, adding
 * remarks, and saving the assignment to the database.
 */
public class VetAssign extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI components
		Label lblTitle, lblOwnerId, lblOwnername, lblContactNo, lblAddress, lblStaff, lblRemarks;
		TextField txtOwnerId, txtOwnername, txtContactNo, txtAddress, txtRemarks;
		Button btnSearch, btnSubmit, btnCancel;

		Alert alert = new Alert(null);
		// ComboBox for Staff
		ComboBox<String> comboStaffNames = new ComboBox<>();
		Map<String, Integer> staffMap = new HashMap<>();

		// Fetch staff list and populate ComboBox
		StaffCRUD staffCRUD = new StaffCRUD();
		List<StaffModel> staffList = staffCRUD.getAllStaff();
		staffList.forEach(staff -> {
			staffMap.put(staff.getFullName(), staff.getStaffId()); // Store Name → ID in Map
			comboStaffNames.getItems().add(staff.getFullName()); // Populate ComboBox with names
		});

		// ComboBox selection listener to store the selected Staff ID
		comboStaffNames.setOnAction(event -> {
			String selectedStaffName = comboStaffNames.getValue();
			int selectedStaffId = staffMap.getOrDefault(selectedStaffName, -1); // Store the selected Staff ID
		});
		comboStaffNames.setPrefWidth(200);
		comboStaffNames.relocate(480, 376);
		StaffModel sm = new StaffModel();
		// Set selected staff ID into the staff model upon ComboBox selection
		comboStaffNames.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String selectedStaffName = comboStaffNames.getValue();
				if (selectedStaffName != null) {
					int selectedStaffId11 = staffMap.get(selectedStaffName); // Get ID from map

					sm.setStaffId(selectedStaffId11);
					System.out.println("Selected Staff: " + selectedStaffName + " (ID: " + selectedStaffId11 + ")");
				}
			}
		});
		comboStaffNames.setStyle(AppSettings.comboBox);

		// Setting fonts and layout for labels and text fields
		Font font = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font font1 = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Font font2 = new Font(AppSettings.textBoxFont, AppSettings.textBoxFontSize);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);
		primaryStage.show();
		primaryStage.setResizable(false);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Labels and Input Fields for Searched owner details
		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font("Arial", 30));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		lblTitle = new Label("Assign Vet to Owner");
		lblTitle.relocate(300, 40);
		lblTitle.setFont(font1);

		lblOwnerId = new Label("Owner Id");
		lblOwnerId.relocate(300, 110);
		lblOwnerId.setFont(font);

		txtOwnerId = new TextField("");
		txtOwnerId.relocate(480, 110);
		txtOwnerId.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		btnSearch = new Button("Search");
		btnSearch.relocate(800, 110);
		btnSearch.setStyle(AppSettings.btnPrimary);

		lblOwnername = new Label("Owner Name");
		lblOwnername.relocate(300, 169);
		lblOwnername.setFont(font);

		txtOwnername = new TextField();
		txtOwnername.relocate(480, 169);
		txtOwnername.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtOwnername.setDisable(true);
		txtOwnername.setFont(font2);

		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(300, 238);
		lblContactNo.setFont(font);

		txtContactNo = new TextField();
		txtContactNo.relocate(480, 238);
		txtContactNo.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtContactNo.setDisable(true);
		txtContactNo.setFont(font2);

		lblAddress = new Label("Address");
		lblAddress.relocate(300, 309);
		lblAddress.setFont(font);

		txtAddress = new TextField();
		txtAddress.relocate(480, 309);
		txtAddress.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtAddress.setDisable(true);
		txtAddress.setFont(font2);

		lblStaff = new Label("Staff");
		lblStaff.relocate(300, 376);
		lblStaff.setFont(font);

		lblRemarks = new Label("Additional Remarks");
		lblRemarks.relocate(300, 443);
		lblRemarks.setFont(font);

		txtRemarks = new TextField();
		txtRemarks.relocate(480, 443);
		txtRemarks.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		// Search owner by ID and display information in form
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!txtOwnerId.getText().isEmpty()) {
					try {
						int ownerId = Integer.parseInt(txtOwnerId.getText()); // Convert text to integer
						OwnerCRUD ownerCRUD = new OwnerCRUD(); // Use repository class
						OwnerModel ownerModel = ownerCRUD.getOwnerbyId(ownerId); // Fetch from DB

						if (ownerModel.getFullName() != null) {
							txtOwnername.setText(ownerModel.getFullName()); // Set text field
							txtContactNo.setText(ownerModel.getContactNo());
							txtAddress.setText(ownerModel.getAddress());
						} else {
							alert.setAlertType(AlertType.WARNING);
							alert.setContentText("Owner Not Found in History");
							;
							alert.show();
						}
						

					} catch (NumberFormatException e) {
						alert.setAlertType(AlertType.WARNING);
						alert.setContentText("Invalid owner ID format: " + e.getMessage());
						;
						alert.show();
					}
				} else {
					alert.setAlertType(AlertType.WARNING);
					alert.setContentText("Owner ID cannot be empty.");
					;
					alert.show();// System.out.println("")
				}
			}
		});

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(480, 600);
		btnSubmit.setStyle(AppSettings.btnPrimary);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setEffect(new DropShadow()));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setEffect(null));
		// Assign selected vet to owner and store it in database
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				VetAssignmentModel vam = new VetAssignmentModel();
				try {
					if (sm.getStaffId() == -1) {
						alert.setAlertType(AlertType.INFORMATION);
						alert.setContentText("Error: No staff selected!");
						alert.show();
						return;
					}
					vam.setOwnerId(Integer.parseInt(txtOwnerId.getText()));
					vam.setStaffId(sm.getStaffId());
					vam.setAdditionalRemarks(txtRemarks.getText());
					vam.setAssignedDate(LocalDate.now().toString());

					new VetAssignmentCRUD().insert(vam);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setContentText("Vet assigned successfully!");
					alert.show();
					txtOwnerId.clear();
					txtOwnername.clear();
					txtContactNo.clear();
					txtAddress.clear();
					txtRemarks.clear();
					comboStaffNames.setValue(null);
				} catch (Exception ex) {
					alert.setAlertType(AlertType.WARNING);
					alert.setContentText("Error assigning vet: " + ex.getMessage());
					alert.show();
				}
			}
		});
		// Close the form
		btnCancel = new Button("Close");
		btnCancel.relocate(600, 600);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setOnMouseEntered(event -> btnCancel.setEffect(new DropShadow()));
		btnCancel.setOnMouseExited(event -> btnCancel.setEffect(null));
		btnCancel.setOnAction(event -> primaryStage.close());

		// Adding elements to Pane
		pane.getChildren().addAll(lblTitle, lblOwnerId, txtOwnerId, lblOwnername, txtOwnername, btnSearch);
		pane.getChildren().addAll(lblContactNo, lblAddress, lblStaff, txtContactNo, txtAddress, comboStaffNames);
		pane.getChildren().addAll(btnSubmit, btnCancel, sidebar, lblSidebarTitle);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
