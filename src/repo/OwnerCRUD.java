package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.InfOwnerCRUD;
import library.DbConnection;
import model.OwnerModel;

public class OwnerCRUD extends DbConnection implements InfOwnerCRUD {

	// Insert new owner
	@Override
	public boolean Insert(OwnerModel ownerModel) {
		boolean result = false;
		String sql = "INSERT INTO owners (full_name, contact_no, email, address, pet_nickname, pet_breed, date_of_birth) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, ownerModel.getFullName());
			pStat.setString(2, ownerModel.getContactNo());
			pStat.setString(3, ownerModel.getEmail());
			pStat.setString(4, ownerModel.getAddress());
			pStat.setString(5, ownerModel.getPetNickName());
			pStat.setString(6, ownerModel.getPetBreed());
			pStat.setString(7, ownerModel.getDateOfBirth());

			int rowsInserted = pStat.executeUpdate();
			result = rowsInserted > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	// Search for owner by ownerName
	@Override
	public OwnerModel searchByName(String ownerName) {
		OwnerModel ownerModel = null;
		String sql = "SELECT * FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, ownerName);
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
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

	// Search for owner by ownerName
	@Override
	public OwnerModel searchByID(Integer ownerId) {
		OwnerModel ownerModel = null;
		String sql = "SELECT * FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);
			ResultSet rs = pStat.executeQuery();

			if (rs.next()) {
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

	// Update owner by ownerId
	@Override
	public boolean Update(OwnerModel ownerModel) {
		boolean result = false;
		String sql = "UPDATE owners SET full_name = ?, contact_no = ?, email = ?, address = ?, pet_nickname = ?, pet_breed = ?, date_of_birth = ? WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setString(1, ownerModel.getFullName());
			pStat.setString(2, ownerModel.getContactNo());
			pStat.setString(3, ownerModel.getEmail());
			pStat.setString(4, ownerModel.getAddress());
			pStat.setString(5, ownerModel.getPetNickName());
			pStat.setString(6, ownerModel.getPetBreed());
			pStat.setString(7, ownerModel.getDateOfBirth());
			pStat.setInt(8, ownerModel.getOwnerId());

			int rowsUpdated = pStat.executeUpdate();
			result = rowsUpdated > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	// Delete owner by ownerId
	@Override
	public boolean delete(int ownerId) {
		boolean result = false;
		String sql = "DELETE FROM owners WHERE owner_id = ?";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			pStat.setInt(1, ownerId);

			int rowsDeleted = pStat.executeUpdate();
			result = rowsDeleted > 0;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return result;
	}

	// Get all owners
	@Override
	public List<OwnerModel> All() {
		List<OwnerModel> owners = new ArrayList<>();
		String sql = "SELECT * FROM owners";

		try (PreparedStatement pStat = connect().prepareStatement(sql)) {
			ResultSet rs = pStat.executeQuery();

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

				owners.add(ownerModel);
			}
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}

		return owners;
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

}
