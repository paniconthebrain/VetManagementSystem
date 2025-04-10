package reports;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.ClientFollowUpModel;
import repo.ClientFollowUpCRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowUpReport extends Application {
	// TableView to show follow-up records
	private TableView<ClientFollowUpModel> followUpTable;
	private ClientFollowUpCRUD followUpCRUD = new ClientFollowUpCRUD();

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Client Follow-Up Reports");

		BorderPane mainPane = new BorderPane();
		Scene scene = new Scene(mainPane, 1000, 700);

		VBox sidebar = createSidebar();
		mainPane.setLeft(sidebar);

		VBox centerContent = new VBox(20);
		centerContent.setPadding(new Insets(20));

		Label titleLabel = new Label("Client Follow-Up Reports");
		titleLabel.setFont(new Font("Arial", 28));

		followUpTable = createFollowUpTable();
		// Buttons for refreshing and printing
		HBox buttonBox = new HBox(15);
		Button refreshBtn = new Button("Refresh Data");
		refreshBtn.setOnAction(e -> refreshTable());

		Button printBtn = new Button("Generate Report");
		printBtn.setOnAction(e -> generatePDFReport());

		buttonBox.getChildren().addAll(refreshBtn, printBtn);

		centerContent.getChildren().addAll(titleLabel, buttonBox, followUpTable);
		mainPane.setCenter(centerContent);

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
	// Create and configure TableView columns
	private TableView<ClientFollowUpModel> createFollowUpTable() {
		TableView<ClientFollowUpModel> table = new TableView<>();

		TableColumn<ClientFollowUpModel, Integer> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("followUpId"));

		TableColumn<ClientFollowUpModel, Integer> idOwn = new TableColumn<>("Owner ID");
		idOwn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));

		TableColumn<ClientFollowUpModel, String> nameCol = new TableColumn<>("Customer");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

		TableColumn<ClientFollowUpModel, String> typeCol = new TableColumn<>("Follow-Up Type");
		typeCol.setCellValueFactory(new PropertyValueFactory<>("followUpType"));

		TableColumn<ClientFollowUpModel, String> dateCol = new TableColumn<>("Date");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("followUpDate"));

		TableColumn<ClientFollowUpModel, String> remarksCol = new TableColumn<>("Remarks");
		remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));

		table.getColumns().addAll(idCol, idOwn, nameCol, typeCol, dateCol, remarksCol);
		return table;
	}

	// Refresh the table with the latest follow-up data from the DB
	private void refreshTable() {
		List<ClientFollowUpModel> followUps = followUpCRUD.getAll();
		ObservableList<ClientFollowUpModel> data = FXCollections.observableArrayList(followUps);
		followUpTable.setItems(data);
	}
	// Generate a PDF report of all follow-ups
	private void generatePDFReport() {
		List<ClientFollowUpModel> followUps = followUpCRUD.getAll();
		if (followUps.isEmpty()) {
			showAlert(Alert.AlertType.WARNING, "No Data", "No follow-ups found to generate report!");
			return;
		}
		// Prepare report data in key-value format
		List<Map<String, String>> reportData = new ArrayList<>();
		for (ClientFollowUpModel followUp : followUps) {
			reportData.add(Map.of("ID", String.valueOf(followUp.getFollowUpId()), "Customer",
					followUp.getCustomerName(), "Owner ID", String.valueOf(followUp.getOwnerId()), "Follow-Up Type",
					followUp.getFollowUpType(), "Date", followUp.getFollowUpDate(), "Remarks", followUp.getRemarks()));
		}
		// Generate the PDF using the report data
		PDFGeneratorService.generatePDFList(reportData, "ClientFollowUpReport.pdf", "Client Follow-Up Report");
		showAlert(Alert.AlertType.INFORMATION, "Success", "PDF Report generated successfully!");
	}
	// Utility method to show alert dialog
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
