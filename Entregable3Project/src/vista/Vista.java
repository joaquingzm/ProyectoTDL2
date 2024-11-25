package vista;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vista extends JFrame {

	private JPanel panelPrincipal = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	
	public void iniciar() {
		this.setTitle("Billetera Virtual");
		frame.setSize(new Dimension(500,500));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelPrincipal);
		frame.setVisible(true);
		
		cardLayout.show(panelPrincipal, "MenuInicio");
	}
}
