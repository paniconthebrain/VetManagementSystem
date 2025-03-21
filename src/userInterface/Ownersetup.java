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
import model.OwnerModel;
import repo.OwnerCRUD;

public class Ownersetup extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label lblTitle, lblOwnerID,lblOwnerName, lblContactNo, lblEmail, lblAddress, lblNickname, lblPetBreed, lblDateOfBirth;
        TextField txtOwnerID,txtOwnerName, txtContactNo, txtEmail, txtAddress, txtNickname, txtPetBreed, txtDateOfBirth;
        Button btnSubmit, btnDelete, btnUpdate, btnView;

        Font font = new Font("Arial", 18);
        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();

        // Sidebar
        Rectangle sidebar = new Rectangle(0, 0, 250, 600);
        sidebar.setFill(Color.LIGHTGRAY);

        Label lblSidebarTitle = new Label("ABC CLINIC");
        lblSidebarTitle.setFont(new Font("Arial", 24));
        lblSidebarTitle.setTextFill(Color.BLACK);
        lblSidebarTitle.relocate(50, 50);

        // Labels and Text Fields
        lblTitle = new Label("Owner Setup");
        lblTitle.relocate(300, 50);
        lblTitle.setFont(font);

        lblOwnerID = new Label("Owner id:");
        lblOwnerID.relocate(300, 80);
        lblOwnerID.setFont(font);
        
        txtOwnerID = new TextField("");
        txtOwnerID.relocate(450, 80);
        txtOwnerID.setPrefSize(200, 25);
        
        
        lblOwnerName = new Label("Owner Name:");
        lblOwnerName.relocate(300, 120);
        lblOwnerName.setFont(font);
        txtOwnerName = new TextField();
        txtOwnerName.relocate(450, 120);
        txtOwnerName.setPrefSize(200, 25);

        lblContactNo = new Label("Contact No:");
        lblContactNo.relocate(300, 160);
        lblContactNo.setFont(font);
        txtContactNo = new TextField();
        txtContactNo.relocate(450, 160);
        txtContactNo.setPrefSize(200, 25);

        lblEmail = new Label("Email:");
        lblEmail.relocate(300, 200);
        lblEmail.setFont(font);
        txtEmail = new TextField();
        txtEmail.relocate(450, 200);
        txtEmail.setPrefSize(200, 25);

        lblAddress = new Label("Address:");
        lblAddress.relocate(300, 240);
        lblAddress.setFont(font);
        txtAddress = new TextField();
        txtAddress.relocate(450, 240);
        txtAddress.setPrefSize(200, 25);

        lblNickname = new Label("Pet Nickname:");
        lblNickname.relocate(300, 280);
        lblNickname.setFont(font);
        txtNickname = new TextField();
        txtNickname.relocate(450, 280);
        txtNickname.setPrefSize(200, 25);

        lblPetBreed = new Label("Pet Breed:");
        lblPetBreed.relocate(300, 320);
        lblPetBreed.setFont(font);
        txtPetBreed = new TextField();
        txtPetBreed.relocate(450, 320);
        txtPetBreed.setPrefSize(200, 25);

        lblDateOfBirth = new Label("Date of Birth:");
        lblDateOfBirth.relocate(300, 360);
        lblDateOfBirth.setFont(font);
        txtDateOfBirth = new TextField();
        txtDateOfBirth.relocate(450, 360);
        txtDateOfBirth.setPrefSize(200, 25);

        // Buttons
        btnSubmit = new Button("Insert");
        btnSubmit.relocate(300, 420);
        btnSubmit.setPrefSize(100, 25);

        btnUpdate = new Button("Update");
        btnUpdate.relocate(410, 420);
        btnUpdate.setPrefSize(100, 25);

        btnDelete = new Button("Delete");
        btnDelete.relocate(520, 420);
        btnDelete.setPrefSize(100, 25);

        btnView = new Button("View");
        btnView.relocate(630, 420);
        btnView.setPrefSize(100, 25);

        // Event Handlers
     // Event Handlers
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
                alert.setHeaderText(result ? "Owner Added Successfully" : "Error Adding Owner");
                alert.setContentText(result ? "Owner details have been added." : "Please try again.");
                alert.showAndWait();
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int ownerId = Integer.parseInt(txtOwnerID.getText()); // Ensure there is a txtOwnerId input for ID
                    boolean result = new OwnerCRUD().delete(ownerId);

                    Alert alert = new Alert(result ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                    alert.setTitle(result ? "Success" : "Error");
                    alert.setHeaderText(result ? "Owner Deleted Successfully" : "Error Deleting Owner");
                    alert.setContentText(result ? "Owner with ID " + ownerId + " has been deleted." : "Please try again.");
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

        btnView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int ownerId = Integer.parseInt(txtOwnerID.getText()); // Ensure there is a txtOwnerId input for ID
                    OwnerModel ownerModel = new OwnerCRUD().searchByName(ownerId);

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
        pane.getChildren().addAll(lblTitle,lblOwnerID, txtOwnerID, lblOwnerName, txtOwnerName, lblContactNo, txtContactNo, lblEmail, txtEmail,
                lblAddress, txtAddress, lblNickname, txtNickname, lblPetBreed, txtPetBreed, lblDateOfBirth,
                txtDateOfBirth, btnSubmit, btnUpdate, btnDelete, btnView, sidebar, lblSidebarTitle);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
