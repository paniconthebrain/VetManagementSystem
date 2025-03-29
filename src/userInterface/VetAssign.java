package userInterface;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.AppSettings;
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
import model.OwnerModel;
import model.StaffModel;
import model.VetAssignmentModel;
import repo.OwnerCRUD;
import repo.StaffCRUD;
import repo.VetAssignmentCRUD;

public class VetAssign extends Application implements AppSettings {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblTitle, lblOwnerId, lblOwnername, lblContactNo, lblAddress, lblStaff, lblRemarks;
		TextField txtOwnerId, txtOwnername, txtContactNo, txtAddress, txtRemarks;
		Button btnSearch, btnSubmit, btnCancel;

		// ComboBox for Staff

		Alert alert = new Alert(null);

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
		comboStaffNames.relocate(645, 376);
		StaffModel sm = new StaffModel();
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
		comboStaffNames.setStyle(comboBox);

		// Fonts and Layout
		Font font = new Font(subFont, subFontSize);
		Font font1 = new Font(mainFont1, mainFont1Size);
		Font font2 = new Font(textBoxFont, textBoxFontSize);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		primaryStage.setScene(scene);
		primaryStage.setWidth(subPageWidth);
		primaryStage.setHeight(subPageHeight);
		primaryStage.show();
		primaryStage.setResizable(false);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label(companyName);
		lblSidebarTitle.setFont(new Font("Arial", 30));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		lblTitle = new Label("Assign Vet to Owner");
		lblTitle.relocate(347, 40);
		lblTitle.setFont(font1);

		lblOwnerId = new Label("Owner Id");
		lblOwnerId.relocate(451, 110);
		lblOwnerId.setFont(font);

		txtOwnerId = new TextField("");
		txtOwnerId.relocate(645, 110);
		txtOwnerId.setPrefSize(textBoxWidth, textBoxHeight);

		btnSearch = new Button("Search");
		btnSearch.relocate(980, 110);
		btnSearch.setStyle(btnPrimary);

		lblOwnername = new Label("Owner Name");
		lblOwnername.relocate(451, 169);
		lblOwnername.setFont(font);

		txtOwnername = new TextField();
		txtOwnername.relocate(645, 169);
		txtOwnername.setPrefSize(textBoxWidth, textBoxHeight);
		txtOwnername.setDisable(true);
		txtOwnername.setFont(font2);

		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(451, 238);
		lblContactNo.setFont(font);

		txtContactNo = new TextField();
		txtContactNo.relocate(645, 238);
		txtContactNo.setPrefSize(textBoxWidth, textBoxHeight);
		txtContactNo.setDisable(true);
		txtContactNo.setFont(font2);

		lblAddress = new Label("Address");
		lblAddress.relocate(451, 309);
		lblAddress.setFont(font);

		txtAddress = new TextField();
		txtAddress.relocate(645, 309);
		txtAddress.setPrefSize(textBoxWidth, textBoxHeight);
		txtAddress.setDisable(true);
		txtAddress.setFont(font2);

		lblStaff = new Label("Staff");
		lblStaff.relocate(451, 376);
		lblStaff.setFont(font);

		lblRemarks = new Label("Additional Remarks");
		lblRemarks.relocate(451, 443);
		lblRemarks.setFont(font);

		txtRemarks = new TextField();
		txtRemarks.relocate(645, 443);
		txtRemarks.setPrefSize(textBoxWidth, textBoxHeight);

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
		btnSubmit.relocate(645, 700);
		btnSubmit.setStyle(btnPrimary);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setEffect(new DropShadow()));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setEffect(null));

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
				} catch (Exception ex) {

					alert.setAlertType(AlertType.WARNING);
					alert.setContentText("Error assigning vet: " + ex.getMessage());
					alert.show();
				}
			}

		});

		btnCancel = new Button("Close");
		btnCancel.relocate(768, 700);
		btnCancel.setStyle(btnSecondary);
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
