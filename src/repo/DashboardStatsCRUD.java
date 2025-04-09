package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.DbConnection;

public class DashboardStatsCRUD extends DbConnection {

    public int getCustomerCount() {
        return getCount("SELECT COUNT(*) FROM users WHERE userType = 'Customer'");
    }

    public int getAppointmentCount() {
        return getCount("SELECT COUNT(*) FROM appointments");
    }

    public int getStaffCount() {
        return getCount("SELECT COUNT(*) FROM users WHERE userType = 'Staff'");
    }

    private int getCount(String query) {
        int count = 0;
        try (Connection conn = connect();  // using inherited connect() method
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return count;
    }
}
