package library;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class PDFGeneratorService {

    // Universal method to generate PDFs dynamically and save them in the Downloads folder
    public static void generatePDF(Map<String, String> data, String fileName, String screenName) {
        try {
            // Get the user's home directory (this works across OSes)
            String userHome = System.getProperty("user.home");

            // Define the folder path to the Downloads directory
            String directoryPath = userHome + "/Downloads/pdfReport/";

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            // Define the full file path (combining directory and file name)
            String filePath = directoryPath + fileName;

            // Create the document and write to the file path
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Add Logo to the PDF (using path from AppSettings)
            String logoPath = System.getProperty("user.dir") + AppSettings.logo; // Adjust path to your actual project directory
            Image logo = Image.getInstance(logoPath);
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(50, 50); // Resize the logo (optional)
            document.add(logo);

            // Add Company Name (from AppSettings interface)
            Paragraph companyName = new Paragraph(AppSettings.companyName,
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22));
            companyName.setAlignment(Element.ALIGN_CENTER);
            document.add(companyName);

            // Add a small space between the logo, company name, and the rest of the content
            document.add(new Paragraph("\n"));

            // Create a dynamic title for the PDF using pet name from the data map
            String title = screenName;
            document.add(new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph("\n"));

            // Create a table to display the data
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            // Loop through the data map and add it to the table
            for (Map.Entry<String, String> entry : data.entrySet()) {
                table.addCell(entry.getKey());
                table.addCell(entry.getValue());
            }

            // Add the table to the document
            document.add(table);
            document.close();

            // Output message to console
            System.out.println("PDF Created: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static boolean generatePDFTable(List<Map<String, String>> reportData, String string, String string2,
			String[] strings) {
		// TODO Auto-generated method stub
		return false;
	}
}
