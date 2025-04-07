package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import library.DbConnection;
import model.UserManagementModel;

public class UserCRUD extends DbConnection {

	// Login method to validate user credentials
	public UserManagementModel login(String username, String password) {
		UserManagementModel user = null;
		String SQL = "SELECT User_Id,Username,Password,UserType FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {

			pStat.setString(1, username);
			pStat.setString(2, password);

			ResultSet resultSet = pStat.executeQuery();
			if (resultSet.next()) {
				user = new UserManagementModel();
				user.setUserId(resultSet.getInt("User_Id"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword(resultSet.getString("Password"));
				user.setUserType(resultSet.getString("UserType"));
			}
			pStat.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return user;
	}

	public void insert(UserManagementModel user) {
		try {
			String SQL = "INSERT INTO users values (1,?, ?, ?, ?)";
			PreparedStatement pStat = connect().prepareStatement(SQL);
			pStat.setString(1, user.getUsername());
			pStat.setString(2, user.getPassword());
			pStat.setString(3, user.getUserType());
			pStat.setInt(4, user.getAssignedId());
			
			pStat.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<UserManagementModel> getAll() {
		List<UserManagementModel> users = new ArrayList<>();
		String SQL = "SELECT User_Id, Username, UserType FROM USERS";

		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			ResultSet resultSet = pStat.executeQuery();
			while (resultSet.next()) {
				UserManagementModel user = new UserManagementModel();
				user.setUserId(resultSet.getInt("User_Id"));
				user.setUsername(resultSet.getString("Username"));
				user.setUserType(resultSet.getString("UserType"));
				users.add(user);
			}
			pStat.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return users;
	}
}
