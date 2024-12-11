package controlador.menuRegistracionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuRegistracion.MenuRegistracion;

public class VolverListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuRegistracion menuRegistracion = framePrincipal.getMenuRegistracion();
		
		menuRegistracion.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUINICIO.name());
	}
	
}
