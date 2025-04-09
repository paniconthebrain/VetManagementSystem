package reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import library.PDFGeneratorService;
import model.AppointmentModel;
import repo.AppointmentCRUD;

public class AppointmentReport extends Application {

    private TableView<AppointmentModel> appointmentTable;
    private AppointmentCRUD appointmentCRUD = new AppointmentCRUD();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Appointment Reports");

        // Main layout
        BorderPane mainPane = new BorderPane();
        Scene scene = new Scene(mainPane, 1000, 700);

        // Sidebar
        VBox sidebar = createSidebar();
        mainPane.setLeft(sidebar);

        // Center content
        VBox centerContent = new VBox(20);
        centerContent.setPadding(new Insets(20));

        // Title
        Label titleLabel = new Label("Appointment Reports");
        titleLabel.setFont(new Font("Arial", 28));

        // Table
        appointmentTable = createAppointmentTable();

        // Buttons
        HBox buttonBox = new HBox(15);
        Button refreshBtn = new Button("Refresh Data");
        refreshBtn.setOnAction(e -> refreshTable());

        Button printBtn = new Button("Generate Report");
        printBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {
				
				List<AppointmentModel> appointments = appointmentCRUD.getAllAppointments();
		        if (appointments.isEmpty()) {
		            showAlert(Alert.AlertType.WARNING, "No Data", "No appointments found to generate report!");
		            return;
		        }		
		        // Prepare data for PDF
		        List<Map<String, String>> reportData = new ArrayList<>();
		        for (AppointmentModel appointment : appointments) {
		            reportData.add(Map.of(
		                    "ID", String.valueOf(appointment.getAppointmentId()),
		                    "Customer", appointment.getCustomerName(),
		                    "Date", appointment.getAppointmentDate().toString(),
		                    "Remarks", appointment.getRemarks()
		            ));
		        }
		        PDFGeneratorService.generatePDFList(reportData, "" + "Appointment" + ".pdf", "Appointment Report");
		        Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Generated!");
				alert.show();
			}
		});

        buttonBox.getChildren().addAll(refreshBtn, printBtn);

        centerContent.getChildren().addAll(titleLabel, buttonBox, appointmentTable);
        mainPane.setCenter(centerContent);

        // Initial data load
        refreshTable();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPrefWidth(250);
        sidebar.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Label companyLabel = new Label(AppSettings.companyName);
        companyLabel.setFont(new Font("Arial", 24));
        companyLabel.setTextFill(Color.WHITE);
        companyLabel.setWrapText(true);
        companyLabel.setPadding(new Insets(20, 10, 40, 10));

        sidebar.getChildren().add(companyLabel);
        return sidebar;
    }

    private TableView<AppointmentModel> createAppointmentTable() {
        TableView<AppointmentModel> table = new TableView<>();
        

        TableColumn<AppointmentModel, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));

        TableColumn<AppointmentModel, String> nameCol = new TableColumn<>("Customer");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<AppointmentModel, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));

        TableColumn<AppointmentModel, String> remarksCol = new TableColumn<>("Remarks");
        remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));

        table.getColumns().addAll(idCol, nameCol, dateCol, remarksCol);
        return table;
    }

    private void refreshTable() {
        List<AppointmentModel> appointments = appointmentCRUD.getAllAppointments();
        ObservableList<AppointmentModel> data = FXCollections.observableArrayList(appointments);
        appointmentTable.setItems(data);
    }
/*
    private void generateReport() {
        List<AppointmentModel> appointments = appointmentCRUD.getAllAppointments();

        if (appointments.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "No appointments found to generate report!");
            return;
        }

        // Prepare data for PDF
        List<Map<String, String>> reportData = new ArrayList<>();
        for (AppointmentModel appointment : appointments) {
            reportData.add(Map.of(
                    "ID", String.valueOf(appointment.getAppointmentId()),
                    "Customer", appointment.getCustomerName(),
                    "Date", appointment.getAppointmentDate().toString(),
                    "Remarks", appointment.getRemarks()
            ));
        }

        boolean success = PDFGeneratorService.generatePDFTable(
                reportData,
                "appointments_report.pdf",
                "Appointments Report",
                new String[]{"ID", "Customer", "Date", "Remarks"}
        );

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "PDF Report generated successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to generate PDF report!");
        }
    }
    */

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
