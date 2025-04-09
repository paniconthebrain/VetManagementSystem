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

	/**
	 * Insert a new appointment into the database.
	 */
	public boolean insertAppointment(AppointmentModel appointment) {
		boolean result = false;
		String SQL = "INSERT INTO appointments (customer_name, appointment_date, remarks) VALUES (?, ?, ?)";
		try (Connection conn = connect(); // Establish a connection to the database
				PreparedStatement pStat = conn.prepareStatement(SQL)) {
			// Set the parameters for the query from the AppointmentModel object
			pStat.setString(1, appointment.getCustomerName());
			pStat.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate()));
			pStat.setString(3, appointment.getRemarks());
			pStat.executeUpdate();// Execute the update (insert the record)
			result = true;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * Retrieve an appointment by its ID.
	 */
	public AppointmentModel getAppointmentById(int id) {
		AppointmentModel appointment = null;
		String SQL = "SELECT * FROM appointments WHERE appointment_id = ?";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			// Prepare the SQL query
			pStat.setInt(1, id);
			ResultSet rs = pStat.executeQuery();
			// If the appointment exists, map the result set to an AppointmentModel object
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

	/**
	 * Update an existing appointment in the database.
	 */
	public boolean updateAppointment(AppointmentModel appointment) {
		boolean result = false;
		String SQL = "UPDATE appointments SET customer_name = ?, appointment_date = ?, remarks = ? WHERE appointment_id = ?";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			// Set the parameters for the query from the AppointmentModel object
			pStat.setString(1, appointment.getCustomerName());
			pStat.setDate(2, java.sql.Date.valueOf(appointment.getAppointmentDate()));
			pStat.setString(3, appointment.getRemarks());
			pStat.setInt(4, appointment.getAppointmentId());// Set the appointment ID for the WHERE clause
			pStat.executeUpdate();
			result = true;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return result;
	}

	/**
	 * Delete an appointment from the database by its ID.
	 */
	public boolean deleteAppointment(int id) {
		boolean result = false;
		String SQL = "DELETE FROM appointments WHERE appointment_id = ?";// Prepare the SQL query
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
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
    
 // Read or Retrieve all appointments for report
    public List<AppointmentModel> getAllAppointmentsByDate() {
        List<AppointmentModel> appointments = new ArrayList<>();
        String SQL = "SELECT * FROM appointments ORDER BY appointment_date";

        try (Connection conn = connect(); 
             PreparedStatement pStat = conn.prepareStatement(SQL);
             ResultSet rs = pStat.executeQuery()) {

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

			pStat.setInt(1, id); // Execute the delete query
			pStat.executeUpdate();
			result = true;
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return result;
	}

	/**
     * Retrieve all appointments from the database.
     */
	public List<AppointmentModel> getAllAppointments() {
		List<AppointmentModel> appointments = new ArrayList<>();
		String SQL = "SELECT * FROM appointments";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {

			ResultSet rs = pStat.executeQuery();
			while (rs.next()) {
				AppointmentModel appointment = new AppointmentModel();
				appointment.setAppointmentId(rs.getInt("appointment_id"));
				appointment.setCustomerName(rs.getString("customer_name"));
				appointment.setAppointmentDate(rs.getDate("appointment_date").toLocalDate()); // Convert SQL Date to LocalDate
				appointment.setRemarks(rs.getString("remarks"));
				appointments.add(appointment);
			}
		} catch (SQLException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return appointments; // Return the list of all appointments
	}
}
