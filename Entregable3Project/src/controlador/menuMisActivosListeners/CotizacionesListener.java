package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import excepciones.InformacionExcepciones;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CotizacionesListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		try {
			GestorDeActualizaciones.actualizarMenuCotizaciones();
			
		} catch (SQLException exc) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		GestorDeDatosDelControlador.comenzarTimer();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
