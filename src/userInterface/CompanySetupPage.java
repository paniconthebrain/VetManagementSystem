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

public class CompanySetupPage extends Application {

	// Display the uploaded company logo
	private ImageView imgLogo;
	// Stores the file path of the uploaded Logo image
	private String logoPath;
	// Alert Object used to Show dialogue message to the users
	Alert alert;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Display label for Page Name, Company Code, Company Name, Company Address,
		// Contact No, and Registered Date
		Label lblHeading, lblCompanyRegisteredCode, lblCompanyName, lblAddress, lblContactNo, lblRegisteredDate;
		// TextField for taking input from User for unqiue Company Code, Company Name,
		// address, and contact no;
		TextField txtCompanyRegisteredCode, txtCompanyName, txtAddress, txtContactNo;
		// Datepicker for taking input from user
		DatePicker dpRegisteredDate;
		// Button to control submit , cancel and chose logo
		Button btnSubmit, btnCancel, btnChooseLogo;

		// Set a new Layout pane and scene and Height/Length of the primary Stage for
		// the Company Registration Page
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Company Register");
		primaryStage.setScene(scene);
		primaryStage.setWidth(AppSettings.subPageWidth);
		primaryStage.setHeight(AppSettings.subPageHeight);

		// declaring of font style and font face
		Font headingFont = new Font(AppSettings.mainFont1, AppSettings.mainFont1Size);
		Font font1 = new Font(AppSettings.subFont, AppSettings.subFontSize);
		Font font2 = new Font(AppSettings.textBoxFont, AppSettings.textBoxFontSize);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Title
		Label lblSidebarTitle = new Label("");
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.relocate(50, lblSidebarTitle.getText().length() > 15 ? 80 : 50);// Wrap title if Name is Long

		// Initialize lblHeading with Company Setup text, relocate position and font
		lblHeading = new Label("Company Setup");
		lblHeading.relocate(346, 44);
		lblHeading.setFont(headingFont);

		// Initialize lblCompanyRegisteredCode with Company Code text,relocate position
		// and set font
		lblCompanyRegisteredCode = new Label("Company Code");
		lblCompanyRegisteredCode.relocate(450, 168);
		lblCompanyRegisteredCode.setFont(font1);

		// Initialize txtCompanyRegisteredCode with blank text,relocate position, text
		// Box size and set font
		txtCompanyRegisteredCode = new TextField();
		txtCompanyRegisteredCode.relocate(645, 168);
		txtCompanyRegisteredCode.setPrefSize(100, AppSettings.textBoxHeight);
		txtCompanyRegisteredCode.setFont(font2);

		// Initialize lblCompanyName with Company Name text,relocate position 
		// and set font 
		lblCompanyName = new Label("Company Name");
		lblCompanyName.relocate(450, 238);
		lblCompanyName.setFont(font1);

		// Initialize txtCompanyName with blank text,relocate position, text Box size
		// and set font
		txtCompanyName = new TextField();
		txtCompanyName.relocate(645, 238);
		txtCompanyName.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtCompanyName.setFont(font2);

		// Initialize lblAddress with Address text,relocate position and set font
		lblAddress = new Label("Office Address");
		lblAddress.relocate(450, 308);
		lblAddress.setFont(font1);

		// Initialize txtAddress with Blank text,relocate position, text Box size and
		// set font
		txtAddress = new TextField();
		txtAddress.relocate(645, 308);
		txtAddress.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtAddress.setFont(font2);

		// Initialize lblContactNo with "Contact no" Text,relocate position and set font
		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(450, 380);
		lblContactNo.setFont(font1);

