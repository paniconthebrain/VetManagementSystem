package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ClientFollowUpModel;
import library.DbConnection;

public class ClientFollowUpCRUD extends DbConnection {

    // Insert new follow-up
    public boolean insert(ClientFollowUpModel followUp) {
        boolean result = false;
        String sql = "INSERT INTO client_followup (owner_id, follow_up_type, follow_up_date, remarks) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setInt(1, followUp.getOwnerId());
            pStat.setString(2, followUp.getFollowUpType());
            pStat.setString(3, followUp.getFollowUpDate());
            pStat.setString(4, followUp.getRemarks());

            int rowsInserted = pStat.executeUpdate();
            result = rowsInserted > 0;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return result;
    }

    // Search for follow-up by customer name
    public ClientFollowUpModel searchByCustomerName(String customerName) {
        ClientFollowUpModel followUp = null;
        String sql = "SELECT * FROM client_followup WHERE customer_name = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setString(1, customerName);
            ResultSet rs = pStat.executeQuery();

            if (rs.next()) {
                followUp = new ClientFollowUpModel();
                followUp.setFollowUpId(rs.getInt("follow_up_id"));
                followUp.setOwnerId(rs.getInt("owner_id"));
                followUp.setFollowUpType(rs.getString("follow_up_type"));
                followUp.setFollowUpDate(rs.getString("follow_up_date"));
                followUp.setRemarks(rs.getString("remarks"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return followUp;
    }

    // Update follow-up
    public boolean update(ClientFollowUpModel followUp) {
        boolean result = false;
        String sql = "UPDATE client_followup SET follow_up_type = ?, follow_up_date = ?, remarks = ? WHERE customer_name = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setString(1, followUp.getFollowUpType());
            pStat.setString(2, followUp.getFollowUpDate());
            pStat.setString(3, followUp.getRemarks());
            pStat.setInt(4, followUp.getOwnerId());

            int rowsUpdated = pStat.executeUpdate();
            result = rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return result;
    }

    // Delete follow-up by customer name
    public boolean deleteByCustomerName(String customerName) {
        boolean result = false;
        String sql = "DELETE FROM client_followup WHERE customer_name = ?";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            pStat.setString(1, customerName);

            int rowsDeleted = pStat.executeUpdate();
            result = rowsDeleted > 0;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return result;
    }

    // Get all follow-ups
    public List<ClientFollowUpModel> getAll() {
        List<ClientFollowUpModel> followUpList = new ArrayList<>();
        String sql = "SELECT c.follow_up_id,c.owner_id,o.full_name,c.follow_up_type,c.follow_up_date,c.remarks FROM client_followup c join owners o on o.owner_id=c.owner_id";

        try (PreparedStatement pStat = connect().prepareStatement(sql)) {
            ResultSet rs = pStat.executeQuery();

            while (rs.next()) {
                ClientFollowUpModel followUp = new ClientFollowUpModel();
                followUp.setFollowUpId(rs.getInt("follow_up_id"));
                followUp.setOwnerId(rs.getInt("owner_id"));
                followUp.setCustomerName(rs.getString("full_name"));
                followUp.setFollowUpType(rs.getString("follow_up_type"));
                followUp.setFollowUpDate(rs.getString("follow_up_date"));
                followUp.setRemarks(rs.getString("remarks"));
                followUpList.add(followUp);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return followUpList;
    }
}
