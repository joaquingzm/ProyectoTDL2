package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import vista.IdentificadoresDePaneles;

public class registroListener implements ActionListener{
	
	private JPanel panelPrincipal;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUREGISTRACION.name());
	}
	
}
