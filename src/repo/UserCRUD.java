package repo;

import java.sql.PreparedStatement;
import java.util.List;

import interfaces.InfUserManagementCRUD;
import library.DbConnection;
import model.UserManagementModel;

public class UserCRUD extends DbConnection implements InfUserManagementCRUD {

	@Override
	public boolean Insert(UserManagementModel user) {
		boolean result = false; 
		PreparedStatement pStat;
		
		String SQL = "INSERT INTO USERS VALUES(?,?,?);";
		try {
			pStat = connect().prepareStatement(SQL);
			pStat.setInt(1,user.getUserId());
			pStat.setString(2, user.getFullName());
			pStat.setString(3, user.getPassword());
		}catch(Exception ex) {
			System.out.println("Error : " +ex.getMessage());
		}
		
		return false;
	}

	@Override
	public UserManagementModel Search(int userId) {
		return null;
	}

	@Override
	public boolean Update(UserManagementModel user) {
		return false;
	}

	@Override
	public List All() {
		return null;
	}

}
