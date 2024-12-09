package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.GestorDeDatosGlobales;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.MenuInicio;

public class RegistroListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		MenuInicio menuInicio = framePrincipal.getMenuInicio();
		
		JTextField email = menuInicio.getEmail();
		JTextField contraseña = menuInicio.getContraseña();
		
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		email.setText("");
		contraseña.setText("");
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUREGISTRACION.name());
	}
	
}
