package sistema;

import java.sql.SQLException;

import componentes.PruebaSwing;
import singletones.MyConnection;
import singletones.MyScanner;

public class Sistema {
	public static void main(String[] args) {
		PruebaSwing frame = new PruebaSwing();
		frame.setVisible(true);
		/*
=======
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			Menu.comenzar();
			MyConnection.cerrarCon();
			MyScanner.cerrarScan();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
