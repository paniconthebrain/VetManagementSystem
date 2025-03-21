package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.LoginModel;
import repo.LoginCRUD;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1440);
        primaryStage.setHeight(800);

        Font headingFont = new Font("Inter", 32);
        Font font1 = new Font("Inter", 24);

        Label lblHeading, lblSubHeading, lblUsername, lblPassword;
        TextField txtUsername;
        PasswordField txtPassword;
        Button btnLogin, btnClose;

        lblHeading = new Label("Veterinary Management System");
        lblHeading.setFont(headingFont);
        lblHeading.setTextAlignment(TextAlignment.CENTER);
        lblHeading.setPrefWidth(1440);
        lblHeading.setLayoutY(50);
        lblHeading.setStyle("-fx-alignment: center;");

        lblSubHeading = new Label("Login");
        lblSubHeading.relocate(664, 320);
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

        // Login button action with alert
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoginCRUD loginRepo = new LoginCRUD();
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                LoginModel loginModel = loginRepo.login(username, password);

                if (loginModel != null) {
                    // Login successful
                	System.out.println(username);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Welcome, " + loginModel.getUserName() + "!");
                    alert.showAndWait();
                } else {
                    // Login failed

                	System.out.println(username);

                	System.out.println(password);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password. Please try again.");
                    alert.showAndWait();
                }
            }
        });

        btnClose = new Button("Close");
        btnClose.relocate(580, 580);
        btnClose.setPrefSize(80, 30);

        // Close button action
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });

        pane.getChildren().addAll(lblHeading, lblSubHeading, lblUsername, txtUsername, lblPassword, txtPassword, btnLogin, btnClose);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
