package singletones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	private static Connection con = null;
	
	private MyConnection() {
		
	}
	
	static {
		
		try {
			con = DriverManager.getConnection("jdbc:sqlite:DataBaseEntregable3.db");
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: " + e.getMessage());	
		}
	}
	
	public static Connection getCon() {
		return con;
	}
	
	public static void cerrarCon() {
		try {
			
			if (con != null) con.close();
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: " + e.getMessage());	
		}
	}
	
	
}
