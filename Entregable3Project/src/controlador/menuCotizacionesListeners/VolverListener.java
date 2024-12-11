package controlador.menuCotizacionesListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class VolverListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		GestorDeDatosDelControlador.terminarTimer();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISACTIVOS.name());
		
	}

}
