package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.ClientFollowUpModel;
import repo.ClientFollowUpCRUD;
import java.time.format.DateTimeFormatter;

public class ClientFollowUpPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblHeading, lblCustomerName, lblFollowUpType, lblFollowUpDate, lblRemarks;
		TextField txtCustomerName, txtFollowUpType, txtRemarks;
		DatePicker dateFollowUpDate;
		Button btnSubmit, btnCancel;

		// Create Pane and Scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Client FollowUp");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);

		// Fonts
		Font headingFont = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Font labelFont = new Font(AppSettings.subFont, AppSettings.subFontSize);

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

		// Main Heading
		lblHeading = new Label("Client Follow Up");
		lblHeading.relocate(346, 42);
		lblHeading.setFont(headingFont);

		// Customer Name Field
		lblCustomerName = new Label("Customer Name:");
		lblCustomerName.relocate(450, 168);
		lblCustomerName.setFont(labelFont);

		txtCustomerName = new TextField();
		txtCustomerName.relocate(645, 168);
		txtCustomerName.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Follow Up Type Field
		lblFollowUpType = new Label("Follow Up Type:");
		lblFollowUpType.relocate(450, 238);
		lblFollowUpType.setFont(labelFont);

		txtFollowUpType = new TextField();
		txtFollowUpType.relocate(645, 238);
		txtFollowUpType.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Follow Up Date Field - Changed to DatePicker
		lblFollowUpDate = new Label("Follow Up Date:");
		lblFollowUpDate.relocate(450, 308);
		lblFollowUpDate.setFont(labelFont);

		dateFollowUpDate = new DatePicker();
		dateFollowUpDate.relocate(645, 308);
		dateFollowUpDate.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Remarks Field
		lblRemarks = new Label("Remarks:");
		lblRemarks.relocate(450, 380);
		lblRemarks.setFont(labelFont);

		txtRemarks = new TextField();
		txtRemarks.relocate(645, 380);
		txtRemarks.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// Submit Button
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 450);
		btnSubmit.setPrefWidth(100);
		btnSubmit.setStyle(AppSettings.btnPrimary);
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				ClientFollowUpModel followUp = new ClientFollowUpModel();
				followUp.setCustomerName(txtCustomerName.getText());
				followUp.setFollowUpType(txtFollowUpType.getText());

				// Get date from DatePicker and format it as string
				if (dateFollowUpDate.getValue() != null) {
					String formattedDate = dateFollowUpDate.getValue()
							.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					followUp.setFollowUpDate(formattedDate);
				} else {
					showAlert(AlertType.ERROR, "Error", "Please select a follow-up date");
					return;
				}

				followUp.setRemarks(txtRemarks.getText());

				boolean result = new ClientFollowUpCRUD().insert(followUp);

				Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(result ? "Follow-Up Added Successfully" : "Error Adding Follow-Up");
				alert.setContentText(result ? "Client follow-up has been added." : "Please try again.");
				alert.showAndWait();
			}
		});

		// Cancel Button
		btnCancel = new Button("Cancel");
		btnCancel.relocate(760, 450);
		btnCancel.setPrefWidth(100);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();
			}
		});

		// Add all elements to pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblHeading, lblCustomerName, txtCustomerName,
				lblFollowUpType, txtFollowUpType, lblFollowUpDate, dateFollowUpDate, lblRemarks, txtRemarks, btnSubmit,
				btnCancel);

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