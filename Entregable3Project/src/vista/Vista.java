package vista;

import java.awt.CardLayout;

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
		this.setSize(800, 800);
		
		//Deberiamos guardarnos los IDs de cada sub-panel para hacer el show cuando corresponda.
		
		menuInicio = new MenuInicio(panelPrincipal);
		menuRegistracion = new MenuRegistracion(panelPrincipal);
		menuCompra = new MenuCompra(panelPrincipal);
		menuCotizaciones = new MenuCotizaciones(panelPrincipal);
		menuMisActivos = new MenuMisActivos(panelPrincipal);
		menuMisOperaciones = new MenuMisOperaciones(panelPrincipal);
		
		
		panelPrincipal.add(menuInicio, IdentificadoresDePaneles.MENUINICIO.name());
		panelPrincipal.add(menuRegistracion, IdentificadoresDePaneles.MENUREGISTRACION.name());
		panelPrincipal.add(menuCompra, IdentificadoresDePaneles.MENUCOMPRA.name());
		panelPrincipal.add(menuCotizaciones, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		panelPrincipal.add(menuMisActivos, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		panelPrincipal.add(menuMisOperaciones, IdentificadoresDePaneles.MENUMISOPERACIONES.name());
		
		this.add(panelPrincipal);

		/*
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelPrincipal);*/
		
		
		//frame.setVisible(true);  Esto deberia ir en el MAIN
		
		//this.setTitle("Billetera Virtual");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}
}
