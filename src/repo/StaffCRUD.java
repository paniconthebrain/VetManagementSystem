package repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import library.DbConnection;
import model.StaffModel;

public class StaffCRUD extends DbConnection {

	// Create operation - insert staff record into the database
	public boolean Insert(StaffModel staffModel) {
		boolean result = false;
		String sql = "INSERT INTO Staff(Staff_Name, Gender, Contact_No, Staff_Type) VALUES(?, ?, ?, ?);";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, staffModel.getFullName());
			pStat.setString(2, staffModel.getGender());
			pStat.setString(3, staffModel.getContactNo());
			pStat.setString(4, staffModel.getStaffType());
			result = pStat.executeUpdate() > 0; // True if the record was inserted successfully
		} catch (SQLException ex) {
			System.out.println("Error inserting staff: " + ex.getMessage());
		}
		return result;
	}

	// Read operation - search staff record by staff ID
	public StaffModel search(int staffId) {
		StaffModel staffModel = null;
		String sql = "SELECT * FROM Staff WHERE Staff_Id = ?;";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, staffId);
			ResultSet resultSet = pStat.executeQuery();

			if (resultSet.next()) {
				staffModel = new StaffModel(resultSet.getInt("Staff_Id"), resultSet.getString("Staff_Name"),
						resultSet.getString("Gender"), resultSet.getString("Contact_No"),
						resultSet.getString("Staff_Type"));
			}
		} catch (SQLException ex) {
			System.out.println("Error searching for staff: " + ex.getMessage());
		}
		return staffModel;
	}

	// Update operation - modify staff record
	public boolean Update(StaffModel staffModel) {
		boolean result = false;
		String sql = "UPDATE Staff SET Staff_Name = ?, Gender = ?, Contact_No = ?, Staff_Type = ? WHERE Staff_Id = ?;";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, staffModel.getFullName());
			pStat.setString(2, staffModel.getGender());
			pStat.setString(3, staffModel.getContactNo());
			pStat.setString(4, staffModel.getStaffType());
			pStat.setInt(5, staffModel.getStaffId());
			result = pStat.executeUpdate() > 0; // True if the record was updated successfully
		} catch (SQLException ex) {
			System.out.println("Error updating staff: " + ex.getMessage());
		}
		return result;
	}

	// Delete operation - delete staff record
	public boolean Delete(int staffId) {
		boolean result = false;
		String sql = "DELETE FROM Staff WHERE Staff_Id = ?;";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, staffId);
			result = pStat.executeUpdate() > 0; // True if the record was deleted successfully
		} catch (SQLException ex) {
			System.out.println("Error deleting staff: " + ex.getMessage());
		}
		return result;
	}

	// Retrieve all staff records (for listing or selection)
	public List<StaffModel> All() {
		List<StaffModel> staffList = new ArrayList<>();
		String sql = "SELECT * FROM Staff;";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			ResultSet resultSet = pStat.executeQuery();

			while (resultSet.next()) {
				StaffModel staffModel = new StaffModel(resultSet.getInt("Staff_Id"), resultSet.getString("Staff_Name"),
						resultSet.getString("Gender"), resultSet.getString("Contact_No"),
						resultSet.getString("Staff_Type"));
				staffList.add(staffModel);
			}
		} catch (SQLException ex) {
			System.out.println("Error retrieving all staff: " + ex.getMessage());
		}
		return staffList;
	}

	public List<StaffModel> getAllStaff() {
	    List<StaffModel> staffList = new ArrayList<>();
	    String sql = "SELECT Staff_Id, Staff_Name FROM Staff;"; // Fetch ID and Name

	    try (PreparedStatement pStat = connect().prepareStatement(sql);
	         ResultSet resultSet = pStat.executeQuery()) {

	        while (resultSet.next()) {
	            StaffModel staffModel = new StaffModel(
	                resultSet.getInt("Staff_Id"), 
	                resultSet.getString("Staff_Name"),
	                "", "", "" // Other fields ignored
	            );
	            staffList.add(staffModel);
	        }

	    } catch (SQLException ex) {
	        System.out.println("Error retrieving all staff: " + ex.getMessage());
	    }
	    return staffList;
	}
}
