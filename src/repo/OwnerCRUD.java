package repo;

import java.sql.PreparedStatement;
import java.util.List;

import interfaces.InfOwnerCRUD;
import library.DbConnection;
import model.OwnerModel;

public class OwnerCRUD extends DbConnection implements InfOwnerCRUD {

	@Override
	public boolean Insert(OwnerModel ownerModel) {
		boolean result = false;

		PreparedStatement pStat;
		String sql = "Insert into customers(Customer_Name,Address,Mobile_No,Email_Address,Status) value(?,?,?,?,?,1);";
		
		try {
			pStat = connect().prepareStatement(sql);
//			pStat.setInt(1, staffModel.getStaffId());
			pStat.setString(1, ownerModel.getFullName());
			pStat.setString(2, ownerModel.getAddress());
			pStat.setString(3, ownerModel.getContactNo());
			pStat.setString(4, ownerModel.getEmail());
			pStat.executeUpdate();
			pStat.close();
		}catch(Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		
		return false;
	}

	@Override
	public OwnerModel search() {
		return null;
	}

	@Override
	public boolean Update(OwnerModel ownerModel) {
		return false;
	}

	@Override
	public List All() {
		return null;
	}

	
}
