package controlador;

import java.sql.SQLException;

import vista.FramePrincipal;

public class Controlador {
	
	public static void main(String[] args) {
		
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FramePrincipal framePrincipal = new FramePrincipal();
		framePrincipal.setVisible(true);
		
		
	}
}
