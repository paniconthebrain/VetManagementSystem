package userInterface;

import java.text.DateFormat;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CompanySetupPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblHeading, lblCompanyName, lblAddress, lblContactNo, lblRegisteredDate;
		TextField txtCompanyName, txtAddress, txtContactNo;
		TextField dfRegisteredDate;

		Button btnSubmit, btnCancel;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Company Register");
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);

		Font headingFont = new Font("Inter", 32);
		Font font1 = new Font("Inter", 24);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label("ABC CLINIC");
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.relocate(50, 50);

		lblHeading = new Label("Company Setup");
		lblHeading.relocate(346, 44);
		lblHeading.setFont(headingFont);

		lblCompanyName = new Label("Company Name");
		lblCompanyName.relocate(450, 168);
		lblCompanyName.setFont(font1);

		txtCompanyName = new TextField();
		txtCompanyName.relocate(645, 168);
		txtCompanyName.setPrefSize(311, 40);

		lblAddress = new Label("Office Address");
		lblAddress.relocate(450, 238);
		lblAddress.setFont(font1);

		txtAddress = new TextField();
		txtAddress.relocate(645, 238);
		txtAddress.setPrefSize(311, 40);

		lblContactNo = new Label("Office Address");
		lblContactNo.relocate(450, 308);
		lblContactNo.setFont(font1);

		txtContactNo = new TextField();
		txtContactNo.relocate(645, 308);
		txtContactNo.setPrefSize(311, 40);

		lblRegisteredDate = new Label("Registered Date");
		lblRegisteredDate.relocate(450, 380);
		lblRegisteredDate.setFont(font1);

		dfRegisteredDate = new TextField();
		dfRegisteredDate.relocate(645, 380);
		dfRegisteredDate.setPrefSize(311, 40);

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 580);
		btnSubmit.setPrefSize(80, 30);

		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 580);
		btnCancel.setPrefSize(80, 30);

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				primaryStage.close();
			}
		});

		pane.getChildren().addAll(lblHeading);
		pane.getChildren().addAll(lblCompanyName, txtCompanyName);
		pane.getChildren().addAll(lblAddress, txtAddress);
		pane.getChildren().addAll(lblContactNo, txtContactNo);
		pane.getChildren().addAll(lblRegisteredDate, dfRegisteredDate);
		pane.getChildren().addAll(btnSubmit, btnCancel);

		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
}
