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
import model.ClientFollowUpModel;
import repo.ClientFollowUpCRUD;

public class ClientFollowUpPage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblHeading, lblCustomerName, lblFollowUpType, lblFollowUpDate, lblRemarks;
		TextField txtCustomerName, txtFollowUpType, txtFollowUpDate, txtRemarks;

		Button btnSubmit, btnCancel;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Client FollowUp");
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

		lblHeading= new Label("Client Follow Up");
		lblHeading.relocate(346, 44);
		lblHeading.setFont(headingFont);
		
		
		lblCustomerName= new Label("Customer Name");
		lblCustomerName.relocate(450, 168);
		lblCustomerName.setFont(font1);
		
		txtCustomerName= new TextField();
		txtCustomerName.relocate(645, 168);
		txtCustomerName.setPrefSize(310, 40);
		
		lblFollowUpType= new Label("Follow Up Type");
		lblFollowUpType.relocate(450, 238);
		lblFollowUpType.setFont(font1);
		
		txtFollowUpType= new TextField();
		txtFollowUpType.relocate(645, 238);
		txtFollowUpType.setPrefSize(310, 40);
		
		lblFollowUpDate= new Label("Follow Up Date");
		lblFollowUpDate.relocate(450, 308);
		lblFollowUpDate.setFont(font1);
		
		txtFollowUpDate= new TextField();
		txtFollowUpDate.relocate(645, 308);
		txtFollowUpDate.setPrefSize(310, 40);
		
		lblRemarks = new Label("Remarks");
		lblRemarks.relocate(450, 380);
		lblRemarks.setFont(font1);
		
		txtRemarks= new TextField();
		txtRemarks.relocate(645,380);
		txtRemarks.setPrefSize(311, 40);
		
		
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 580);
		btnSubmit.setPrefSize(80, 30);
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
		        ClientFollowUpModel followUp = new ClientFollowUpModel();
		        followUp.setCustomerName(txtCustomerName.getText());
		        followUp.setFollowUpType(txtFollowUpType.getText());
		        followUp.setFollowUpDate(txtFollowUpDate.getText());
		        followUp.setRemarks(txtRemarks.getText());

		        boolean result = new ClientFollowUpCRUD().insert(followUp);

		        Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
		        alert.setTitle(result ? "Success" : "Error");
		        alert.setHeaderText(result ? "Follow-Up Added Successfully" : "Error Adding Follow-Up");
		        alert.setContentText(result ? "Client follow-up has been added." : "Please try again.");
		        alert.showAndWait();
		    }
		});


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
		pane.getChildren().addAll(lblCustomerName, txtCustomerName);
		pane.getChildren().addAll(lblFollowUpType,txtFollowUpType);
		pane.getChildren().addAll(lblFollowUpDate,txtFollowUpDate);
		pane.getChildren().addAll(lblRemarks,txtRemarks);
		pane.getChildren().addAll(btnSubmit,btnCancel);
		
		pane.getChildren().add(sidebar);
		pane.getChildren().add(lblSidebarTitle);

		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}
}
