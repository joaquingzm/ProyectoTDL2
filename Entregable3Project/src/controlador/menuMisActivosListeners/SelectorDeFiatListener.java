package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeActualizaciones;
import excepciones.InformacionExcepciones;
import vista.FramePrincipal;


public class SelectorDeFiatListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			GestorDeActualizaciones.actualizarMenuMisActivosFIAT();
		} catch (SQLException e) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
	}

}
