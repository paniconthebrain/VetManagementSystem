package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.UserManagementModel;
import repo.UserCRUD;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblTitle, lblUsername, lblPassword;
        TextField txtUsername;
        PasswordField txtPassword;
        Button btnLogin, btnCancel;

        Font font = new Font("Arial", 25);
        Font font1 = new Font("Arial", 30);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();

        // Sidebar Background
        Rectangle sidebar = new Rectangle(0, 0, 600, 50);
        sidebar.setFill(Color.BLACK);

        // Title
        lblTitle = new Label("Login");
        lblTitle.relocate(250, 10);
        lblTitle.setFont(font1);
        lblTitle.setTextFill(Color.WHITE);

        // Username
        lblUsername = new Label("Username:");
        lblUsername.relocate(100, 100);
        lblUsername.setFont(font);

        txtUsername = new TextField();
        txtUsername.relocate(250, 100);
        txtUsername.setPrefSize(200, 40);

        // Password
        lblPassword = new Label("Password:");
        lblPassword.relocate(100, 160);
        lblPassword.setFont(font);

        txtPassword = new PasswordField();
        txtPassword.relocate(250, 160);
        txtPassword.setPrefSize(200, 40);

        // Buttons
        btnLogin = new Button("Login");
        btnLogin.relocate(250, 250);
        btnLogin.setPrefSize(100, 40);

        btnLogin.setOnAction(event -> {
            // Get input values
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            // Call the CRUD search operation
            UserCRUD userCRUD = new UserCRUD();
            UserManagementModel user = userCRUD.login(username, password);

            if (user != null) {
                // Successful login
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Welcome " + user.getUsername() + "!");
                alert.showAndWait();
                
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
        });

        btnCancel = new Button("Cancel");
        btnCancel.relocate(360, 250);
        btnCancel.setPrefSize(100, 40);

        pane.getChildren().add(lblTitle);
        pane.getChildren().addAll(lblUsername, txtUsername);
        pane.getChildren().addAll(lblPassword, txtPassword);
        pane.getChildren().addAll(btnLogin, btnCancel);
        pane.getChildren().add(sidebar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
