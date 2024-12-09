package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.GestorDeDatosGlobales;
import vista.IdentificadoresDePaneles;

public class VolverListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		
		nombre.setText("");
		apellido.setText("");
		email.setText("");
		contraseña.setText("");
		terminosCondicionesCaja.setSelected(false);
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}
	
}
