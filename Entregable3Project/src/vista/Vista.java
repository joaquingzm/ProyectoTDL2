package vista;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import modelos.GestorDeDatosGlobales;

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
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setTitle("Billetera Virtual");
		
		panelPrincipal = new JPanel();
		cardLayout = new CardLayout();
		panelPrincipal.setLayout(cardLayout);
		GestorDeDatosGlobales.setPanelPrincipal(panelPrincipal);
		
		//Deberiamos guardarnos los IDs de cada sub-panel para hacer el show cuando corresponda.
		
		menuInicio = new MenuInicio(panelPrincipal);
		menuRegistracion = new MenuRegistracion();
		menuCompra = new MenuCompra();
		menuCotizaciones = new MenuCotizaciones();
		menuMisActivos = new MenuMisActivos();
		menuMisOperaciones = new MenuMisOperaciones();
		
		
		panelPrincipal.add(menuInicio, IdentificadoresDePaneles.MENUINICIO.name());
		panelPrincipal.add(menuRegistracion, IdentificadoresDePaneles.MENUREGISTRACION.name());
		panelPrincipal.add(menuCompra, IdentificadoresDePaneles.MENUCOMPRA.name());
		panelPrincipal.add(menuCotizaciones, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		panelPrincipal.add(menuMisActivos, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		panelPrincipal.add(menuMisOperaciones, IdentificadoresDePaneles.MENUMISOPERACIONES.name());
		
		this.add(panelPrincipal);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUMISACTIVOS.name());
	}
}
