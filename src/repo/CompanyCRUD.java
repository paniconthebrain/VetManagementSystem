package repo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import library.DbConnection;
import model.CompanySetupModel;
/**
 * CompanyCRUD class handles all database operations related to Comapny Details.
 * It includes methods for Insert new Company, and retrieving Company Information and Delete.
 */
public class CompanyCRUD extends DbConnection {
	/**
     * Insert a new company into the database.
     */
	public boolean insert(CompanySetupModel company) {
		boolean result = false;
		// SQL query to insert a new Company into the database
		String sql = "INSERT INTO Company (companyCode, companyName, companyAddress, companyContactNo, companyRegisteredDate, companyLogoPath) VALUES (?, ?, ?, ?, ?, ?);";
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			// Set parameters for the prepared statement
			pStat.setString(1, company.getCompanyRegisteredCode());
			pStat.setString(2, company.getCompanyName());
			pStat.setString(3, company.getCompanyAddress());
			pStat.setString(4, company.getCompanyContactNo());

			// Convert LocalDate to java.sql.Date
			LocalDate registeredDate = company.getCompanyRegisteredDate();
			if (registeredDate != null) {
				pStat.setDate(5, Date.valueOf(registeredDate)); // Convert LocalDate to java.sql.Date
			} else {
				pStat.setDate(5, null); // Handle null dates if necessary
			}

			pStat.setString(6, company.getCompanyLogoPath());
			pStat.executeUpdate(); // Execute the insert query
			result = true;
		} catch (Exception ex) {
			// If an exception occurs during insert, print the error message
			System.out.println("Insert Error: " + ex.getMessage());
		}
		return result;
	}

	// Read (Fetch by ID)
	public CompanySetupModel getCompanyById(int companyId) {
		// SQL query to Retrieve Company info from the database
		String sql = "SELECT * FROM Company order by companyId desc limit 1";
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			 // Set the company ID parameter for the query
			//pStat.setInt(1, companyId);
			ResultSet rs = pStat.executeQuery();
			if (rs.next()) {
				// If a company is found, return a new CompanySetupModel object populated with the data
				return new CompanySetupModel(rs.getInt("companyId"), rs.getString("companyCode"),
						rs.getString("companyName"), rs.getString("companyAddress"), rs.getString("companyContactNo"),
						rs.getDate("companyRegisteredDate").toLocalDate(), // Convert to LocalDate
						rs.getString("companyLogoPath"));
			}
		} catch (Exception ex) {
			// If an exception occurs during fetch, print the error message
			System.out.println("Fetch Error: " + ex.getMessage());
		}
		return null;
	}

	/**
    * Delete a company from the database by its ID.
    */ 
	public boolean delete(int companyId) {
		// Initialize the result to false (indicating failure by default)
		boolean result = false;
		// SQL query to delete the company by its ID
		String sql = "DELETE FROM Company WHERE companyId = ?";
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, companyId); // Set the company ID parameter for the query
			pStat.executeUpdate();  // Execute the delete query
			result = true;// If the delete was successful, set result to true
		} catch (Exception ex) {
			System.out.println("Delete Error: " + ex.getMessage());
		}
		return result; // Return the result indicating success or failure
	}

}
