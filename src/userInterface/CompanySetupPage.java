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
import model.CompanySetupModel;
import repo.CompanyCRUD;

public class CompanySetupPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblHeading, lblCompanyName, lblAddress, lblContactNo, lblRegisteredDate, lblMessage;
        DatePicker datePicker;
        TextField txtCompanyName, txtAddress, txtContactNo;
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

        lblContactNo = new Label("Office Number");
        lblContactNo.relocate(450, 308);
        lblContactNo.setFont(font1);

        txtContactNo = new TextField();
        txtContactNo.relocate(645, 308);
        txtContactNo.setPrefSize(311, 40);

        lblRegisteredDate = new Label("Registered Date");
        lblRegisteredDate.relocate(450, 380);
        lblRegisteredDate.setFont(font1);

        datePicker = new DatePicker();
        datePicker.relocate(645, 380);
        datePicker.setPrefSize(311, 40);

        // Message Label for success or failure
        lblMessage = new Label();
        lblMessage.relocate(645, 450);
        lblMessage.setFont(font1);
        lblMessage.setTextFill(Color.RED); // Default to red for errors

        btnSubmit = new Button("Submit");
        btnSubmit.relocate(645, 680);
        btnSubmit.setPrefSize(80, 30);

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                CompanySetupModel companySetup = new CompanySetupModel();
                companySetup.setCompanyName(txtCompanyName.getText());
                companySetup.setCompanyAddress(txtAddress.getText());
                companySetup.setCompanyContactNo(txtContactNo.getText());

                // Fetch date from DatePicker
                if (datePicker.getValue() != null) {
                    companySetup.setCompanyRegisteredDate(datePicker.getValue().toString());
                } else {
                    companySetup.setCompanyRegisteredDate(null);
                }

                boolean result = new CompanyCRUD().Insert(companySetup);
                if (result) {
                    lblMessage.setText("Company registered successfully!");
                    lblMessage.setTextFill(Color.GREEN); // Set text color to green for success
                } else {
                    lblMessage.setText("Failed to register company.");
                    lblMessage.setTextFill(Color.RED); // Set text color to red for failure
                }
            }
        });

        btnCancel = new Button("Cancel");
        btnCancel.relocate(768, 680);
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
        pane.getChildren().addAll(lblRegisteredDate, datePicker);
        pane.getChildren().addAll(btnSubmit, btnCancel);
        pane.getChildren().addAll(lblMessage); // Add message label to the pane
        pane.getChildren().add(sidebar);
        pane.getChildren().add(lblSidebarTitle);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
