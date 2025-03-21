package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.DbConnection;
import model.AppointmentModel;

public class AppointmentCRUD extends DbConnection {

    // Create or Insert
    public boolean insertAppointment(AppointmentModel appointment) {
        boolean result = false;
        String SQL = "INSERT INTO appointments (customer_name, appointment_date, remarks) VALUES (?, ?, ?)";
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {

            pStat.setString(1, appointment.getCustomerName());
            pStat.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            pStat.setString(3, appointment.getRemarks());
            pStat.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    // Read or Retrieve
    public AppointmentModel getAppointmentById(int id) {
        AppointmentModel appointment = null;
        String SQL = "SELECT * FROM appointments WHERE appointment_id = ?";
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {

            pStat.setInt(1, id);
            ResultSet rs = pStat.executeQuery();
            if (rs.next()) {
                appointment = new AppointmentModel();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setCustomerName(rs.getString("customer_name"));
                appointment.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
                appointment.setRemarks(rs.getString("remarks"));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return appointment;
    }

    // Update
    public boolean updateAppointment(AppointmentModel appointment) {
        boolean result = false;
        String SQL = "UPDATE appointments SET customer_name = ?, appointment_date = ?, remarks = ? WHERE appointment_id = ?";
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {

            pStat.setString(1, appointment.getCustomerName());
            pStat.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            pStat.setString(3, appointment.getRemarks());
            pStat.setInt(4, appointment.getAppointmentId());
            pStat.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    // Delete
    public boolean deleteAppointment(int id) {
        boolean result = false;
        String SQL = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {

            pStat.setInt(1, id);
            pStat.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return result;
    }

    // List All Appointments
    public List<AppointmentModel> getAllAppointments() {
        List<AppointmentModel> appointments = new ArrayList<>();
        String SQL = "SELECT * FROM appointments";
        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL)) {

            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                AppointmentModel appointment = new AppointmentModel();
                appointment.setAppointmentId(rs.getInt("appointment_id"));
                appointment.setCustomerName(rs.getString("customer_name"));
                appointment.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
                appointment.setRemarks(rs.getString("remarks"));
                appointments.add(appointment);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return appointments;
    }
}
