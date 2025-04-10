package reports;

import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import library.PDFGeneratorService;
import model.OwnerModel;
import repo.OwnerCRUD;

public class PetReport extends Application {

	Label lblTitle, lblOwnerid, lblFullName, lblPetName, lblBreed, lblDob;
	private TextField ownerIdInput, fullNameInput, petNameInput, petBreedInput, dobInput;
	private Button viewButton, printButton, btnClose;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Pet Report");

		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		Font font1 = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);

		Font font = new Font(AppSettings.textBoxFont, AppSettings.textBoxFontSize);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font(AppSettings.mainFont1, AppSettings.mainFont1Size));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		// Labels and Text Fields
		lblTitle = new Label("Pet Report");
		lblTitle.relocate(300, 50);
		lblTitle.setFont(font1);

		int labelX = 300, inputX = 450, startY = 200, spacingY = 50;

		lblOwnerid = new Label("Owner ID:");
		lblOwnerid.relocate(labelX, startY);
		lblOwnerid.setFont(font);
		ownerIdInput = new TextField();
		ownerIdInput.relocate(inputX, startY);
		ownerIdInput.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		lblFullName = new Label("Owner Name:");
		lblFullName.relocate(labelX, startY + spacingY);
		lblFullName.setFont(font);
		fullNameInput = new TextField();
		fullNameInput.relocate(inputX, startY + spacingY);
		fullNameInput.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		fullNameInput.setDisable(true);

		lblPetName = new Label("Pet Name:");
		lblPetName.relocate(labelX, startY + 2 * spacingY);
		lblPetName.setFont(font);
		petNameInput = new TextField();
		petNameInput.relocate(inputX, startY + 2 * spacingY);
		petNameInput.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		petNameInput.setDisable(true);

		lblBreed = new Label("Breed:");
		lblBreed.relocate(labelX, startY + 3 * spacingY);
		lblBreed.setFont(font);
		petBreedInput = new TextField();
		petBreedInput.relocate(inputX, startY + 3 * spacingY);
		petBreedInput.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		petBreedInput.setDisable(true);

		lblDob = new Label("Date Of Birth:");
		lblDob.relocate(labelX, startY + 4 * spacingY);
		lblDob.setFont(font);
		dobInput = new TextField();
		dobInput.relocate(inputX, startY + 4 * spacingY);
		dobInput.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		dobInput.setDisable(true);

		// Buttons
		viewButton = new Button("Search");
		viewButton.relocate(780, 200);
		viewButton.setPrefSize(100, 30);
		viewButton.setStyle(AppSettings.btnPrimary);
		viewButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(ownerIdInput.getText());
				OwnerModel owner = new OwnerCRUD().getPetById(ownerId);

				if (owner.getOwnerId() != 0) {
					fullNameInput.setText(owner.getFullName());
					petNameInput.setText(owner.getPetNickName());
					petBreedInput.setText(owner.getPetBreed());
					dobInput.setText(owner.getDateOfBirth().toString());

					printButton.setDisable(false);
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING, "No pet found for this Owner ID!");
					alert.show();
				}
			}
		});

		printButton = new Button("Print to PDF");
		printButton.relocate(300, 480);
		printButton.setPrefSize(100, 30);
		printButton.setStyle(AppSettings.btnContent);
		printButton.setDisable(true);
		printButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(ownerIdInput.getText());
				OwnerModel owner = new OwnerCRUD().getPetById(ownerId);

				if (owner.getOwnerId() != 0) {
					Map<String, String> data = Map.of("Owner ID", String.valueOf(owner.getOwnerId()), "Owner Name",
							owner.getFullName(), "Pet Name", owner.getPetNickName(), "Pet Breed", owner.getPetBreed(),
							"Date of Birth", owner.getDateOfBirth().toString());

					PDFGeneratorService.generatePDF(data, "pet_Report_" + ownerId + ".pdf", "Pet Report");

					Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Generated!");
					alert.show();
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING, "No pet found for this Owner ID!");
					alert.show();
				}
			}
		});

		btnClose = new Button("Close");
		btnClose.relocate(500, 480);
		btnClose.setPrefSize(100, 30);
		btnClose.setStyle(AppSettings.btnSecondary);

		btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionevent) {
				primaryStage.close();

			}
		});

		viewButton.setOnMouseEntered(e -> viewButton.setEffect(new DropShadow()));
		viewButton.setOnMouseExited(e -> viewButton.setEffect(null));

		printButton.setOnMouseEntered(e -> viewButton.setEffect(new DropShadow()));
		printButton.setOnMouseExited(e -> viewButton.setEffect(null));

		// Adding elements to Pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblTitle, lblOwnerid, lblFullName, lblPetName, lblBreed,
				lblDob, ownerIdInput, fullNameInput, petNameInput, petBreedInput, dobInput, viewButton, printButton,
				btnClose);

		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}