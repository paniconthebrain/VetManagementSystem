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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.UserManagementModel;
import reports.PetReport;
import repo.DashboardStatsCRUD;

public class Dashboard extends Application {

	@Override
	public void start(Stage primaryStage) {

		UserManagementModel session = UserManagementModel.getInstance();
		String username = session.getUsername();
		String userType = session.getUserType();
		int userId = session.getUserId(); // Hidden value

		// Font settings for buttons and labels
		Font buttonFont = new Font("Arial", 16);
		Font titleFont = new Font("Arial", 20);

		Label lblWelcome = new Label("Welcome, " + username);
		lblWelcome.setFont(titleFont);
		lblWelcome.setTextFill(Color.WHITE);

		// Top Bar (for user name)
		BorderPane topBar = new BorderPane();
		topBar.setStyle("-fx-background-color: #2c3e50;");
		topBar.setPadding(new Insets(10));
		topBar.setRight(lblWelcome);

		// Sidebar Buttons
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
		if (userType.equals("admin")) {
			sidebar.getChildren().addAll(btnCompanySetup, btnStaffSetup, btnUserSetup, btnOwnerSetup,
					btnClientAppointment, btnVetAssign, btnPetReport, btnOwnerReport, btnAppointmentReport,
					btnFieldVisitReport, btnAnalysisReport, spacer, btnLogOut);
		} else if (userType.equals("Owner")) {
			sidebar.getChildren().addAll(btnClientAppointment, btnPetReport, btnAnalysisReport, spacer, btnLogOut);
		} else if (userType.equals("Staff")) {
			sidebar.getChildren().addAll(btnStaffSetup, btnOwnerSetup, btnClientAppointment, btnOwnerReport,
					btnFieldVisitReport, spacer, btnLogOut);
		} else {
			sidebar.getChildren().addAll(spacer, btnLogOut); // Default case: only Logout

		}

		
		// Dashboard stats
		DashboardStatsCRUD stats = new DashboardStatsCRUD();

		Label lblCustomerCount = new Label("Total Customers: " + stats.getCustomerCount());
		Label lblAppointmentCount = new Label("Total Appointments: " + stats.getAppointmentCount());
		Label lblStaffCount = new Label("Total Staff: " + stats.getStaffCount());

		lblCustomerCount.setFont(new Font("Arial", 18));
		lblAppointmentCount.setFont(new Font("Arial", 18));
		lblStaffCount.setFont(new Font("Arial", 18));

		lblCustomerCount.setTextFill(Color.web("#2ecc71"));
		lblAppointmentCount.setTextFill(Color.web("#f39c12"));
		lblStaffCount.setTextFill(Color.web("#e74c3c"));

		VBox dashboardStatsBox = new VBox(20, lblCustomerCount, lblAppointmentCount, lblStaffCount);
		dashboardStatsBox.setAlignment(Pos.CENTER);
		dashboardStatsBox.setPadding(new Insets(20));

		// BorderPane Layout
		BorderPane root = new BorderPane();
		root.setTop(topBar);
		root.setLeft(sidebar);
		root.setCenter(dashboardStatsBox);

		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setTitle("Dashboard - " + userType);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		// Button Actions
		btnCompanySetup.setOnAction(e -> openWindow("Company Setup", () -> {
			CompanySetupPage companySetupPage = new CompanySetupPage();
			try {
				companySetupPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));

		btnStaffSetup.setOnAction(e -> openWindow("Staff Setup", () -> {
			CreateStaff staffSetupPage = new CreateStaff();
			try {
				staffSetupPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnUserSetup.setOnAction(e -> openWindow("User Setup", () -> {
			UserSetup userSetupPage = new UserSetup();
			try {
				userSetupPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnOwnerSetup.setOnAction(e -> openWindow("Owner Setup", () -> {
			Ownersetup ownerSetupPage = new Ownersetup();
			try {
				ownerSetupPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnClientAppointment.setOnAction(e -> openWindow("Appointment", () -> {
			Appointment appointmentPage = new Appointment();
			try {
				appointmentPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnVetAssign.setOnAction(e -> openWindow("Vet Assign", () -> {
			VetAssign vetAssignPage = new VetAssign();
			try {
				vetAssignPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnPetReport.setOnAction(e -> openWindow("Pet Report", () -> {
			PetReport petReportPage = new PetReport();
			try {
				petReportPage.start(new Stage()); // Start the existing page in the new window
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
		btnLogOut.setOnAction(e -> openWindow("Log Out", () -> {
			Login loginPage = new Login();
			try {
				loginPage.start(new Stage()); // Start the existing page in the new window

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
	}

	// Helper method to create styled buttons
	private Button createStyledButton(String text, Font font) {
		Button button = new Button(text);
		button.setPrefSize(160, 30);
		button.setFont(font);
		button.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px;");
		button.setCursor(Cursor.HAND);
		button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
		button.setOnMouseExited(e -> button.setEffect(null));
		return button;
	}

	private Button createLogoutButton(String text, Font font) {
		Button button = new Button(text);
		button.setPrefSize(160, 30);
		button.setFont(font);
		button.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px;");
		button.setCursor(Cursor.HAND);
		button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
		button.setOnMouseExited(e -> button.setEffect(null));
		return button;
	}

	// Methods to open new windows with existing pages
	private void openWindow(String title, Runnable pageSetup) {
		Stage newStage = new Stage();
		newStage.setTitle(title);

		try {
			pageSetup.run(); // Execute the passed Runnable, which will initialize the respective page
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	private void logout() {
//		Stage loginStage = new Stage();
//		loginStage.setTitle("Login");
//		Login login = new Login();
//		try {
//			login.start(loginStage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		launch(args);
	}
}
