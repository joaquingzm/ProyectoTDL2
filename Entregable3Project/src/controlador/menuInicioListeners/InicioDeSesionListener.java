package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class InicioDeSesionListener implements ActionListener{

	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	
	public InicioDeSesionListener(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
		this.cardLayout = (CardLayout) panelPrincipal.getLayout();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
