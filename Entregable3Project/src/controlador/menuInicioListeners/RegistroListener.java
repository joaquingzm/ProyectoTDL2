package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import vista.IdentificadoresDePaneles;

public class RegistroListener implements ActionListener{
	
	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	
	public RegistroListener(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		this.cardLayout = (CardLayout) panelPrincipal.getLayout();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUREGISTRACION.name());
	}
	
}
