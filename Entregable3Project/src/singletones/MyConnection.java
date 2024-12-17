package singletones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import excepciones.InformacionExcepciones;
import vista.FramePrincipal;

public class MyConnection {

	private static Connection con = null;
	
	private MyConnection() {
		
	}
	
	static {
		
		try {
			con = DriverManager.getConnection("jdbc:sqlite:DataBaseEntregable3.db");
			
		} catch (SQLException e) {
			
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
		}
	}
	
	public static Connection getCon() {
		return con;
	}
	
	public static void cerrarCon() {
		try {
			
			if (con != null) con.close();
			
		} catch (SQLException e) {
			
			FramePrincipal.mostrarAviso(e.getClass().getSimpleName(), e.getMessage());
		}
	}
	
	
}
