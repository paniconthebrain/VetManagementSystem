package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Dashboard extends Application {
	private String userType = "Admin"; // Change this value to "Vet", "Staff", etc.

	@Override
	public void start(Stage primaryStage) {
		// Font settings for buttons and labels
		Font buttonFont = new Font("Arial", 16);
		Font titleFont = new Font("Arial", 20);

		Label lblWelcome = new Label("Welcome, " + userType);
		lblWelcome.setFont(titleFont);
		lblWelcome.setTextFill(Color.WHITE);

		// Top Bar (for user name)
		BorderPane topBar = new BorderPane();
		topBar.setStyle("-fx-background-color: #2c3e50;");
		topBar.setPadding(new Insets(10));
		topBar.setRight(lblWelcome);

		// Sidebar Buttons
		Button btnAppointment = createStyledButton("Appointment", buttonFont);
		Button btnStaffSetup = createStyledButton("Staff Setup", buttonFont);
		Button btnOwnerSetup = createStyledButton("Owner Setup", buttonFont);
		Button btnClientAppointment = createStyledButton("Client Appointment", buttonFont);
		Button btnCompanySetup = createStyledButton("Company Setup", buttonFont);
		Button btnUserSetup = createStyledButton("User Setup", buttonFont);
		Button btnVetAssign = createStyledButton("Vet Assign", buttonFont);
		Button btnPetReport = createStyledButton("Pet Report", buttonFont);
		Button btnOwnerReport = createStyledButton("Owner Report", buttonFont);
		Button btnAppointmentReport = createStyledButton("Appointment Report", buttonFont);
		Button btnFieldVisitReport = createStyledButton("Field Visit Report", buttonFont);
		Button btnAnalysisReport = createStyledButton("Analysis Report", buttonFont);
		Button btnLogOut = createLogoutButton("Logout", buttonFont);

		// Create a flexible spacer to push logout button to the bottom
		Region spacer = new Region();
		VBox.setVgrow(spacer, Priority.ALWAYS);

		// Sidebar Layout
		VBox sidebar = new VBox(10);
		sidebar.setStyle("-fx-background-color: #34495e;");
		sidebar.setPadding(new Insets(20));
		sidebar.setPrefWidth(200);

		// Add buttons based on user type
		if (userType.equals("Admin")) {
			sidebar.getChildren().addAll(btnCompanySetup, btnStaffSetup, btnUserSetup, btnOwnerSetup, btnAppointment,
					btnClientAppointment, btnVetAssign, btnPetReport, btnOwnerReport, btnAppointmentReport,
					btnFieldVisitReport, btnAnalysisReport, spacer, btnLogOut);
		} else if (userType.equals("Staff")) {
			sidebar.getChildren().addAll(btnAppointment, btnClientAppointment, btnPetReport, btnAnalysisReport, spacer,
					btnLogOut);
		} else if (userType.equals("Staff")) {
			sidebar.getChildren().addAll(btnStaffSetup, btnOwnerSetup, btnClientAppointment, btnOwnerReport,
					btnFieldVisitReport, spacer, btnLogOut);
		} else {
			sidebar.getChildren().addAll(spacer, btnLogOut); // Default case: only Logout
		}

		// Main Content Area (Placeholder)
		Label lblContent = new Label("");
		lblContent.setFont(titleFont);
		lblContent.setAlignment(Pos.CENTER);

		// BorderPane Layout
		BorderPane root = new BorderPane();
		root.setTop(topBar);
		root.setLeft(sidebar);
		root.setCenter(lblContent);

		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setTitle("Dashboard - " + userType);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		// Button Actions
		btnLogOut.setOnAction((ActionEvent event) -> {
			logout();
			primaryStage.close();
		});
	}

	// Helper method to create styled buttons
	private Button createStyledButton(String text, Font font) {
		Button button = new Button(text);
		button.setPrefSize(160, 40);
		button.setFont(font);
		button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
		button.setCursor(Cursor.HAND);
		button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
		button.setOnMouseExited(e -> button.setEffect(null));
		return button;
	}

	private Button createLogoutButton(String text, Font font) {
		Button button = new Button(text);
		button.setPrefSize(160, 40);
		button.setFont(font);
		button.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px;");
		button.setCursor(Cursor.HAND);
		button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
		button.setOnMouseExited(e -> button.setEffect(null));
		return button;
	}

	private void logout() {
		Stage loginStage = new Stage();
		loginStage.setTitle("Login");
		Login login = new Login();
		try {
			login.start(loginStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
