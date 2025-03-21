package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	final private String HOST = "localhost";
	final private String USER = "root";
	final private String PASS = "1234";
	final private int PORT = 3306;
	final private String DBNAME = "vetmanagement";
	final private String DRIVER = "com.mysql.cj.jdbc.Driver";
	final private String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

	private Connection conn = null;

	// Connect with database
	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return conn;
    }
	// Close the connection
	public void close() {
		try {
			if (!this.conn.isClosed()) {
				this.conn.close();
			}
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
	}
}
