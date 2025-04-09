package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import library.DbConnection;
import model.UserManagementModel;

/**
 * UserCRUD class handles all database operations related to user management.
 * It includes methods for logging in, inserting new users, and retrieving user data.
 */
public class UserCRUD extends DbConnection {

	// Login method to validate user credentials
	public UserManagementModel login(String username, String password) {
		UserManagementModel user = null;
		String SQL = "SELECT User_Id,Username,Password,UserType FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";

		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {

			pStat.setString(1, username);
			pStat.setString(2, password);
			// Execute the query and get the result set
			ResultSet resultSet = pStat.executeQuery();
			// If a user is found, create a UserManagementModel object and populate it
			if (resultSet.next()) {
				user = new UserManagementModel();
				user.setUserId(resultSet.getInt("User_Id"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword(resultSet.getString("Password"));
				user.setUserType(resultSet.getString("UserType"));
			}
			pStat.close(); // Close the PreparedStatement after execution
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return user;
	}
	/**
	 * Method to insert a new user into the database.
	 */
	public void insert(UserManagementModel user) {
		try {
			// SQL query to insert a new user into the database
			String SQL = "INSERT INTO users values (1,?, ?, ?, ?)";
			PreparedStatement pStat = connect().prepareStatement(SQL);
			 // Set the parameters for the SQL query
			pStat.setString(1, user.getUsername());
			pStat.setString(2, user.getPassword());
			pStat.setString(3, user.getUserType());
			pStat.setInt(4, user.getAssignedId());
			// Execute the update (insert the new user)
			pStat.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Method to retrieve all users from the database.
	 */
	public List<UserManagementModel> getAll() {
		List<UserManagementModel> users = new ArrayList<>();
		// SQL query to fetch all users from the database
		String SQL = "SELECT User_Id, Username, UserType FROM USERS";

		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			ResultSet resultSet = pStat.executeQuery();
			 // Loop through the result set and populate the users list with UserManagementModel objects
			while (resultSet.next()) {
				UserManagementModel user = new UserManagementModel();
				user.setUserId(resultSet.getInt("User_Id"));
				user.setUsername(resultSet.getString("Username"));
				user.setUserType(resultSet.getString("UserType"));
				users.add(user);
			}
			pStat.close();  // Close the PreparedStatement after execution
		} catch (Exception ex) {
			// Print any errors that occur during database interaction
			System.out.println("Error: " + ex.getMessage());
		}
		return users;
	}
}
