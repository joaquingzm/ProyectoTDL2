package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.GestorDeDatosDelControlador;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.MenuRegistracion;

public class VolverListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuRegistracion menuRegistracion = framePrincipal.getMenuRegistracion();
		
		JTextField nombre = menuRegistracion.getNombre();
		JTextField apellido = menuRegistracion.getApellido();
		JTextField email = menuRegistracion.getEmail();
		JTextField contraseña = menuRegistracion.getContraseña();
		JCheckBox terminosCondicionesCaja = menuRegistracion.getTerminosCondicionesCaja();
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		nombre.setText("");
		apellido.setText("");
		email.setText("");
		contraseña.setText("");
		terminosCondicionesCaja.setSelected(false);
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}
	
}
