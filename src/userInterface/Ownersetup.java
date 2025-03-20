package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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

		Label lblTitle, lblOwnerinfo, lblOwnername, lblGender, lblAddress, lblPetinfo, lblNickname, lblPetbreed,
				lblDateofbirth;
		TextField txtOwnername, txtGender, txtAddress, txtNickname, txtPetbreed;
		DatePicker datePicker;
		Button btnSubmit, btnCancel;

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

		lblOwnerinfo = new Label("Owner Information");
		lblOwnerinfo.relocate(440, 160);
		lblOwnerinfo.setFont(font2);

		lblOwnername = new Label("Owner Name");
		lblOwnername.relocate(456, 231);
		lblOwnername.setFont(font);

		txtOwnername = new TextField();
		txtOwnername.relocate(650, 215);
		txtOwnername.setPrefSize(311, 40);

		lblGender = new Label("Gender");
		lblGender.relocate(456, 295);
		lblGender.setFont(font);

		txtGender = new TextField();
		txtGender.relocate(650, 281);
		txtGender.setPrefSize(311, 40);

		lblAddress = new Label("Address");
		lblAddress.relocate(451, 359);
		lblAddress.setFont(font);

		txtAddress = new TextField();
		txtAddress.relocate(650, 349);
		txtAddress.setPrefSize(311, 40);

		lblPetinfo = new Label("Pet Information");
		lblPetinfo.relocate(440, 434);
		lblPetinfo.setFont(font);

		lblNickname = new Label("Pet NickName");
		lblNickname.relocate(456, 500);
		lblNickname.setFont(font);

		txtNickname = new TextField();
		txtNickname.relocate(650, 484);
		txtNickname.setPrefSize(311, 40);

		lblPetbreed = new Label("Pet Breed");
		lblPetbreed.relocate(456, 566);
		lblPetbreed.setFont(font);

		txtPetbreed = new TextField();
		txtPetbreed.relocate(650, 550);
		txtPetbreed.setPrefSize(311, 40);

		lblDateofbirth = new Label("Date of Birth");
		lblDateofbirth.relocate(456, 632);
		lblDateofbirth.setFont(font);

		datePicker = new DatePicker();
		datePicker.relocate(650, 616);
		datePicker.setPrefSize(311, 40);

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 700);
//		btnSubmit.setPrefSize(311, 40);
		
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				 OwnerModel ownerModel = new OwnerModel();
				 ownerModel.setFullName(txtOwnername.getText());;
				 ownerModel.setAddress(txtAddress.getText());
				 ownerModel.setPetNickName(txtNickname.getText());
				 ownerModel.setPetBread(txtPetbreed.getText());
				 ownerModel.setDate(txtDateofbirth.getText());
				 
				 boolean result = new OwnerCRUD().Insert(ownerModel);
				 
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 700);
//		btnCancel.setPrefSize(311, 40);

		pane.getChildren().add(lblTitle);
		pane.getChildren().add(lblOwnerinfo);
		pane.getChildren().addAll(lblOwnername, txtOwnername);
		pane.getChildren().addAll(lblGender, txtGender);
		pane.getChildren().addAll(lblAddress, txtAddress);
		pane.getChildren().addAll(lblPetinfo);
		pane.getChildren().addAll(lblNickname, txtNickname);
		pane.getChildren().addAll(lblPetbreed, txtPetbreed);
		pane.getChildren().addAll(lblDateofbirth, datePicker);
		pane.getChildren().add(btnSubmit);
		pane.getChildren().add(btnCancel);
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
