package userInterface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.StaffModel;
import repo.StaffCRUD;

public class CreateStaff extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Label lblTitle, lblfullname, lblGender, lblContactno, lblStafftype;
		TextField txtFullname, txtGender, txtContactno, txtStafftype;
		Button btnSubmit, btnCancel;

		Font font = new Font("Arial", 25);
		Font font1 = new Font("Arial", 30);
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

		lblTitle = new Label("Staff Setup");
		lblTitle.relocate(347, 44);
		lblTitle.setFont(font1);

		lblfullname = new Label("Staff Full Name");
		lblfullname.relocate(451, 169);
		lblfullname.setFont(font);

		txtFullname = new TextField();
		txtFullname.relocate(645, 169);
		txtFullname.setPrefSize(311, 40);

		lblGender = new Label("Gender");
		lblGender.relocate(451, 238);
		lblGender.setFont(font);

		txtGender = new TextField();
		txtGender.relocate(645, 238);
		txtGender.setPrefSize(311, 40);

		lblContactno = new Label("Contact No");
		lblContactno.relocate(451, 309);
		lblContactno.setFont(font);

		txtContactno = new TextField();
		txtContactno.relocate(645, 309);
		txtContactno.setPrefSize(311, 40);

		lblStafftype = new Label("Staff type");
		lblStafftype.relocate(451, 376);
		lblStafftype.setFont(font);

		txtStafftype = new TextField();
		txtStafftype.relocate(645, 376);
		txtStafftype.setPrefSize(311, 40);

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 700);
//		btnSubmit.setPrefSize(311, 40);
		
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				StaffModel staffModel = new StaffModel();
				staffModel.setFullName(txtFullname.getText());
				staffModel.setGender(txtGender.getText());
				staffModel.setContactNo(txtContactno.getText());
				staffModel.setStaffType(txtStafftype.getText());
				boolean result = new StaffCRUD().Insert(staffModel);
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 700);
//		btnCancel.setPrefSize(311, 40);

		pane.getChildren().add(lblTitle);
		pane.getChildren().add(lblfullname);
		pane.getChildren().add(txtFullname);
		pane.getChildren().add(lblGender);
		pane.getChildren().add(txtGender);
		pane.getChildren().add(lblContactno);
		pane.getChildren().add(txtContactno);
		pane.getChildren().add(lblStafftype);
		pane.getChildren().add(txtStafftype);
		pane.getChildren().add(btnSubmit);
		pane.getChildren().add(btnCancel);
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
