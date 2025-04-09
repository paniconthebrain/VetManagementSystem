package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import library.AppSettings;
import model.OwnerModel;
import repo.OwnerCRUD;

/**
 * JavaFX UI class for registering and managing Owners in the Veterinary Management System.
 */
public class Ownersetup extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI elements
		Label lblTitle, lblOwnerID, lblOwnerName, lblContactNo, lblEmail, lblAddress, lblNickname, lblPetBreed,
				lblDateOfBirth;
		TextField txtOwnerID, txtOwnerName, txtContactNo, txtEmail, txtAddress, txtNickname, txtPetBreed,
				txtDateOfBirth;
		Button btnSubmit, btnDelete, btnUpdate, btnView, btnClear;

		Font font = new Font("Arial", 18);

		// Create Pane and Scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);
		primaryStage.show();

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

		// Labels and Text Fields
		lblTitle = new Label("Owner Setup");
		lblTitle.relocate(300, 50);
		lblTitle.setFont(font);

		int labelX = 300, inputX = 450, startY = 100, spacingY = 50;


		// Labels and Input Fields for Owner details
		lblOwnerID = new Label("Owner ID:");
		lblOwnerID.relocate(labelX, startY);
		lblOwnerID.setFont(font);
		txtOwnerID = new TextField();
		txtOwnerID.relocate(inputX, startY);
		txtOwnerID.setPrefSize(200, 30);

		lblOwnerName = new Label("Owner Name:");
		lblOwnerName.relocate(labelX, startY + spacingY);
		lblOwnerName.setFont(font);
		txtOwnerName = new TextField();
		txtOwnerName.relocate(inputX, startY + spacingY);
		txtOwnerName.setPrefSize(200, 30);

		lblContactNo = new Label("Contact No:");
		lblContactNo.relocate(labelX, startY + 2 * spacingY);
		lblContactNo.setFont(font);
		txtContactNo = new TextField();
		txtContactNo.relocate(inputX, startY + 2 * spacingY);
		txtContactNo.setPrefSize(200, 30);

		lblEmail = new Label("Email:");
		lblEmail.relocate(labelX, startY + 3 * spacingY);
		lblEmail.setFont(font);
		txtEmail = new TextField();
		txtEmail.relocate(inputX, startY + 3 * spacingY);
		txtEmail.setPrefSize(200, 30);

		lblAddress = new Label("Address:");
		lblAddress.relocate(labelX, startY + 4 * spacingY);
		lblAddress.setFont(font);
		txtAddress = new TextField();
		txtAddress.relocate(inputX, startY + 4 * spacingY);
		txtAddress.setPrefSize(200, 30);

		lblNickname = new Label("Pet Nickname:");
		lblNickname.relocate(labelX, startY + 5 * spacingY);
		lblNickname.setFont(font);
		txtNickname = new TextField();
		txtNickname.relocate(inputX, startY + 5 * spacingY);
		txtNickname.setPrefSize(200, 30);

		lblPetBreed = new Label("Pet Breed:");
		lblPetBreed.relocate(labelX, startY + 6 * spacingY);
		lblPetBreed.setFont(font);
		txtPetBreed = new TextField();
		txtPetBreed.relocate(inputX, startY + 6 * spacingY);
		txtPetBreed.setPrefSize(200, 30);

		lblDateOfBirth = new Label("Date of Birth:");
		lblDateOfBirth.relocate(labelX, startY + 7 * spacingY);
		lblDateOfBirth.setFont(font);
		txtDateOfBirth = new TextField();
		txtDateOfBirth.relocate(inputX, startY + 7 * spacingY);
		txtDateOfBirth.setPrefSize(200, 30);

		// Button Initialization and Placement
		int btnY = startY + 8 * spacingY;
		btnSubmit = new Button("Insert");
		btnSubmit.relocate(labelX, btnY);
		btnSubmit.setPrefSize(100, 30);
		btnSubmit.setStyle(AppSettings.btnPrimary);

		btnUpdate = new Button("Update");
		btnUpdate.relocate(labelX + 110, btnY);
		btnUpdate.setPrefSize(100, 30);
		btnUpdate.setStyle(AppSettings.btnPrimary);

		btnDelete = new Button("Delete");
		btnDelete.relocate(labelX + 220, btnY);
		btnDelete.setPrefSize(100, 30);
		btnDelete.setStyle(AppSettings.btnSecondary);

		btnView = new Button("View");
		btnView.relocate(labelX + 330, btnY);
		btnView.setPrefSize(100, 30);
		btnView.setStyle(AppSettings.btnStage1);

		btnClear = new Button("Clear");
		btnClear.relocate(labelX, 600);
		btnClear.setPrefSize(100, 30);
		btnClear.setStyle(AppSettings.btnStage2);

		btnClear.setOnAction(e -> {
			txtOwnerID.clear();
			txtOwnerName.clear();
			txtContactNo.clear();
			txtEmail.clear();
			txtAddress.clear();
			txtNickname.clear();
			txtPetBreed.clear();
			txtDateOfBirth.clear();
		});

		// Insert new Owner Record
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				OwnerModel ownerModel = new OwnerModel();
				ownerModel.setFullName(txtOwnerName.getText());
				ownerModel.setContactNo(txtContactNo.getText());
				ownerModel.setEmail(txtEmail.getText());
				ownerModel.setAddress(txtAddress.getText());
				ownerModel.setPetNickName(txtNickname.getText());
				ownerModel.setPetBreed(txtPetBreed.getText());
				ownerModel.setDateOfBirth(txtDateOfBirth.getText());

				boolean result = new OwnerCRUD().Insert(ownerModel);

				Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(result ? "Owner Added Successfully" : "Error : Owner Name Cannot Be Empty");
				alert.setContentText(result ? "Owner details have been added." : "Please try again.");
				alert.showAndWait();
			}
		});
		// Delete existing Owner record
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					int ownerId = Integer.parseInt(txtOwnerID.getText()); // Ensure there is a txtOwnerId input for ID
					boolean result = new OwnerCRUD().delete(ownerId);

					Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
					alert.setTitle(result ? "Success" : "Error");
					alert.setHeaderText(result ? "Owner Deleted Successfully" : "Error Deleting Owner");
					alert.setContentText(
							result ? "Owner with ID " + ownerId + " has been deleted." : "Please try again.");
					alert.showAndWait();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Owner ID");
					alert.setContentText("Please provide a valid numeric Owner ID.");
					alert.showAndWait();
				}
			}
		});
		// Update existing Owner details
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					int ownerId = Integer.parseInt(txtOwnerID.getText()); // Ensure there is a txtOwnerId input for ID
					OwnerModel ownerModel = new OwnerModel();
					ownerModel.setOwnerId(ownerId); // Set the ownerId for update
					ownerModel.setFullName(txtOwnerName.getText());
					ownerModel.setContactNo(txtContactNo.getText());
					ownerModel.setEmail(txtEmail.getText());
					ownerModel.setAddress(txtAddress.getText());
					ownerModel.setPetNickName(txtNickname.getText());
					ownerModel.setPetBreed(txtPetBreed.getText());
					ownerModel.setDateOfBirth(txtDateOfBirth.getText());

					boolean result = new OwnerCRUD().Update(ownerModel);

					Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
					alert.setTitle(result ? "Success" : "Error");
					alert.setHeaderText(result ? "Owner Updated Successfully" : "Error Updating Owner");
					alert.setContentText(result ? "Owner details have been updated." : "Please try again.");
					alert.showAndWait();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Owner ID");
					alert.setContentText("Please provide a valid numeric Owner ID.");
					alert.showAndWait();
				}
			}
		});
		// View Owner details based on ID
		btnView.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					Integer ownerId = Integer.parseInt(txtOwnerID.getText()); // Ensure there is a txtOwnerId input for
																				// ID
					OwnerModel ownerModel = new OwnerCRUD().searchByID(ownerId);

					if (ownerModel != null) {
						txtOwnerName.setText(ownerModel.getFullName());
						txtContactNo.setText(ownerModel.getContactNo());
						txtEmail.setText(ownerModel.getEmail());
						txtAddress.setText(ownerModel.getAddress());
						txtNickname.setText(ownerModel.getPetNickName());
						txtPetBreed.setText(ownerModel.getPetBreed());
						txtDateOfBirth.setText(ownerModel.getDateOfBirth());

						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Owner Found");
						alert.setHeaderText("Owner Details Found");
						alert.setContentText("Owner " + ownerModel.getFullName() + " has been found.");
						alert.showAndWait();
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Owner Not Found");
						alert.setContentText("No owner found with ID " + ownerId + ".");
						alert.showAndWait();
					}
				} catch (NumberFormatException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Invalid Owner ID");
					alert.setContentText("Please provide a valid numeric Owner ID.");
					alert.showAndWait();
				}
			}
		});

		// Add elements to the pane
		pane.getChildren().addAll(lblTitle, lblOwnerID, txtOwnerID, lblOwnerName, txtOwnerName, lblContactNo,
				txtContactNo, lblEmail, txtEmail, lblAddress, txtAddress, lblNickname, txtNickname, lblPetBreed,
				txtPetBreed, lblDateOfBirth, txtDateOfBirth, btnSubmit, btnUpdate, btnDelete, btnView, sidebar,
				lblSidebarTitle, btnClear);
	}

	// Entry point of the application
	public static void main(String[] args) {
		launch(args);
	}
}
