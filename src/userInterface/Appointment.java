package userInterface;

import javafx.application.Application;
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

public class Appointment extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Declare UI components
		Label lblTitle, lblCustomername, lblAppointmentdate, lblRemarks;
		TextField txtCustomername, txtRemarks;
		DatePicker datePicker;
		Button btnSubmit, btnCancel;

		// Declaring font styles
		Font font = new Font("Arial", 25);
		Font font1 = new Font("Arial", 30);

		// Creating main pane and scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		// Setting up the primary stage
		primaryStage.setScene(scene);
		primaryStage.setWidth(1440);
		primaryStage.setHeight(800);
		primaryStage.show();
		primaryStage.setResizable(false);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label("ABC CLINIC");
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.relocate(50, 50);

		// Main title label
		lblTitle = new Label("Client Appointment");
		lblTitle.relocate(409, 21);
		lblTitle.setFont(font1);

		// label and TextField for customer name
		lblCustomername = new Label("Customer Name");
		lblCustomername.relocate(451, 180);
		lblCustomername.setFont(font1);

		txtCustomername = new TextField();
		txtCustomername.relocate(695, 180);
		txtCustomername.setPrefSize(311, 40);
		// label and DatePicker for customer name
		lblAppointmentdate = new Label("Appointment Date");
		lblAppointmentdate.relocate(451, 244);
		lblAppointmentdate.setFont(font);

		datePicker = new DatePicker();
		datePicker.relocate(695, 244);
		datePicker.setPrefSize(311, 40);

		// Label and TextField for Remarks
		lblRemarks = new Label("Remarks");
		lblRemarks.relocate(451, 550);
		lblRemarks.setFont(font1);

		txtRemarks = new TextField();
		txtRemarks.relocate(695, 550);
		txtRemarks.setPrefSize(311, 40);

		// Submit Button
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(675, 700);
//		btnSubmit.setPrefSize(311, 40);

		// Cancel Button
		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 700);
//		btnCancel.setPrefSize(311, 40);

		// Adding all elements to the pane
		pane.getChildren().add(lblTitle);
		pane.getChildren().addAll(lblCustomername, txtCustomername);
		pane.getChildren().addAll(lblAppointmentdate, datePicker);
		pane.getChildren().addAll(lblRemarks, txtRemarks);
		pane.getChildren().add(btnSubmit);
		pane.getChildren().add(btnCancel);
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
