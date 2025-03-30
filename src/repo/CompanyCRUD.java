package repo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import library.DbConnection;
import model.CompanySetupModel;

public class CompanyCRUD extends DbConnection {
    // Create (Insert)
	public boolean insert(CompanySetupModel company) {
	    boolean result = false;
	    String sql = "INSERT INTO Company (companyCode, companyName, companyAddress, companyContactNo, companyRegisteredDate, companyLogoPath) VALUES (?, ?, ?, ?, ?, ?);";
	    try (PreparedStatement pStat = connect().prepareStatement(sql)) {
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
	        pStat.executeUpdate();
	        result = true;
	    } catch (Exception ex) {
	        System.out.println("Insert Error: " + ex.getMessage());
	    }
	    return result;
	}

    // Read (Fetch by ID)
	public CompanySetupModel getCompanyById(int companyId) {
	    String sql = "SELECT * FROM Company WHERE companyId = ?";
	    try (PreparedStatement pStat = connect().prepareStatement(sql)) {
	        pStat.setInt(1, companyId);
	        ResultSet rs = pStat.executeQuery();
	        if (rs.next()) {
	            return new CompanySetupModel(
	                rs.getInt("companyId"),
	                rs.getString("companyCode"),
	                rs.getString("companyName"),
	                rs.getString("companyAddress"),
	                rs.getString("companyContactNo"),
	                rs.getDate("companyRegisteredDate").toLocalDate(), // Convert to LocalDate
	                rs.getString("companyLogoPath")
	            );
	        }
	    } catch (Exception ex) {
	        System.out.println("Fetch Error: " + ex.getMessage());
	    }
	    return null;
	}

    // Delete
    public boolean delete(int companyId) {
        boolean result = false;
        String sql = "DELETE FROM Company WHERE companyId = ?";
        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setInt(1, companyId);
            pStat.executeUpdate();
            result = true;
        } catch (Exception ex) {
            System.out.println("Delete Error: " + ex.getMessage());
        }
        return result;
    }

}
