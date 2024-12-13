package controlador.menuInicioListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuInicio.MenuInicio;

public class RegistroListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuInicio menuInicio = framePrincipal.getMenuInicio();
	
		menuInicio.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUREGISTRACION.name());	}
	
}
