package userInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import library.AppSettings;
import model.CompanySetupModel;
import repo.CompanyCRUD;

/**
 * JavaFX UI class for registering company details in the Veterinary Management
 * System.
 */
public class CompanySetupPage extends Application {

	private ImageView imgLogo; // Views the uploaded logo
	private String logoPath; // Stores the path to the uploaded logo image

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Declare all UI components
		Label lblHeading, lblCompanyRegisteredCode, lblCompanyName, lblAddress, lblContactNo, lblRegisteredDate;
		TextField txtCompanyRegisteredCode, txtCompanyName, txtAddress, txtContactNo;
		DatePicker dpRegisteredDate;
		Button btnSubmit, btnCancel, btnChooseLogo;
		// Main pane and scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Company Register");
		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);

		// Fonts using AppSettings for consistency
		Font headingFont = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Font labelFont = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font textFont = new Font(AppSettings.textBoxFont, AppSettings.textBoxFontSize);

		// Sidebar
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Labels
		Label lblSidebarTitle = new Label(AppSettings.companyName);
		lblSidebarTitle.setFont(new Font("Arial", 30));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.setMaxWidth(200);
		lblSidebarTitle.relocate(50, 50);
		lblSidebarTitle.setWrapText(true);

		// Heading Label
		lblHeading = new Label("Company Setup");
		lblHeading.relocate(346, 44);
		lblHeading.setFont(headingFont);

		// Company Code Label and TextField
		lblCompanyRegisteredCode = new Label("Company Code");
		lblCompanyRegisteredCode.relocate(450, 168);
		lblCompanyRegisteredCode.setFont(labelFont);

		txtCompanyRegisteredCode = new TextField();
		txtCompanyRegisteredCode.relocate(645, 168);
		txtCompanyRegisteredCode.setPrefSize(100, AppSettings.textBoxHeight);
		txtCompanyRegisteredCode.setFont(textFont);

		// Company Name Label and TextField
		lblCompanyName = new Label("Company Name");
		lblCompanyName.relocate(450, 238);
		lblCompanyName.setFont(labelFont);

		txtCompanyName = new TextField();
		txtCompanyName.relocate(645, 238);
		txtCompanyName.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtCompanyName.setFont(textFont);

		// Address Label and TextField
		lblAddress = new Label("Office Address");
		lblAddress.relocate(450, 308);
		lblAddress.setFont(labelFont);

		txtAddress = new TextField();
		txtAddress.relocate(645, 308);
		txtAddress.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtAddress.setFont(textFont);

		// Contact Number Label and TextField with validation
		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(450, 380);
		lblContactNo.setFont(labelFont);

		txtContactNo = new TextField();
		txtContactNo.relocate(645, 380);
		txtContactNo.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtContactNo.setFont(textFont);
		// Restrict input to digits and max 10 characters
		txtContactNo.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				txtContactNo.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if (newValue.length() > 10) {
				txtContactNo.setText(oldValue);
			}
		});

		// Registered Date Label and DatePicker
		lblRegisteredDate = new Label("Registered Date");
		lblRegisteredDate.relocate(450, 452);
		lblRegisteredDate.setFont(labelFont);

		dpRegisteredDate = new DatePicker();
		dpRegisteredDate.relocate(645, 452);
		dpRegisteredDate.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		// Logo ImageView and upload button
		imgLogo = new ImageView();
		imgLogo.setFitWidth(100);
		imgLogo.setFitHeight(100);
		imgLogo.relocate(450, 524);

		btnChooseLogo = new Button("Choose Logo");
		btnChooseLogo.relocate(645, 524);
		btnChooseLogo.setOnAction(event -> uploadLogo(primaryStage));
		btnChooseLogo.setStyle(AppSettings.btnContent);
		btnChooseLogo.setOnMouseEntered(e -> btnChooseLogo.setEffect(new DropShadow()));
		btnChooseLogo.setOnMouseExited(e -> btnChooseLogo.setEffect(null));

		// Submit button with validation and saving to database
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 580);
		btnSubmit.setPrefSize(80, 30);
		btnSubmit.setStyle(AppSettings.btnPrimary);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setEffect(new DropShadow()));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setEffect(null));
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// Form validation
				if (txtCompanyRegisteredCode.getText().isEmpty()) {
					showAlert(AlertType.ERROR, "Error", "Please enter company code");
					return;
				}
				if (txtCompanyName.getText().isEmpty()) {
					showAlert(AlertType.ERROR, "Error", "Please enter company name");
					return;
				}
				if (txtAddress.getText().isEmpty()) {
					showAlert(AlertType.ERROR, "Error", "Please enter company address");
					return;
				}
				if (txtContactNo.getText().length() != 10) {
					showAlert(AlertType.ERROR, "Error", "Contact number must be 10 digits");
					return;
				}
				if (dpRegisteredDate.getValue() == null) {
					showAlert(AlertType.ERROR, "Error", "Please select registered date");
					return;
				}

				// Fill the model with values
				CompanySetupModel companySetup = new CompanySetupModel();
				companySetup.setCompanyRegisteredCode(txtCompanyRegisteredCode.getText());
				companySetup.setCompanyName(txtCompanyName.getText());
				companySetup.setCompanyAddress(txtAddress.getText());
				companySetup.setCompanyContactNo(txtContactNo.getText());
				companySetup.setCompanyRegisteredDate(dpRegisteredDate.getValue());
				companySetup.setCompanyLogoPath(logoPath);

				// Save to Database
				boolean result = new CompanyCRUD().insert(companySetup);

				Alert alert = new Alert(result ? AlertType.INFORMATION : AlertType.ERROR);
				alert.setTitle(result ? "Success" : "Error");
				alert.setHeaderText(result ? "Company Registered Successfully" : "Error Registering Company");
				alert.setContentText(result ? "Company has been registered successfully!" : "Please try again.");
				alert.showAndWait();
				
				// Clear form on success
				if (result) {
					txtCompanyRegisteredCode.clear();
					txtCompanyName.clear();
					txtAddress.clear();
					txtContactNo.clear();
					dpRegisteredDate.setValue(null);
					imgLogo.setImage(null);
					logoPath = null;
				}
			}
		});

		// Cancel button to close the window
		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 580);
		btnCancel.setPrefSize(80, 30);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setOnMouseEntered(e -> btnCancel.setEffect(new DropShadow()));
		btnCancel.setOnMouseExited(e -> btnCancel.setEffect(null));
		btnCancel.setOnAction(event -> primaryStage.close());

		// Add all components to pane
		pane.getChildren().addAll(sidebar, lblSidebarTitle, lblHeading, lblCompanyRegisteredCode,
				txtCompanyRegisteredCode, lblCompanyName, txtCompanyName, lblAddress, txtAddress, lblContactNo,
				txtContactNo, lblRegisteredDate, dpRegisteredDate, imgLogo, btnChooseLogo, btnSubmit, btnCancel);

		primaryStage.show();
	}

	/**
	* Allows the user to select an image file from their system and sets it as the company logo.
	* The selected image is copied to the local "img/" directory, and its path is stored for later use.
	*
	* This method is based on guidance from StackOverflow and ChatGPT assistance.
	*
	* @param stage the current Stage used to show the FileChooser dialog
	*/
	private void uploadLogo(Stage stage) {
		FileChooser fileChooser = new FileChooser();

		// Limit file selection to image formats only
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

		// Limit file selection to image formats only
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile == null)
			return;
		// Ensure the destination folder exists
		File destFolder = new File("img/");
		if (!destFolder.exists())
			destFolder.mkdir();
		// Create a new file object in the destination directory with the same name
		File destFile = new File(destFolder, selectedFile.getName());
		try {
			// Copy the selected file to the 'img/' folder, replacing if it already exists
			Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			// Save the new file path and display the image in the ImageView
			logoPath = destFile.getPath();
			imgLogo.setImage(new Image(destFile.toURI().toString()));
		} catch (IOException e) {
			// Show an error alert if copying fails
			showAlert(AlertType.ERROR, "Error", "Failed to upload logo: " + e.getMessage());
		}
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}