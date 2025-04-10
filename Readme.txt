###############################################
		Veterinary Management System 
###############################################

Project: JavaFX Desktop Application
Module: Veterinary Management System
Students: Sakar Lal Dangol (2432689)/Krijen Shahi(2432413)

-------------------------------------------
ABOUT THIS MODULE
-------------------------------------------
Veterinary Management System allows users to insert, view, manage, and generate printable PDF reports 
of client  records. This report includes details such as  customer name, owner ID,pet id, pet name, appointment detials, users
, follow-up type, follow-up date, and remarks.

-------------------------------------------
FEATURES
-------------------------------------------
- Sidebar displaying company name.
- Simple and clean JavaFX user interface.
- User Role based Login
- Generate PDF report of all follow-up entries.

-------------------------------------------
TECHNOLOGIES USED
-------------------------------------------
- Java 17+
- JavaFX (UI Framework)
- MySQL (Database)
- JDBC (Database connectivity)
- iTextPDF (PDF generation)

-------------------------------------------
HOW TO RUN
-------------------------------------------
1. Open the project in your Java IDE (e.g., Eclipse).
2. Ensure the MySQL server is running and the database is correctly configured in DB Connection.
3. Run the `Login.java` class to launch the application.


-------------------------------------------
FILES INVOLVED
-------------------------------------------
- PDFGeneratorService.java (PDF generation utility)
- AppSettings.java (Application settings like company name)
- DbConnection.java (Database Connection with MySQL)
- MySQlScripts.sql (Create table for the project)

-------------------------------------------
NOTES
-------------------------------------------
- Make sure you have data in the `users` table in your MySQL database to login to application newly.

- Generated reports are saved as PDF files in the project root directory.
