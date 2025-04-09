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
import library.AppSettings;
import model.UserManagementModel;
import reports.PetReport;
import repo.DashboardStatsCRUD;

/**
* Dashboard.java
* This is the dashboard of the Veterinary Management System.
* It dynamically loads the sidebar options based on the user role of the logged-in user (admin, staff, or owner)
* and displays statistics like total customers, appointments, and staff.
*
* The dashboard serves as a centralized point for accessing different modules of the system.
*/

public class Dashboard extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Retrieve the session data of the logged-in user
		UserManagementModel session = UserManagementModel.getInstance();
		String username = session.getUsername();
		String userType = session.getUserType();
		int userId = session.getUserId(); //  User ID is hidden from display but used internally

		// Font settings for buttons and labels
		Font buttonFont = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font titleFont = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		
		// Top bar layout (Header with welcome message)
		Label lblWelcome = new Label("Welcome, " + username);
		lblWelcome.setFont(titleFont);
		lblWelcome.setTextFill(Color.WHITE);

		// Top Bar (for user name)
		BorderPane topBar = new BorderPane();
		topBar.setStyle("-fx-background-color: #2c3e50;");
		topBar.setPadding(new Insets(10));
		topBar.setRight(lblWelcome);

		// Define sidebar buttons for different features/modules
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
		// Dashboard statistics using helper class DashboardStatsCRUD
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

		// Display box for dashboard stats
		VBox dashboardStatsBox = new VBox(20, lblCustomerCount, lblAppointmentCount, lblStaffCount);
		dashboardStatsBox.setAlignment(Pos.CENTER);
		dashboardStatsBox.setPadding(new Insets(20));

		// BorderPane Layout
		BorderPane root = new BorderPane();
		root.setTop(topBar);
		root.setLeft(sidebar);
		root.setCenter(dashboardStatsBox);

		// Create and display the main scene
		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setTitle("Dashboard - " + userType);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		// Event handlers for sidebar button actions (navigate to respective windows)
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
				primaryStage.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));
	}

	/**
	 * methods to create styled sidebar buttons
	 */
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
	/**
	 * Opens a new window with the corresponding scene/page
	 */
	private void openWindow(String title, Runnable pageSetup) {
		Stage newStage = new Stage();
		newStage.setTitle(title);

		try {
			pageSetup.run(); // Execute the passed Runnable, which will initialize the respective page
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Entry point of the application
	public static void main(String[] args) {
		launch(args);
	}
}
