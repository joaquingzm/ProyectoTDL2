package sistema;

import java.sql.SQLException;

import singletones.MyConnection;
import singletones.MyScanner;

public class Sistema {
	public static void main(String[] args) {
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			Menu.comenzar();
			MyConnection.cerrarCon();
			MyScanner.cerrarScan();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
