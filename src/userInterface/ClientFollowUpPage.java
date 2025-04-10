package userInterface;

import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.ClientFollowUpModel;
import model.OwnerModel;
import repo.ClientFollowUpCRUD;
import repo.OwnerCRUD;

public class ClientFollowUpPage extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label lblHeading, lblOwnerId, lblCustomerName, lblFollowUpType, lblFollowUpDate, lblRemarks;
		TextField txtOwnerId, txtCustomerName, txtRemarks;
		ComboBox<String> cmbFollowUpType;
		DatePicker dateFollowUpDate;
		Button btnSearch, btnSubmit, btnCancel;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Client FollowUp");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);

		Font headingFont = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Font labelFont = new Font(AppSettings.subFont, AppSettings.subFontSize);

		// Sidebar
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font(AppSettings.mainFont1, 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		// Heading
		lblHeading = new Label("Client Follow Up");
		lblHeading.relocate(346, 42);
		lblHeading.setFont(headingFont);

		// Owner ID Field
		lblOwnerId = new Label("Owner ID:");
		lblOwnerId.relocate(450, 120);
		lblOwnerId.setFont(labelFont);

		txtOwnerId = new TextField();
		txtOwnerId.relocate(645, 120);
		txtOwnerId.setPrefSize(150, AppSettings.textBoxHeight);

		// Customer Name (Read-Only)
		lblCustomerName = new Label("Customer Name:");
		lblCustomerName.relocate(450, 168);
		lblCustomerName.setFont(labelFont);

		txtCustomerName = new TextField();
		txtCustomerName.relocate(645, 168);
		txtCustomerName.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtCustomerName.setDisable(true);
		
		// Search Button
		btnSearch = new Button("Search");
		btnSearch.relocate(805, 120);
		btnSearch.setStyle(AppSettings.btnPrimary);
		btnSearch.setOnAction((ActionEvent e) -> {
			try {
				int ownerId = Integer.parseInt(txtOwnerId.getText());
				OwnerModel owner = new OwnerCRUD().searchByID(ownerId);
				if (owner.getOwnerId() != 0) {
					txtCustomerName.setText(owner.getFullName());
				} else {
					showAlert(Alert.AlertType.WARNING, "Not Found", "Owner not found for ID: " + ownerId);
					txtCustomerName.clear();
				}
			} catch (NumberFormatException ex) {
				showAlert(Alert.AlertType.ERROR, "Invalid Input", "Owner ID must be a number.");
			}
		});



		// Follow Up Type ComboBox
		lblFollowUpType = new Label("Follow Up Type:");
		lblFollowUpType.relocate(450, 238);
		lblFollowUpType.setFont(labelFont);

		ObservableList<String> followUpTypes = FXCollections.observableArrayList("Post-Treatment Checkup",
				"Medication Follow-up", "Test Results Review", "General Consultation");
		cmbFollowUpType = new ComboBox<>(followUpTypes);
		cmbFollowUpType.relocate(645, 238);
		cmbFollowUpType.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		cmbFollowUpType.setStyle(AppSettings.comboBox);

		// Follow Up Date
		lblFollowUpDate = new Label("Follow Up Date:");
		lblFollowUpDate.relocate(450, 308);
		lblFollowUpDate.setFont(labelFont);

		dateFollowUpDate = new DatePicker();
		dateFollowUpDate.relocate(645, 308);
		dateFollowUpDate.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Remarks
		lblRemarks = new Label("Remarks:");
		lblRemarks.relocate(450, 380);
		lblRemarks.setFont(labelFont);

		txtRemarks = new TextField();
		txtRemarks.relocate(645, 380);
		txtRemarks.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Submit
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 450);
		btnSubmit.setPrefWidth(100);
		btnSubmit.setStyle(AppSettings.btnPrimary);
		btnSubmit.setOnAction(e -> {
			ClientFollowUpModel followUp = new ClientFollowUpModel();
			followUp.setOwnerId(Integer.parseInt(txtOwnerId.getText()));

			if (cmbFollowUpType.getValue() == null || cmbFollowUpType.getValue().isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please select a follow-up type");
				return;
			}
			followUp.setFollowUpType(cmbFollowUpType.getValue());

			if (dateFollowUpDate.getValue() == null) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please select a follow-up date");
				return;
			}
			String formattedDate = dateFollowUpDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			followUp.setFollowUpDate(formattedDate);
			followUp.setRemarks(txtRemarks.getText());

			boolean result = new ClientFollowUpCRUD().insert(followUp);

			showAlert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR, result ? "Success" : "Error",
					result ? "Follow-Up added successfully!" : "Failed to add follow-up.");
			
			txtOwnerId.clear();
			txtCustomerName.clear();
			txtRemarks.clear();
			cmbFollowUpType.setValue(null);
			dateFollowUpDate.setValue(null);
		});

		// Cancel
		btnCancel = new Button("Cancel");
		btnCancel.relocate(760, 450);
		btnCancel.setPrefWidth(100);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setOnAction(e -> primaryStage.close());

		// Add to pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblHeading, lblOwnerId, txtOwnerId, btnSearch,
				lblCustomerName, txtCustomerName, lblFollowUpType, cmbFollowUpType, lblFollowUpDate, dateFollowUpDate,
				lblRemarks, txtRemarks, btnSubmit, btnCancel);

		primaryStage.show();
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
