package reports;

import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import library.PDFGeneratorService;
import model.OwnerModel;
import repo.OwnerCRUD;

public class PetReport extends Application {

	private TableView<OwnerModel>table;
	private TextField ownerIdInput, fullNameInput, petNameInput, petBreedInput, dobInput;
	private Button viewButton, printButton;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Pet Management");

		// Text Fields (Initially Disabled)
		ownerIdInput = new TextField();
		ownerIdInput.setPromptText("Owner ID");

		fullNameInput = new TextField();
		fullNameInput.setPromptText("Full Name");
		fullNameInput.setDisable(true);

		petNameInput = new TextField();
		petNameInput.setPromptText("Pet Nickname");
		petNameInput.setDisable(true);

		petBreedInput = new TextField();
		petBreedInput.setPromptText("Pet Breed");
		petBreedInput.setDisable(true);

		dobInput = new TextField();
		dobInput.setPromptText("Date of Birth");
		dobInput.setDisable(true);

		// Buttons
		viewButton = new Button("View");

		viewButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(ownerIdInput.getText());
				OwnerModel owner = new OwnerCRUD().getPetById(ownerId);

				if (owner.getOwnerId() != 0) {
					fullNameInput.setText(owner.getFullName());
					petNameInput.setText(owner.getPetNickName());
					petBreedInput.setText(owner.getPetBreed());
					dobInput.setText(owner.getDateOfBirth());

					printButton.setDisable(false);
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING, "No pet found for this Owner ID!");
					alert.show();
				}
			}
		});

		printButton = new Button("Print to PDF");
		printButton.setDisable(true);
		printButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(ownerIdInput.getText());
				OwnerModel owner = new OwnerCRUD().getPetById(ownerId);

				if (owner.getOwnerId() != 0) {
					Map<String, String> data = Map.of("Owner ID", String.valueOf(owner.getOwnerId()), "Owner Name",
							owner.getFullName(), "Pet Name", owner.getPetNickName(), "Pet Breed", owner.getPetBreed(),
							"Date of Birth", owner.getDateOfBirth());

					PDFGeneratorService.generatePDF(data, "pet_Report_" + ownerId + ".pdf", "Pet Report");

					Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Generated!");
					alert.show();
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING, "No pet found for this Owner ID!");
					alert.show();
				}
			}
		});

		HBox buttonLayout = new HBox(10, viewButton, printButton);
		VBox layout = new VBox(10, ownerIdInput, fullNameInput, petNameInput, petBreedInput, dobInput, buttonLayout);
		layout.setPadding(new Insets(10));

		primaryStage.setScene(new Scene(layout, 400, 300));
		primaryStage.show();
	}

}