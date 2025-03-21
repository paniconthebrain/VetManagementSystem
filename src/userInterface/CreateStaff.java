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
import model.StaffModel;
import repo.StaffCRUD;

public class CreateStaff extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label lblTitle, lblfullname, lblGender, lblContactno, lblStafftype;
        TextField txtFullname, txtGender, txtContactno, txtStafftype, txtStaffId;
        Button btnSubmit, btnUpdate, btnDelete, btnSearch, btnCancel;

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

        // Prompt for the users "Staff Setup"
        lblTitle = new Label("Staff Setup");
        lblTitle.relocate(347, 44);
        lblTitle.setFont(font1);

        // Staff ID field for CRUD operations
        Label lblStaffId = new Label("Staff ID");
        lblStaffId.relocate(451, 100);
        lblStaffId.setFont(font);

        txtStaffId = new TextField();
        txtStaffId.relocate(645, 100);
        txtStaffId.setPrefSize(311, 40);

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

        // Buttons for CRUD operations
        btnSubmit = new Button("Submit");
        btnSubmit.relocate(645, 700);

        btnUpdate = new Button("Update");
        btnUpdate.relocate(750, 700);

        btnDelete = new Button("Delete");
        btnDelete.relocate(855, 700);

        btnSearch = new Button("Search");
        btnSearch.relocate(960, 700);

        btnCancel = new Button("Cancel");
        btnCancel.relocate(1065, 700);

        // Event handler for the submit button (Insert)
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                StaffModel staffModel = new StaffModel();
                staffModel.setFullName(txtFullname.getText());
                staffModel.setGender(txtGender.getText());
                staffModel.setContactNo(txtContactno.getText());
                staffModel.setStaffType(txtStafftype.getText());

                boolean result = new StaffCRUD().Insert(staffModel);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Staff inserted successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert staff.");
                }
            }
        });

        // Event handler for the search button (Read)
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                int staffId = Integer.parseInt(txtStaffId.getText());
                StaffModel staffModel = new StaffCRUD().search(staffId);
                if (staffModel != null) {
                    txtFullname.setText(staffModel.getFullName());
                    txtGender.setText(staffModel.getGender());
                    txtContactno.setText(staffModel.getContactNo());
                    txtStafftype.setText(staffModel.getStaffType());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Staff not found.");
                }
            }
        });

        // Event handler for the update button (Update)
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                StaffModel staffModel = new StaffCRUD().search(Integer.parseInt(txtStaffId.getText()));
                if (staffModel != null) {
                    staffModel.setFullName(txtFullname.getText());
                    staffModel.setGender(txtGender.getText());
                    staffModel.setContactNo(txtContactno.getText());
                    staffModel.setStaffType(txtStafftype.getText());

                    boolean result = new StaffCRUD().Update(staffModel);
                    if (result) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Staff updated successfully!");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Failed to update staff.");
                    }
                }
            }
        });

        // Event handler for the delete button (Delete)
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                int staffId = Integer.parseInt(txtStaffId.getText());
                boolean result = new StaffCRUD().Delete(staffId);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Staff deleted successfully!");
                    //clearForm();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete staff.");
                }
            }
        });

        pane.getChildren().addAll(lblTitle, lblfullname, txtFullname, lblGender, txtGender, lblContactno, txtContactno, 
                                   lblStafftype, txtStafftype, btnSubmit, btnUpdate, btnDelete, btnSearch, btnCancel, 
                                   sidebar, lblSidebarTitle, lblStaffId, txtStaffId);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    private void clearForm() {
//        txtFullname.clear();
//        txtGender.clear();
//        txtContactno.clear();
//        txtStafftype.clear();
//        txtStaffId.clear();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
