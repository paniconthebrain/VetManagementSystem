package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.UserManagementModel;
import repo.UserCRUD;

public class Login extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Display label for Title, UserName, Password,
		Label lblTitle, lblUsername, lblPassword;
		// TextField for taking input from User for username
		TextField txtUsername;
		// PasswordField for taking input from user for password
		PasswordField txtPassword;
		// Button to control Login and cancel
		Button btnLogin, btnCancel;

		// declaring of font style and font face
		Font font = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font font1 = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);

		// Set a new Layout pane and scene and Height /Length of the primary Stage for
		// the User Login Page
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 700, 350);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.setResizable(false);
		primaryStage.show();

		// Title
		lblTitle = new Label(AppSettings.softwareName);
		lblTitle.relocate(100, 10);
		lblTitle.setFont(font1);
		lblTitle.setTextFill(Color.WHITE);

		// Username
		lblUsername = new Label("Username");
		lblUsername.relocate(100, 100);
		lblUsername.setFont(font);

		txtUsername = new TextField();
		txtUsername.relocate(250, 100);
		txtUsername.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtUsername.setPromptText("Enter your username");

		// Password
		lblPassword = new Label("Password");
		lblPassword.relocate(100, 160);
		lblPassword.setFont(font);

		txtPassword = new PasswordField();
		txtPassword.relocate(250, 160);
		txtPassword.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtPassword.setPromptText("Enter your password");

		// Buttons
		btnLogin = new Button("Login");
		btnLogin.relocate(250, 250);
		btnLogin.setPrefSize(100, 40);
		btnLogin.setStyle(AppSettings.btnPrimary);
		btnLogin.setCursor(Cursor.HAND);

		btnLogin.setOnMouseEntered(e -> btnLogin.setEffect(new DropShadow()));
		btnLogin.setOnMouseExited(e -> btnLogin.setEffect(null));

		// Event handler for login button click
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// Get input values
				// Retrieve user inputs from text fields
				String username = txtUsername.getText();
				String password = txtPassword.getText();

				// Validate input fields are not empty
				if (username.isEmpty() || password.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Input Error");
					alert.setHeaderText(null);
					alert.setContentText("Please fill in all fields.");
					alert.showAndWait();
					return;
				}
				// Authenticate user using login method from UserCRUD
				UserCRUD userCRUD = new UserCRUD();
				UserManagementModel user = userCRUD.login(username, password);

				if (user != null) {
					// If Login credential is correct, Success Message is shown 
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Login Successful");
					alert.setHeaderText(null);
					alert.setContentText("Welcome " + user.getUsername() + "!");
					alert.showAndWait();
					
					// Set logged-in user details globally
					UserManagementModel.getInstance().setUserDetails(user.getUserId(), user.getUsername(),
							user.getUserType());
					// Redirect to Dashboard interface
					Dashboard dashboard = new Dashboard();
					try {
						dashboard.start(primaryStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Clear fields
					txtUsername.clear();
					txtPassword.clear();
				} else {
					// Failed login
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Login Failed");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password.");
					alert.showAndWait();
				}
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.relocate(360, 250);
		btnCancel.setPrefSize(100, 40);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setCursor(Cursor.HAND);
		btnCancel.setOnMouseEntered(e -> btnCancel.setEffect(new DropShadow()));
		btnCancel.setOnMouseExited(e -> btnCancel.setEffect(null));

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();
			}
		});

		// Set a solid background color with optional rounded corners and padding
		pane.setBackground(new Background(new BackgroundFill(Color.web("#1c92d2"), // Solid background color
				CornerRadii.EMPTY, Insets.EMPTY)));

		pane.getChildren().add(lblTitle);
		pane.getChildren().addAll(lblUsername, txtUsername);
		pane.getChildren().addAll(lblPassword, txtPassword);
		pane.getChildren().addAll(btnLogin, btnCancel);
	}

	public static void main(String[] args) {
		launch(args);
	}
}