		// Initialize txtContactNo with blank text,relocate position, text Box size and
		// set font
		txtContactNo = new TextField();
		txtContactNo.relocate(645, 380);
		txtContactNo.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);
		txtContactNo.setFont(font2);

		// Initialize lblRegisteredDate with "Registered Date" text,relocate position
		// and set font
		lblRegisteredDate = new Label("Registered Date");
		lblRegisteredDate.relocate(450, 452);
		lblRegisteredDate.setFont(font1);

		// Initialize Date picker ,relocate position and set font
		dpRegisteredDate = new DatePicker();
		dpRegisteredDate.relocate(645, 452);
		dpRegisteredDate.setPrefSize(AppSettings.textBoxWidth, AppSettings.textBoxHeight);

		// LocalDate registeredDate = dpRegisteredDate.getValue();

		// Logo Placeholder
		imgLogo = new ImageView();
		imgLogo.setFitWidth(100);
		imgLogo.setFitHeight(100);
		imgLogo.relocate(450, 524);

		// Initialize button with "Choose logo" , relocate position , set On action
		// event and provide button style
		btnChooseLogo = new Button("Choose Logo");
		btnChooseLogo.relocate(645, 524);
		btnChooseLogo.setOnAction(event -> uploadLogo(primaryStage));
		btnChooseLogo.setStyle(AppSettings.btnContent);

		// Set behavior of btnChoose Logo
		btnChooseLogo.setOnMouseEntered(e -> btnChooseLogo.setEffect(new DropShadow()));
		btnChooseLogo.setOnMouseExited(e -> btnChooseLogo.setEffect(null));

		// Initialize button with "Submit" , relocate position , set On action event,
		// provide button style and behaviour of button
		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 580);
		btnSubmit.setPrefSize(80, 30);
		btnSubmit.setStyle(AppSettings.btnPrimary);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setEffect(new DropShadow()));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setEffect(null));

		// Set a event Handler for submit button to collect all the text input
		// Store all the user input value into Company Setup Object, and save data using
		// CompanyCRUD
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				CompanySetupModel companySetup = new CompanySetupModel();

				// Retrive input values from textfields and Date picker.
				companySetup.setCompanyRegisteredCode(txtCompanyRegisteredCode.getText());
				companySetup.setCompanyName(txtCompanyName.getText());
				companySetup.setCompanyAddress(txtAddress.getText());
				companySetup.setCompanyContactNo(txtContactNo.getText());

				// Get the date from the DatePicker and set it as LocalDate
				LocalDate registeredDate = dpRegisteredDate.getValue();
				companySetup.setCompanyRegisteredDate(registeredDate); // Set the LocalDate

				// Set the logo path selected by the users
				companySetup.setCompanyLogoPath(logoPath);

				// Insert the company data into database using CRUD operation
				boolean result = new CompanyCRUD().insert(companySetup);
				if (result) {
					// Display success message
					alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText(null);
					alert.setContentText("Company registered successfully!");
					alert.showAndWait();
				}
				txtCompanyRegisteredCode.clear();
				txtCompanyName.clear();
				txtAddress.clear();
				txtContactNo.clear();
				
			}
		});

		// Initialize button with "Cancel" , relocate position , set On action event to
		// close the stage, provide button style and behaviour of button
		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 580);
		btnCancel.setPrefSize(80, 30);
		btnCancel.setStyle(AppSettings.btnSecondary);
		btnCancel.setOnMouseEntered(e -> btnCancel.setEffect(new DropShadow()));
		btnCancel.setOnMouseExited(e -> btnCancel.setEffect(null));
		btnCancel.setOnAction(event -> primaryStage.close());

		// Add the sidebar, SideBar Title , Image logo btnLogo
		pane.getChildren().addAll(sidebar, lblSidebarTitle, imgLogo, btnChooseLogo);
		// Add heading label , company registered code label, company name label,
		// company registered code and company name
		pane.getChildren().addAll(lblHeading, lblCompanyRegisteredCode, lblCompanyName, txtCompanyRegisteredCode,
				txtCompanyName);
		// Added Heading for lblAddress and lblContactNo and text input for address and
		// contact
		pane.getChildren().addAll(lblAddress, txtAddress, lblContactNo, txtContactNo);
		// Added Registered Date label , date picker , submit button and cancel button
		pane.getChildren().addAll(lblRegisteredDate, dpRegisteredDate, btnSubmit, btnCancel);
		// Display the primary stage to the users
		primaryStage.show();
	}

	/**
	 * Allows the user to upload a company logo image. This method opens a file
	 * chooser to select an image file (PNG, JPG, JPEG). Once selected, the image is
	 * copied into a local "img/" directory, and the logo is displayed in the
	 * ImageView. The image file path is saved for future use during form
	 * submission.
	 *
	 * @param stage The application window used to display the file chooser dialog.
	 * 
	 * This Code is taken from ChatGPT 
	 * 
	 */
	private void uploadLogo(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile == null)
			return;

		File destFolder = new File("img/");
		if (!destFolder.exists())
			destFolder.mkdir();
		File destFile = new File(destFolder, selectedFile.getName());
		try {
			Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			logoPath = destFile.getPath();
			imgLogo.setImage(new Image(destFile.toURI().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Entry point of Company Setup Page
	public static void main(String[] args) {
		launch(args);
	}
}
