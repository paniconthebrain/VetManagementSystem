package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI components
		Label lblTitle;
		Label lblHeading, lblSubHeading, lblUsername, lblPassword;
		TextField txtUsername;
		PasswordField txtPassword;
		Button btnLogin, btnClose;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);
		
		// Declaring font styles
		Font headingFont = new Font("Inter", 32);
		Font font1 = new Font("Inter", 24);

		lblHeading = new Label("Veterinary Management System");
		lblHeading.setFont(headingFont);
		lblHeading.setTextAlignment(TextAlignment.CENTER);
		lblHeading.setPrefWidth(1440); // Make the width as wide as the window
		lblHeading.setLayoutY(50); // Adjust Y position
		lblHeading.setStyle("-fx-alignment: center;"); // Center align text

		lblSubHeading = new Label("Login");
		lblSubHeading.relocate(664, 320);
		lblSubHeading.setLayoutY(320); // Maintain the height
		lblSubHeading.setFont(headingFont);

		lblUsername = new Label("Username");
		lblUsername.relocate(538, 388);
		lblUsername.setFont(font1);

		txtUsername = new TextField();
		txtUsername.relocate(538, 429);
		txtUsername.setPrefSize(300, 30);

		lblPassword = new Label("Password");
		lblPassword.relocate(538, 489);
		lblPassword.setFont(font1);

		txtPassword = new PasswordField();
		txtPassword.relocate(538, 530);
		txtPassword.setPrefSize(300, 30);

		btnLogin = new Button("Login");
		btnLogin.relocate(760, 580);
		btnLogin.setPrefSize(80, 30);

		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				LoginModel loginModel = new LoginModel();
				loginModel.setUserName(txtUsername.getText());
				loginModel.setPassword(txtPassword.getText());
			}
		});

		btnClose = new Button("Close");
		btnClose.relocate(580, 580);
		btnClose.setPrefSize(80, 30);

		btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();
			}
		});

		pane.getChildren().addAll(lblHeading, lblSubHeading);
		pane.getChildren().addAll(lblUsername, txtUsername);
		pane.getChildren().addAll(lblPassword, txtPassword);
		pane.getChildren().addAll(btnLogin, btnClose);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
