package vista;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vista extends JFrame {

	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	private MenuInicio menuInicio;
	private MenuRegistracion menuRegistracion;
	private MenuCompra menuCompra;
	private MenuCotizaciones menuCotizaciones;
	private MenuMisActivos menuMisActivos;
	private MenuMisOperaciones menuMisOperaciones;
	
	public Vista() {
		panelPrincipal = new JPanel();
		cardLayout = new CardLayout();
		panelPrincipal.setLayout(cardLayout);

		
		//Deberiamos guardarnos los IDs de cada sub-panel para hacer el show cuando corresponda.
		
		menuInicio = new MenuInicio(panelPrincipal);
		menuRegistracion = new MenuRegistracion(panelPrincipal);
		menuCompra = new MenuCompra(panelPrincipal);
		menuCotizaciones = new MenuCotizaciones(panelPrincipal);
		menuMisActivos = new MenuMisActivos(panelPrincipal);
		menuMisOperaciones = new MenuMisOperaciones(panelPrincipal);
		
		
		/*
		this.setTitle("Billetera Virtual");
		frame.setSize(new Dimension(500,500));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelPrincipal);*/
		
		
		//frame.setVisible(true);  Esto deberia ir en el MAIN
		
		cardLayout.show(panelPrincipal, identificadoresDePaneles.get("idMenuInicio"));
	}
}
