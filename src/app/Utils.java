package app;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utils {

//	Setup Database Connection
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String dbName = "hrmdb";
			String url = "jdbc:mysql://localhost:3306/" + dbName;
			String username = "root";
			String password = "";
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Database connected");
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
		}
		return conn;
	}
}