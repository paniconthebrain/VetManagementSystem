package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import interfaces.InfCompanyCRUD;
import library.DbConnection;
import model.CompanySetupModel;


public class CompanyCRUD extends DbConnection implements InfCompanyCRUD {

	@Override
	public boolean Insert(CompanySetupModel company) {
		boolean result = false;

		PreparedStatement pStat;

		String sql = "INSERT INTO Company VALUE(?,?,?,?,?);";
		try {
			pStat = connect().prepareStatement(sql);
			pStat.setInt(1, company.getCompanyId());
			pStat.setString(2, company.getCompanyName());
			pStat.setString(3, company.getCompanyAddress());
			pStat.setString(4, company.getCompanyContactNo());
			pStat.setString(5, company.getCompanyRegisteredDate());
			
			  // Convert String date to SQL Date
	        if (company.getCompanyRegisteredDate() != null) {
	            java.sql.Date sqlDate = java.sql.Date.valueOf(company.getCompanyRegisteredDate());
	            pStat.setDate(5, sqlDate);
	        } else {
	            pStat.setNull(5, java.sql.Types.DATE);
	        }

	        int rowsAffected = pStat.executeUpdate();
	        pStat.close();

	        return rowsAffected > 0; // Return true only if rows were inserted successfully

	    } catch (Exception ex) {
	        System.out.println("Error : " + ex.getMessage());
	        return false; // Return false only when an error occurs
	    }
	}
	
	@Override
	public CompanySetupModel search(int companyId) {
		//declare	
		PreparedStatement pStat;
		Connection conn;
		ResultSet resultSet;// Virtual table
		//search
		String sqlQuery="Select * from company where user_id=?;";
		
		CompanySetupModel companySetupModel = new CompanySetupModel();
		
		//connect
		try {
			conn=connect();
			pStat = conn.prepareStatement(sqlQuery);
			pStat.setInt(1, companySetupModel.getCompanyId());
			resultSet = pStat.executeQuery();//Select
			
			while(resultSet.next()) {
				companySetupModel.setCompanyId(resultSet.getInt("companyId"));
				companySetupModel.setCompanyName(resultSet.getString("companyName"));
				companySetupModel.setCompanyAddress(resultSet.getString("companyAddress"));
				companySetupModel.setCompanyRegisteredDate(resultSet.getString("CompanyRegisteredDate"));
				companySetupModel.setCompanyContactNo(resultSet.getString("ContactNo"));
				
			}
			
		}catch(Exception ex) {
			System.out.println("Error : " +ex.getMessage());
		}
		//return
		return companySetupModel;
	}

}
