package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CancelarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		int delay = 5000;
		
		GestorDeDatosDelControlador.comenzarTimer(delay);
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
		framePrincipal.getMenuCompra().realizarAccionesDeSalidaDelMenu();
	}

}
