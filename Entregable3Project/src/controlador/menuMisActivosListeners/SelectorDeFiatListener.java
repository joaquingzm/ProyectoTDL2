package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeActualizaciones;


public class SelectorDeFiatListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			GestorDeActualizaciones.actualizarMenuMisActivosFIAT();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
