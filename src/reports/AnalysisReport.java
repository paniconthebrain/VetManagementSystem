package reports;

import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import library.PDFGeneratorService;
import repo.AnalysisReportCRUD; // A new service to fetch company data

public class AnalysisReport extends Application {

    Label lblTitle, lblTotalOwners, lblTotalPets, lblBreedStats;
    private Button viewButton, printButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Company Analysis Report");

        Alert alert = new Alert(null);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        Font font1 = new Font("Arial", 38);

        Font font = new Font("Arial", 18);

        // Sidebar Background
        Rectangle sidebar = new Rectangle(0, 0, 250, 800);
        sidebar.setFill(Color.BLACK);

        // Sidebar Labels
        Label lblSidebarTitle = new Label(AppSettings.companyName);
        lblSidebarTitle.setFont(new Font("Arial", 30));
        lblSidebarTitle.setTextFill(Color.WHITE);
        lblSidebarTitle.setMaxWidth(200);
        lblSidebarTitle.relocate(50, 50);
        lblSidebarTitle.setWrapText(true);

        // Labels and Buttons
        lblTitle = new Label("Company Analysis Report");
        lblTitle.relocate(300, 50);
        lblTitle.setFont(font1);

        int labelX = 300, inputX = 450, startY = 200, spacingY = 50;

        lblTotalOwners = new Label("Total Owners:");
        lblTotalOwners.relocate(labelX, startY);
        lblTotalOwners.setFont(font);

        lblTotalPets = new Label("Total Pets:");
        lblTotalPets.relocate(labelX, startY + spacingY);
        lblTotalPets.setFont(font);

        lblBreedStats = new Label("Pet Breed Statistics:");
        lblBreedStats.relocate(labelX, startY + 2 * spacingY);
        lblBreedStats.setFont(font);

        // Buttons
        int btnY = startY + 6 * spacingY;
        viewButton = new Button("Generate Report");
        viewButton.relocate(780,200);
        viewButton.setPrefSize(150, 30);
        viewButton.setStyle(AppSettings.btnPrimary);
        viewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Fetching analysis data
            	AnalysisReportCRUD analysisService = new AnalysisReportCRUD();
                int totalOwners = analysisService.getTotalOwners();
                int totalPets = analysisService.getTotalPets();
                //String breedStats = analysisService.getPetBreedStatistics();

                // Display the fetched data on the UI
                lblTotalOwners.setText("Total Owners: " + totalOwners);
                lblTotalPets.setText("Total Pets: " + totalPets);
                lblBreedStats.setText("Pet Breed Statistics:\n" );

                printButton.setDisable(false);
            }
        });

        printButton = new Button("Print to PDF");
        printButton.relocate(300, btnY);
        printButton.setPrefSize(100, 30);
        printButton.setStyle(AppSettings.btnContent);
        printButton.setDisable(true);
        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Generate PDF with the collected analysis data
            	AnalysisReportCRUD analysisService = new AnalysisReportCRUD();
                int totalOwners = analysisService.getTotalOwners();
                int totalPets = analysisService.getTotalPets();
                //String breedStats = analysisService.getPetBreedStatistics();

                // Creating a map of the report data
                Map<String, String> data = Map.of(
                        "Total Owners", String.valueOf(totalOwners),
                        "Total Pets", String.valueOf(totalPets)
                );

                // Generate PDF using PDFGeneratorService
                PDFGeneratorService.generatePDF(data, "company_analysis_report.pdf", "Company Analysis Report");

                // Show success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Generated!");
                alert.show();
            }
        });

        viewButton.setOnMouseEntered(e -> viewButton.setEffect(new DropShadow()));
        viewButton.setOnMouseExited(e -> viewButton.setEffect(null));

        printButton.setOnMouseEntered(e -> printButton.setEffect(new DropShadow()));
        printButton.setOnMouseExited(e -> printButton.setEffect(null));

        // Adding elements to the Pane
        pane.getChildren().addAll(sidebar, lblSidebarTitle, lblTitle, lblTotalOwners, lblTotalPets, lblBreedStats, viewButton, printButton);

        primaryStage.setScene(scene);
        primaryStage.setWidth(AppSettings.subPageWidth);
        primaryStage.setHeight(AppSettings.subPageHeight);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
