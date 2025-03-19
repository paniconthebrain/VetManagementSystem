package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblTitle;
		
	
		Label heading,lblUsername,lblPassword;
		TextField txtUsername, txtPassword;
		Button btnLogin;
		
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);
		
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
