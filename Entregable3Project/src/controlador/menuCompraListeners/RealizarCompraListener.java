package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class RealizarCompraListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		GestorDeDatosDelControlador.comenzarTimer();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
