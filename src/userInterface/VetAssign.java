package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VetAssign extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare UI components
		Label lblTitle, lblOwnername, lblPetname, lblVetstaffname, lblStafftype;
		TextField txtOwnername, txtPetname, txtVetstaffname, txtStafftype;
		Button btnSubmit, btnCancel;

		// Declaring font styles
		Font font = new Font("Arial", 25);
		Font font1 = new Font("Arial", 30);

		// Creating the main pane and scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane);

        // Setting up the primary stage properties
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

		// Main title label
		lblTitle = new Label("Vet Assignment");
		lblTitle.relocate(347, 44);
		lblTitle.setFont(font1);

        // Ownername label and input field
		lblOwnername = new Label("Owner Name");
		lblOwnername.relocate(451, 169);
		lblOwnername.setFont(font);

		txtOwnername = new TextField();
		txtOwnername.relocate(645, 169);
		txtOwnername.setPrefSize(311, 40);

		lblPetname = new Label("Pet Name");
		lblPetname.relocate(451, 238);
		lblPetname.setFont(font);

		txtPetname = new TextField();
		txtPetname.relocate(645, 238);
		txtPetname.setPrefSize(311, 40);

		lblVetstaffname = new Label("Vet Staff Name");
		lblVetstaffname.relocate(451, 309);
		lblVetstaffname.setFont(font);

		txtVetstaffname = new TextField();
		txtVetstaffname.relocate(645, 309);
		txtVetstaffname.setPrefSize(311, 40);

		lblStafftype = new Label("Staff type");
		lblStafftype.relocate(451, 376);
		lblStafftype.setFont(font);

		txtStafftype = new TextField();
		txtStafftype.relocate(645, 376);
		txtStafftype.setPrefSize(311, 40);

		// Submit button to save the record
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 700);
//		btnSubmit.setPrefSize(311, 40);

        // Close button to exit the application
		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 700);
//		btnCancel.setPrefSize(311, 40);

		// Adding all elements to the pane
		pane.getChildren().add(lblTitle);
		pane.getChildren().addAll(lblOwnername, txtOwnername);
		pane.getChildren().addAll(lblPetname, txtPetname);
		pane.getChildren().addAll(lblVetstaffname, txtVetstaffname);
		pane.getChildren().addAll(lblStafftype, txtStafftype);
		pane.getChildren().add(btnSubmit);
		pane.getChildren().add(btnCancel);
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

	}

	public static void main(String[] args) {
		launch(args);

	}
}
