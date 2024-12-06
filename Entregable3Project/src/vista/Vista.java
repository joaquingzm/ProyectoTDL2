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
	private Map<String, String> identificadoresDePaneles;
	
	public Vista() {
		panelPrincipal = new JPanel();
		cardLayout = new CardLayout();
		panelPrincipal.setLayout(cardLayout);
		identificadoresDePaneles = new HashMap<String, String>();
		
		identificadoresDePaneles.put("idMenuInicio", "MenuInicio");
		identificadoresDePaneles.put("idMenuRegistracion", "MenuRegistracion");
		identificadoresDePaneles.put("idMenuCompra", "MenuCompra");
		identificadoresDePaneles.put("idMenuCotizaciones", "MenuCotizaciones");
		identificadoresDePaneles.put("idMenuMisActivos", "MenuMisActivos");
		identificadoresDePaneles.put("idMenuMisOperaciones", "MenuMisOperaciones");
		
		//Deberiamos guardarnos los IDs de cada sub-panel para hacer el show cuando corresponda.
		
		menuInicio = new MenuInicio(identificadoresDePaneles);
		menuRegistracion = new MenuRegistracion(identificadoresDePaneles);
		menuCompra = new MenuCompra(identificadoresDePaneles);
		menuCotizaciones = new MenuCotizaciones(identificadoresDePaneles);
		menuMisActivos = new MenuMisActivos(identificadoresDePaneles);
		menuMisOperaciones = new MenuMisOperaciones(identificadoresDePaneles);
		
		
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
