package repo;

import java.sql.*;
import model.VetAssignmentModel;
import library.DbConnection;

public class VetAssignmentCRUD extends DbConnection {

    public boolean insert(VetAssignmentModel assignment) {
        try {
            String query = "INSERT INTO vet_assignment (ownerName, gender, contactNo, staffType) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connect().prepareStatement(query);
            stmt.setString(1, assignment.getOwnerName());
            stmt.setString(2, assignment.getGender());
            stmt.setString(3, assignment.getContactNo());
            stmt.setString(4, assignment.getStaffType());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(VetAssignmentModel assignment) {
        try {
            String query = "UPDATE vet_assignment SET gender = ?, contactNo = ?, staffType = ? WHERE ownerName = ?";
            PreparedStatement stmt = connect().prepareStatement(query);
            stmt.setString(1, assignment.getGender());
            stmt.setString(2, assignment.getContactNo());
            stmt.setString(3, assignment.getStaffType());
            stmt.setString(4, assignment.getOwnerName());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String ownerName) {
        try {
            String query = "DELETE FROM vet_assignment WHERE ownerName = ?";
            PreparedStatement stmt = connect().prepareStatement(query);
            stmt.setString(1, ownerName);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public VetAssignmentModel search(String ownerName) {
        try {
            String query = "SELECT * FROM vet_assignment WHERE ownerName = ?";
            PreparedStatement stmt = connect().prepareStatement(query);
            stmt.setString(1, ownerName);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                VetAssignmentModel assignment = new VetAssignmentModel();
                assignment.setOwnerName(rs.getString("ownerName"));
                assignment.setGender(rs.getString("gender"));
                assignment.setContactNo(rs.getString("contactNo"));
                assignment.setStaffType(rs.getString("staffType"));
                return assignment;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
