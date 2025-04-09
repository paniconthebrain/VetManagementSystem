package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.InfOwnerCRUD;
import library.DbConnection;
import model.OwnerModel;

public class OwnerCRUD extends DbConnection implements InfOwnerCRUD {

	/**
	 * Insert a new owner record into the database.
	 * 
	 */
	@Override
	public boolean Insert(OwnerModel ownerModel) {
		// Initialize the result to false (indicating failure by default)
		boolean result = false;
		// SQL insert query
		String sql = "INSERT INTO owners (full_name, contact_no, email, address, pet_nickname, pet_breed, date_of_birth) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		// Set the parameters for the prepared statement
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, ownerModel.getFullName());
			pStat.setString(2, ownerModel.getContactNo());
			pStat.setString(3, ownerModel.getEmail());
			pStat.setString(4, ownerModel.getAddress());
			pStat.setString(5, ownerModel.getPetNickName());
			pStat.setString(6, ownerModel.getPetBreed());
			pStat.setString(7, ownerModel.getDateOfBirth());
			// Execute the query and check if any rows were inserted
			int rowsInserted = pStat.executeUpdate();
			result = rowsInserted > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	/**
	 * Search for an owner by their name.
	 *
	 */
	@Override
	public OwnerModel searchByName(String ownerName) {
		OwnerModel ownerModel = null;
		// SQL query to search by owner name
		String sql = "SELECT * FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			// Set owner name as the parameter
			pStat.setString(1, ownerName);
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
				// If a result is found, create and populate the OwnerModel
				ownerModel = new OwnerModel();
				ownerModel.setOwnerId(rs.getInt("owner_id"));
				ownerModel.setFullName(rs.getString("full_name"));
				ownerModel.setContactNo(rs.getString("contact_no"));
				ownerModel.setEmail(rs.getString("email"));
				ownerModel.setAddress(rs.getString("address"));
				ownerModel.setPetNickName(rs.getString("pet_nickname"));
				ownerModel.setPetBreed(rs.getString("pet_breed"));
				ownerModel.setDateOfBirth(rs.getString("date_of_birth"));
			}
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return ownerModel;
	}

	/**
	 * Search for an owner by their ID.
	 */
	@Override
	public OwnerModel searchByID(Integer ownerId) {
		OwnerModel ownerModel = null;
		// SQL query to search by owner ID
		String sql = "SELECT * FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);
			// Set owner ID as the parameter
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
				// If a result is found, create and populate the OwnerModel
				ownerModel = new OwnerModel();
				ownerModel.setOwnerId(rs.getInt("owner_id"));
				ownerModel.setFullName(rs.getString("full_name"));
				ownerModel.setContactNo(rs.getString("contact_no"));
				ownerModel.setEmail(rs.getString("email"));
				ownerModel.setAddress(rs.getString("address"));
				ownerModel.setPetNickName(rs.getString("pet_nickname"));
				ownerModel.setPetBreed(rs.getString("pet_breed"));
				ownerModel.setDateOfBirth(rs.getString("date_of_birth"));
			}
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return ownerModel;
	}

	/**
	 * Update an existing owner record by their ID.
	 */
	@Override
	public boolean Update(OwnerModel ownerModel) {
		boolean result = false;
		String sql = "UPDATE owners SET full_name = ?, contact_no = ?, email = ?, address = ?, pet_nickname = ?, pet_breed = ?, date_of_birth = ? WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			// Set parameters for the prepared statement
			pStat.setString(1, ownerModel.getFullName());
			pStat.setString(2, ownerModel.getContactNo());
			pStat.setString(3, ownerModel.getEmail());
			pStat.setString(4, ownerModel.getAddress());
			pStat.setString(5, ownerModel.getPetNickName());
			pStat.setString(6, ownerModel.getPetBreed());
			pStat.setString(7, ownerModel.getDateOfBirth());
			pStat.setInt(8, ownerModel.getOwnerId());
			// Execute the update query and check if any rows were updated
			int rowsUpdated = pStat.executeUpdate();
			result = rowsUpdated > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	/**
     * Delete an owner record by their ID.
     */
	@Override
	public boolean delete(int ownerId) {
		boolean result = false;
		// SQL query to delete owner by ID
		String sql = "DELETE FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);// Set the owner ID parameter for the query

			// Execute the delete query and check if any rows were deleted
			int rowsDeleted = pStat.executeUpdate();
			result = rowsDeleted > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	 /**
     * Fetch all owner records from the database.
     */
	@Override
	public List<OwnerModel> All() {
		List<OwnerModel> owners = new ArrayList<>(); // List to store all owners
		String sql = "SELECT * FROM owners";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			ResultSet rs = pStat.executeQuery();
			// Loop through the result set and add each owner to the list
			while (rs.next()) {
				OwnerModel ownerModel = new OwnerModel();
				ownerModel.setOwnerId(rs.getInt("owner_id"));
				ownerModel.setFullName(rs.getString("full_name"));
				ownerModel.setContactNo(rs.getString("contact_no"));
				ownerModel.setEmail(rs.getString("email"));
				ownerModel.setAddress(rs.getString("address"));
				ownerModel.setPetNickName(rs.getString("pet_nickname"));
				ownerModel.setPetBreed(rs.getString("pet_breed"));
				ownerModel.setDateOfBirth(rs.getString("date_of_birth"));

				owners.add(ownerModel); // Add owner to the list
			}
		} catch (SQLException ex) {
			// Print the error message if an exception occurs during fetching
			System.out.println("Error: " + ex.getMessage());
		}

		return owners;// Return the list of owners
	}

	@Override
	public OwnerModel getOwnerbyId(int ownerId) {
		String sql = "select owner_id, full_name, contact_no,address from owners where owner_id=?;";

		OwnerModel owner = new OwnerModel();
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
				owner.setOwnerId(rs.getInt("owner_id"));
				owner.setFullName(rs.getString("full_name"));
				owner.setContactNo(rs.getString("contact_no"));
				owner.setAddress(rs.getString("address"));
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return owner;
	}

	/**
     * Fetch the pet details of an owner by their ID.
     */
	public OwnerModel getPetById(int ownerId) {
		String sql = "select owner_id,full_name, pet_nickname, pet_breed, date_of_birth from owners where owner_id=?;";

		OwnerModel owner = new OwnerModel(); // Create a new OwnerModel to hold the data
		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
				 // If a result is found, populate the owner object with pet details
				owner.setOwnerId(rs.getInt("owner_id"));
				owner.setFullName(rs.getString("full_name"));
				owner.setPetNickName(rs.getString("pet_nickname"));
				owner.setPetBreed(rs.getString("pet_breed"));
				owner.setDateOfBirth(rs.getString("date_of_birth"));
				;
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return owner;
	}
}
