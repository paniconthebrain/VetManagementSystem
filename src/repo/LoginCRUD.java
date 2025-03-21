package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.DbConnection;
import model.LoginModel;

public class LoginCRUD extends DbConnection {

	// Create (Register a new user)
	public boolean createUser(String username, String password, String email) {
		PreparedStatement pStat = null;
		Connection conn = null;
		boolean isCreated = false;

		try {
			conn = this.connect();
			String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
			pStat = conn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password); // Plain text password
			pStat.setString(3, email);

			int result = pStat.executeUpdate();
			if (result > 0) {
				isCreated = true; // Successfully created
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStat != null)
					pStat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isCreated;
	}

	// Read (Login with credentials)
	public LoginModel login(String userName, String password) {
		PreparedStatement pStat = null;
		Connection conn = null;
		ResultSet resultSet = null;
		LoginModel loginModel = null;

		try {
			conn = this.connect();
			String query = "SELECT username, password FROM users WHERE username = ? AND password = ?";

			pStat = conn.prepareStatement(query);
			pStat.setString(1, userName);
			pStat.setString(2, password);

			resultSet = pStat.executeQuery();

			if (resultSet.next()) {
				// If credentials are correct, populate LoginModel
				loginModel = new LoginModel();
				loginModel.setUserName(resultSet.getString("username"));
				loginModel.setPassword(resultSet.getString("password"));
				loginModel.setEmail(resultSet.getString("email"));

				// Debugging print statements
				System.out.println("User found: " + resultSet.getString("username"));
				System.out.println("Password from DB: " + resultSet.getString("password"));
			} else {
				// Debugging statement for login failure
				System.out.println("No matching user found with provided username and password.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); // Close connection
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return loginModel;
	}

	// Update (Change password or email)
	public boolean updateUser(String userName, String newPassword, String newEmail) {
		PreparedStatement pStat = null;
		Connection conn = null;
		boolean isUpdated = false;

		try {
			conn = this.connect();
			String query = "UPDATE users SET password = ?, email = ? WHERE username = ?";
			pStat = conn.prepareStatement(query);
			pStat.setString(1, newPassword);
			pStat.setString(2, newEmail);
			pStat.setString(3, userName);

			int result = pStat.executeUpdate();
			if (result > 0) {
				isUpdated = true; // Successfully updated
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStat != null)
					pStat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;
	}

	// Delete (Delete user)
	public boolean deleteUser(String userName) {
		PreparedStatement pStat = null;
		Connection conn = null;
		boolean isDeleted = false;

		try {
			conn = this.connect();
			String query = "DELETE FROM users WHERE username = ?";
			pStat = conn.prepareStatement(query);
			pStat.setString(1, userName);

			int result = pStat.executeUpdate();
			if (result > 0) {
				isDeleted = true; // Successfully deleted
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStat != null)
					pStat.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isDeleted;
	}
}
