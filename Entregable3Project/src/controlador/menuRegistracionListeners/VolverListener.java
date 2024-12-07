package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import vista.IdentificadoresDePaneles;

public class VolverListener implements ActionListener{
	
	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	
	public VolverListener(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		this.cardLayout = (CardLayout) panelPrincipal.getLayout();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}
	
}
