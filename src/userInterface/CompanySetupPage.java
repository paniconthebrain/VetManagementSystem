package userInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import interfaces.AppSettings;
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
import model.CompanySetupModel;
import repo.CompanyCRUD;

public class CompanySetupPage extends Application implements AppSettings {
	private ImageView imgLogo;
	private String logoPath;
	Alert alert;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lblHeading, lblCompanyRegisteredCode, lblCompanyName, lblAddress, lblContactNo, lblRegisteredDate;
		TextField txtCompanyRegisteredCode, txtCompanyName, txtAddress, txtContactNo;
		DatePicker dpRegisteredDate;
		Button btnSubmit, btnCancel, btnChooseLogo;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Company Register");
		primaryStage.setScene(scene);
		primaryStage.setWidth(subPageWidth);
		primaryStage.setHeight(subPageHeight);

		Font headingFont = new Font(mainFont1, mainFont1Size);
		Font font1 = new Font(subFont, subFontSize);
		Font font2 = new Font(textBoxFont, textBoxFontSize);

		// Sidebar Background
		Rectangle sidebar = new Rectangle(0, 0, 250, 800);
		sidebar.setFill(Color.BLACK);

		// Sidebar Title (Move Down if Name is Long)
		Label lblSidebarTitle = new Label("ABC CLINIC");
		lblSidebarTitle.setFont(new Font("Arial", 24));
		lblSidebarTitle.setTextFill(Color.WHITE);
		lblSidebarTitle.relocate(50, lblSidebarTitle.getText().length() > 15 ? 80 : 50);

		lblHeading = new Label("Company Setup");
		lblHeading.relocate(346, 44);
		lblHeading.setFont(headingFont);

		lblCompanyRegisteredCode = new Label("Company Code");
		lblCompanyRegisteredCode.relocate(450, 168);
		lblCompanyRegisteredCode.setFont(font1);

		txtCompanyRegisteredCode = new TextField();
		txtCompanyRegisteredCode.relocate(645, 168);
		txtCompanyRegisteredCode.setPrefSize(100, textBoxHeight);
		txtCompanyRegisteredCode.setFont(font2);

		lblCompanyName = new Label("Company Name");
		lblCompanyName.relocate(450, 238);
		lblCompanyName.setFont(font1);

		txtCompanyName = new TextField();
		txtCompanyName.relocate(645, 238);
		txtCompanyName.setPrefSize(textBoxWidth, textBoxHeight);
		txtCompanyName.setFont(font2);

		lblAddress = new Label("Office Address");
		lblAddress.relocate(450, 308);
		lblAddress.setFont(font1);

		txtAddress = new TextField();
		txtAddress.relocate(645, 308);
		txtAddress.setPrefSize(textBoxWidth, textBoxHeight);
		txtAddress.setFont(font2);

		lblContactNo = new Label("Contact No");
		lblContactNo.relocate(450, 380);
		lblContactNo.setFont(font1);

		txtContactNo = new TextField();
		txtContactNo.relocate(645, 380);
		txtContactNo.setPrefSize(textBoxWidth, textBoxHeight);
		txtContactNo.setFont(font2);

		lblRegisteredDate = new Label("Registered Date");
		lblRegisteredDate.relocate(450, 452);
		lblRegisteredDate.setFont(font1);

		dpRegisteredDate = new DatePicker();
		dpRegisteredDate.relocate(645, 452);
		dpRegisteredDate.setPrefSize(textBoxWidth, textBoxHeight);

		LocalDate registeredDate = dpRegisteredDate.getValue();

		// Logo Placeholder
		imgLogo = new ImageView();
		imgLogo.setFitWidth(100);
		imgLogo.setFitHeight(100);
		imgLogo.relocate(450, 524);

		btnChooseLogo = new Button("Choose Logo");
		btnChooseLogo.relocate(645, 524);
		btnChooseLogo.setOnAction(event -> uploadLogo(primaryStage));
		btnChooseLogo.setStyle(btnContent);

		btnChooseLogo.setOnMouseEntered(e -> btnChooseLogo.setEffect(new DropShadow()));
		btnChooseLogo.setOnMouseExited(e -> btnChooseLogo.setEffect(null));

		btnSubmit = new Button("Submit");
		btnSubmit.relocate(645, 580);
		btnSubmit.setPrefSize(80, 30);
		btnSubmit.setStyle(btnPrimary);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setEffect(new DropShadow()));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setEffect(null));

		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				CompanySetupModel companySetup = new CompanySetupModel();

				companySetup.setCompanyRegisteredCode(txtCompanyRegisteredCode.getText());
				companySetup.setCompanyName(txtCompanyName.getText());
				companySetup.setCompanyAddress(txtAddress.getText());
				companySetup.setCompanyContactNo(txtContactNo.getText());

				// Get the date from the DatePicker and set it as LocalDate
				LocalDate registeredDate = dpRegisteredDate.getValue();
				companySetup.setCompanyRegisteredDate(registeredDate); // Set the LocalDate

				companySetup.setCompanyLogoPath(logoPath);

				// Insert the company data
				boolean result = new CompanyCRUD().insert(companySetup);
				if (result) {
					// System.out.println("Company registered successfully!");
					alert.setTitle("Success");
					alert.setHeaderText(null);
					alert.setContentText("Company registered successfully!");

				}
			}
		});

		btnCancel = new Button("Cancel");
		btnCancel.relocate(768, 580);
		btnCancel.setPrefSize(80, 30);
		btnCancel.setStyle(btnSecondary);
		btnCancel.setOnMouseEntered(e -> btnCancel.setEffect(new DropShadow()));
		btnCancel.setOnMouseExited(e -> btnCancel.setEffect(null));
		btnCancel.setOnAction(event -> primaryStage.close());

		pane.getChildren().addAll(sidebar, lblSidebarTitle, imgLogo, btnChooseLogo);
		pane.getChildren().addAll(lblHeading, lblCompanyRegisteredCode, lblCompanyName, txtCompanyRegisteredCode,
				txtCompanyName);
		pane.getChildren().addAll(lblAddress, txtAddress, lblContactNo, txtContactNo);
		pane.getChildren().addAll(lblRegisteredDate, dpRegisteredDate, btnSubmit, btnCancel);

		primaryStage.show();
	}

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

	public static void main(String[] args) {
		launch(args);
	}
}
