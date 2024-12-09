package controlador.menuMisActivosListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import modelos.GestorDeDatosGlobales;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CotizacionesListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
