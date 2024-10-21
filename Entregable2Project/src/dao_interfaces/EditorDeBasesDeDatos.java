package dao_interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class EditorDeBasesDeDatos {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlite:DataBaseEntregable2.db");
			
			//Aca debo llamar a los metodos estaticos de otras clases que editen tablas!!!!
			//Preguntar a la profe si lo de antes esta bien y si solo debo agregar VOLATILIDAD en las clases de java pero no añadirlo al UML
			
			System.out.println("Exito");
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: " + e.getMessage());
			
		} finally {
			
			try {
				
				if (con != null) con.close();
				
			} catch (SQLException e) {
				
				System.out.println("ERROR: " + e.getMessage());
				
			}
		}
		
		
		in.close();
	}
	
}
