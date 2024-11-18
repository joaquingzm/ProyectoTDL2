package sistema;

import java.sql.SQLException;

import singletones.MyConnection;
import singletones.MyScanner;
import singletones.MyStatement;

public class Sistema {
	public static void main(String[] args) {
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			Menu.comenzar();
			MyStatement.cerrarStmt();
			MyConnection.cerrarCon();
			MyScanner.cerrarScan();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
