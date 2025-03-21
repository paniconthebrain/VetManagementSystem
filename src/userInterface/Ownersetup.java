package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.OwnerModel;
import repo.OwnerCRUD;

public class Ownersetup extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Labels, TextFields, and Buttons
		Label lblTitle, lblOwnerInfo, lblOwnerName, lblContactNo, lblEmail, lblAddress, lblPetInfo, lblNickname,
				lblPetBreed, lblDateOfBirth;
		TextField txtOwnerName, txtContactNo, txtEmail, txtAddress, txtNickname, txtPetBreed, txtDateOfBirth;
		Button btnSubmit, btnDelete, btnUpdate, btnView;


		Font font = new Font("Arial", 25);
		Font font1 = new Font("Arial", 30);
		Font font2 = new Font("Arial", 28);
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);

		primaryStage.show();

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label("ABC CLINIC");
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.relocate(50, 50);

		lblTitle = new Label("Owner Setup");
		lblTitle.relocate(347, 44);
		lblTitle.setFont(font1);

		lblOwnerInfo = new Label("Owner Information");
		lblOwnerInfo.relocate(440, 160);
		lblOwnerInfo.setFont(font2);

		lblOwnerName = new Label("Owner Name");
		lblOwnerName.relocate(456, 231);
		lblOwnerName.setFont(font);

		txtOwnerName = new TextField();
		txtOwnerName.relocate(650, 215);
		txtOwnerName.setPrefSize(311, 40);

		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(456, 295);
		lblContactNo.setFont(font);

		txtContactNo = new TextField();
		txtContactNo.relocate(650, 281);
		txtContactNo.setPrefSize(311, 40);

		lblEmail = new Label("Email");
		lblEmail.relocate(456, 359);
		lblEmail.setFont(font);

		txtEmail = new TextField();
		txtEmail.relocate(650, 349);
		txtEmail.setPrefSize(311, 40);

		lblAddress = new Label("Address");
		lblAddress.relocate(456, 423);
		lblAddress.setFont(font);

		txtAddress = new TextField();
		txtAddress.relocate(650, 413);
		txtAddress.setPrefSize(311, 40);

		lblPetInfo = new Label("Pet Information");
		lblPetInfo.relocate(440, 490);
		lblPetInfo.setFont(font);

		lblNickname = new Label("Pet NickName");
		lblNickname.relocate(456, 550);
		lblNickname.setFont(font);

		txtNickname = new TextField();
		txtNickname.relocate(650, 534);
		txtNickname.setPrefSize(311, 40);

		lblPetBreed = new Label("Pet Breed");
		lblPetBreed.relocate(456, 616);
		lblPetBreed.setFont(font);

		txtPetBreed = new TextField();
		txtPetBreed.relocate(650, 600);
		txtPetBreed.setPrefSize(311, 40);

		lblDateOfBirth = new Label("Date of Birth");
		lblDateOfBirth.relocate(456, 682);
		lblDateOfBirth.setFont(font);

		txtDateOfBirth = new TextField();
		txtDateOfBirth.relocate(650, 666);
		txtDateOfBirth.setPrefSize(311, 40);

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 730);
		// Insert Button
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

				Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
				alert.setTitle("Insert Operation");
				alert.setHeaderText(null);
				alert.setContentText(result ? "Owner added successfully!" : "Error occurred while adding owner.");
				alert.showAndWait();
			}
		});

		// Delete Button
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(txtOwnerId.getText());

				boolean result = new OwnerCRUD().delete(ownerId);

				Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
				alert.setTitle("Delete Operation");
				alert.setHeaderText(null);
				alert.setContentText(result ? "Owner deleted successfully!" : "Error occurred while deleting owner.");
				alert.showAndWait();
			}
		});

		// Update Button
		btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				OwnerModel ownerModel = new OwnerModel();
				ownerModel.setOwnerId(Integer.parseInt(txtOwnerId.getText()));
				ownerModel.setFullName(txtOwnerName.getText());
				ownerModel.setContactNo(txtContactNo.getText());
				ownerModel.setEmail(txtEmail.getText());
				ownerModel.setAddress(txtAddress.getText());
				ownerModel.setPetNickName(txtNickname.getText());
				ownerModel.setPetBreed(txtPetBreed.getText());
				ownerModel.setDateOfBirth(txtDateOfBirth.getText());

				boolean result = new OwnerCRUD().Update(ownerModel);

				Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
				alert.setTitle("Update Operation");
				alert.setHeaderText(null);
				alert.setContentText(result ? "Owner updated successfully!" : "Error occurred while updating owner.");
				alert.showAndWait();
			}
		});

		// View Button (Search by ID)
		btnView.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int ownerId = Integer.parseInt(txtOwnerId.getText());

				OwnerModel owner = new OwnerCRUD().search(ownerId);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("View Operation");
				alert.setHeaderText(null);

				if (owner != null) {
					txtOwnerName.setText(owner.getFullName());
					txtContactNo.setText(owner.getContactNo());
					txtEmail.setText(owner.getEmail());
					txtAddress.setText(owner.getAddress());
					txtNickname.setText(owner.getPetNickName());
					txtPetBreed.setText(owner.getPetBreed());
					txtDateOfBirth.setText(owner.getDateOfBirth());

					alert.setContentText("Owner details retrieved successfully!");
				} else {
					alert.setAlertType(AlertType.ERROR);
					alert.setContentText("Owner not found.");
				}

				alert.showAndWait();
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.relocate(820, 730);

		// Add elements to the pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblTitle, lblOwnerInfo, lblOwnerName, txtOwnerName,
				lblContactNo, txtContactNo, lblEmail, txtEmail, lblAddress, txtAddress, lblPetInfo, lblNickname,
				txtNickname, lblPetBreed, txtPetBreed, lblDateOfBirth, txtDateOfBirth, btnSubmit, btnCancel);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
