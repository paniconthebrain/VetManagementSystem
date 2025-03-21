package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import library.DbConnection;
import model.UserManagementModel;

public class UserCRUD extends DbConnection {

    // Login method to validate user credentials
    public UserManagementModel login(String username, String password) {
        UserManagementModel user = null;
        String SQL = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {
             
            pStat.setString(1, username);
            pStat.setString(2, password);
            
            ResultSet resultSet = pStat.executeQuery();
            if (resultSet.next()) {
                user = new UserManagementModel();
                user.setUserId(resultSet.getInt("USER_ID"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
            }
            pStat.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return user;
    }
}
