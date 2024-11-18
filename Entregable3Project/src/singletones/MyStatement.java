package singletones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MyStatement {
	
	private static Statement stmt = null;
	
	private MyStatement() {
		
	}
	
	static {
		
		try {
			Connection con = MyConnection.getCon();
			stmt = con.createStatement();
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: " + e.getMessage());	
		}
	}
	
	public static Statement getStmt() {
		return stmt;
	}
	
	public static void cerrarStmt() {
		try {
			
			if (stmt != null) stmt.close();
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: " + e.getMessage());	
		}
	}
	
	
	
}
