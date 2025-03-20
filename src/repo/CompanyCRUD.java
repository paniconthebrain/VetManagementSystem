package repo;

import java.sql.PreparedStatement;

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
			pStat.executeUpdate();
			pStat.close();
			result = true;
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}

		return false;
	}

}
