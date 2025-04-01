package userInterface;

import interfaces.AppSettings;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.OwnerModel;
import model.StaffModel;
import model.UserManagementModel;
import repo.OwnerCRUD;
import repo.StaffCRUD;
import repo.UserCRUD;

public class UserSetup extends Application implements AppSettings {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Label lblTitle, lblUserFor, lblAssignedId, lblName, lblUserName, lblPassword;
		TextField txtAssignedId, txtName, txtUserName;
		PasswordField txtPassword;
		ComboBox<String> cmUserFor = new ComboBox<>();
		Button btnSearch, btnSubmit, btnClose;

		// Fonts
		Font font = new Font(subFont, subFontSize);
		Font font1 = new Font(mainFont1, mainFont1Size);

		// Create Pane and Scene
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

		// Title Label
		lblTitle = new Label("User Setup Page");
		lblTitle.relocate(346, 42);
		lblTitle.setFont(font1);

		// User For Label & ComboBox
		lblUserFor = new Label("User For:");
		lblUserFor.relocate(450, 168);
		lblUserFor.setFont(font);

		cmUserFor.getItems().addAll("Staff", "Owner");
		cmUserFor.relocate(645, 168);
		cmUserFor.setStyle(comboBox);

		// Name Label & TextField
		lblAssignedId = new Label("ID:");
		lblAssignedId.relocate(450, 220);
		lblAssignedId.setFont(font);

		txtAssignedId = new TextField();
		txtAssignedId.relocate(645, 220);
		txtAssignedId.setPrefSize(textBoxWidth, textBoxHeight);

		btnSearch = new Button("Search");
		btnSearch.relocate(980, 220);
		btnSearch.setStyle(btnContent);

		// Username Label & TextField
		lblName = new Label("Name:");
		lblName.relocate(450, 270);
		lblName.setFont(font);

		txtName = new TextField();
		txtName.relocate(645, 270);
		txtName.setPrefSize(textBoxWidth, textBoxHeight);
		txtName.setDisable(true);

		// Password Label & PasswordField
		lblUserName = new Label("Username:");
		lblUserName.relocate(450, 320);
		lblUserName.setFont(font);

		txtUserName = new TextField();
		txtUserName.relocate(645, 320);
		txtUserName.setPrefSize(textBoxWidth, textBoxHeight);

		// Password Label & PasswordField
		lblPassword = new Label("Password:");
		lblPassword.relocate(450, 370);
		lblPassword.setFont(font);

		txtPassword = new PasswordField();
		txtPassword.relocate(645, 370);
		txtPassword.setPrefSize(textBoxWidth, textBoxHeight);
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String selectedUser = cmUserFor.getValue();
				if ("Staff".equals(selectedUser)) {
					int staffId = Integer.parseInt(txtAssignedId.getText());
					StaffModel staffModel = new StaffCRUD().search(staffId);
					if (staffModel != null) {
						txtName.setText(staffModel.getFullName());

					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Staff not found.");
					}
				} else {
					Integer ownerId = Integer.parseInt(txtAssignedId.getText()); // Ensure there is a txtOwnerId input
																					// for
					// ID
					OwnerModel ownerModel = new OwnerCRUD().searchByID(ownerId);
					if (ownerModel != null) {
						txtName.setText(ownerModel.getFullName());
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Owner not found.");
					}
				}
			}
		});
		// Submit Button
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 420);
		btnSubmit.setPrefWidth(100);
		btnSubmit.setStyle(btnPrimary);

		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				UserManagementModel umm = new UserManagementModel();
				try {
					if (umm.getAssignedId() == -1) {
						showAlert(Alert.AlertType.ERROR, "Error", "Owner/Staff not found.");
						return;
					}
					
					umm.setUsername(txtUserName.getText());
					umm.setPassword(txtPassword.getText());
					umm.setUserType(cmUserFor.getValue());
					umm.setAssignedId(Integer.parseInt(txtAssignedId.getText()));
					
					if (umm.getUsername() == "") {
						showAlert(Alert.AlertType.ERROR, "Error", "Please enter User Name.");
						return;
					}
					if (umm.getPassword() == "") {
						showAlert(Alert.AlertType.ERROR, "Error", "Password must not be blank.");
						return;
					}
					new UserCRUD().insert(umm);
					showAlert(Alert.AlertType.INFORMATION, "Success", "User Created Succesfully.");
					
				}catch(Exception ex) {
					showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
				}
			}
		});

		btnClose = new Button("Close");
		btnClose.relocate(760, 420);
		btnClose.setPrefWidth(100);
		btnClose.setStyle(btnSecondary);
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();
			}
		});

		// Add Elements to Pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblTitle, lblUserFor, cmUserFor, lblAssignedId,
				txtAssignedId, btnSearch, lblName, txtName, lblUserName, txtUserName, lblPassword, txtPassword,
				btnSubmit, btnClose);
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
