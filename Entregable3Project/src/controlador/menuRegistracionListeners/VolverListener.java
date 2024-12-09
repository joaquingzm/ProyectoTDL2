package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.GestorDeDatosGlobales;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.MenuRegistracion;

public class VolverListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		MenuRegistracion menuRegistracion = framePrincipal.getMenuRegistracion();
		
		JTextField nombre = menuRegistracion.getNombre();
		JTextField apellido = menuRegistracion.getApellido();
		JTextField email = menuRegistracion.getEmail();
		JTextField contraseña = menuRegistracion.getContraseña();
		JCheckBox terminosCondicionesCaja = menuRegistracion.getTerminosCondicionesCaja();
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}
	
}
