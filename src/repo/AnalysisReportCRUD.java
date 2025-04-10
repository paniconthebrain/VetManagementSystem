package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.DbConnection;

public class AnalysisReportCRUD extends DbConnection {

	// Method to get total number of owners
	public int getTotalOwners() {
		int totalOwners = 0;
		String sql = "SELECT COUNT(*) AS totalOwners FROM Owners"; // Assuming your table is called "Owners"

		try (Connection conn = connect(); // Replace with your DB connection code
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				totalOwners = resultSet.getInt("totalOwners");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalOwners;
	}

	// Method to get total number of pets
	public int getTotalPets() {
		int totalPets = 0;
		String sql = "SELECT COUNT(*) AS totalPets FROM owners"; // Assuming your table is called "Pets"

		try (Connection conn = connect(); // Replace with your DB connection code
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			if (resultSet.next()) {
				totalPets = resultSet.getInt("totalPets");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPets;
	}

	// Method to get pet breed statistics (e.g., count of pets per breed)
//	public String getPetBreedStatistics() {
//		StringBuilder breedStats = new StringBuilder();
//		String sql = "SELECT Pet_Breed, COUNT(*) AS breedCount FROM Pets GROUP BY Pet_Breed"; // Assuming "Pets" table
//																								// has "Pet_Breed"
//
//		try (Connection conn = connect(); // Replace with your DB connection code
//				PreparedStatement statement = conn.prepareStatement(sql);
//				ResultSet resultSet = statement.executeQuery()) {
//
//			while (resultSet.next()) {
//				String breed = resultSet.getString("Pet_Breed");
//				int breedCount = resultSet.getInt("breedCount");
//				breedStats.append(breed).append(": ").append(breedCount).append("\n");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return breedStats.toString();
//	}
}
