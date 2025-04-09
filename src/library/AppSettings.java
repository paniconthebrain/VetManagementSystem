package library;

/**
 * AppSettings class stores application-wide constants for the Veterinary Management System.
 * This includes UI styles, fonts, dimensions, and branding details.
 * Centralizing these settings helps maintain a consistent look and feel throughout the application.
 */
public class AppSettings {		

	// Application branding and identity
	public static final String softwareName = "Vetinary Management System"; // Name of the software
	public static final String companyName = "Mobile Veterinary Consultancy Service"; // Company name
	public static final String logo = "/img/Vetlogo.jpg"; // Path to the logo image

	// Button styling
	public static final String btnPrimary = "-fx-background-color: #292F36; -fx-text-fill: white; -fx-font-size: 16px;"; // Main action buttons
	public static final String btnSecondary = "-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 16px;"; // Danger or cancel actions
	public static final String btnStage1 = "-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-font-size: 16px;"; // Used for first-stage navigation or steps
	public static final String btnStage2 = "-fx-background-color: #E09F3E; -fx-text-fill: white; -fx-font-size: 16px;"; // Used for second-stage navigation or steps
	public static final String btnContent = "-fx-background-color: #344966; -fx-text-fill: white; -fx-font-size: 14px;"; // Buttons within content sections

	// Dimensions for sub-pages
	public static final Integer subPageWidth = 1300; // Standard width for internal application pages
	public static final Integer subPageHeight = 800; // Standard height for internal application pages

	// Font settings
	public static final String subFont = "Roboto"; // Font used in sub-sections
	public static final Integer subFontSize = 24; // Size for subsection fonts
	public static final String mainFont1 = "Roboto"; // Primary font for headers
	public static final Integer mainFont1Size = 38; // Size for main header font

	public static final String textBoxFont = "Roboto"; // Font used in input fields
	public static final Integer textBoxFontSize = 18; // Size for text in input fields

	// ComboBox styling
	public static final String comboBox = "-fx-font-size: 14px; -fx-padding: 5px; -fx-background-color: white; -fx-border-color: #4CAF50; -fx-border-radius: 5px;";

	// TextBox dimensions
	public static final int textBoxWidth = 310; // Width of input text fields
	public static final int textBoxHeight = 40; // Height of input text fields	
}
