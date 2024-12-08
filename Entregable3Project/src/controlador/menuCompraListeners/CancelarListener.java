package controlador.menuCompraListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import modelos.GestorDeDatosGlobales;
import vista.IdentificadoresDePaneles;

public class CancelarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
