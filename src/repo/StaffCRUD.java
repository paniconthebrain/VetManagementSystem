package repo;

import java.sql.PreparedStatement;
import java.util.List;

import interfaces.InfStaffCRUD;
import library.DbConnection;
import model.StaffModel;

public class StaffCRUD extends DbConnection implements InfStaffCRUD{

	@Override
	public boolean Insert(StaffModel staffModel) {
		boolean result = false;

		PreparedStatement pStat;
		String sql = "Insert into Staff(Staff_Name,Gender,Contact_No,Staff_Type) value(?,?,?,?);";
		
		try {
			pStat = connect().prepareStatement(sql);
//			pStat.setInt(1, staffModel.getStaffId());
			pStat.setString(2, staffModel.getFullName());
			pStat.setString(3, staffModel.getGender());
			pStat.setString(4, staffModel.getContactNo());
			pStat.setString(5, staffModel.getStaffType());
			pStat.executeUpdate();
			pStat.close();
		}catch(Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		
		return false;
	}

	@Override
	public StaffModel search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Update(StaffModel staffmodel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List All() {
		// TODO Auto-generated method stub
		return null;
	}

}
