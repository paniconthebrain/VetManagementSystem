package repo;

import java.sql.*;
import model.VetAssignmentModel;
import library.DbConnection;

public class VetAssignmentCRUD extends DbConnection {
	/**
     * Insert a new vet assignment record into the database.
     */
	public void insert(VetAssignmentModel assignment) {
		try {
			// SQL query to insert a new vet assignment record
			String query = "INSERT INTO vet_staff_assignment (ownerId, staffid, additional_remarks, Assigned_Date) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = connect().prepareStatement(query);
			// Set the parameters for the query from the VetAssignmentModel
			stmt.setInt(1, assignment.getOwnerId());
			stmt.setInt(2, assignment.getStaffId());
			stmt.setString(3, assignment.getAdditionalRemarks());
			stmt.setString(4, assignment.getAssignedDate());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     * Update an existing vet assignment record.
     */
	public boolean update(VetAssignmentModel assignment) {
		try {
			// SQL query to update an existing vet assignment record
			String query = "UPDATE vet_assignment SET staffid = ?, additional_remarks = ? WHERE ownerId = ?";
			PreparedStatement stmt = connect().prepareStatement(query);
			// Set the parameters for the query from the VetAssignmentModel
			stmt.setInt(1, assignment.getOwnerId());
			stmt.setInt(2, assignment.getStaffId());
			stmt.setString(3, assignment.getAdditionalRemarks());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
     * Delete a vet assignment record by owner name.
     */
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
}
