package vista;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.FramePrincipalListeners.CierreListener;
import vista.menuCompra.MenuCompra;
import vista.menuCotizaciones.MenuCotizaciones;
import vista.menuInicio.MenuInicio;
import vista.menuMisActivos.MenuMisActivos;
import vista.menuMisOperaciones.MenuMisOperaciones;
import vista.menuRegistracion.MenuRegistracion;

@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {

	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	private MenuInicio menuInicio;
	private MenuRegistracion menuRegistracion;
	private MenuCompra menuCompra;
	private MenuCotizaciones menuCotizaciones;
	private MenuMisActivos menuMisActivos;
	private MenuMisOperaciones menuMisOperaciones;

	public FramePrincipal() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,450);
		this.setLocationRelativeTo(null);
		this.setTitle("Billetera Virtual");
		
		panelPrincipal = new JPanel();
		cardLayout = new CardLayout();
		panelPrincipal.setLayout(cardLayout);
		
		menuInicio = new MenuInicio();
		menuRegistracion = new MenuRegistracion();
		menuCompra = new MenuCompra();
		menuMisActivos = new MenuMisActivos();
		menuCotizaciones = new MenuCotizaciones();
		menuMisOperaciones = new MenuMisOperaciones();
		
		panelPrincipal.add(menuInicio, IdentificadoresDePaneles.MENUINICIO.name());
		panelPrincipal.add(menuRegistracion, IdentificadoresDePaneles.MENUREGISTRACION.name());
		panelPrincipal.add(menuCompra, IdentificadoresDePaneles.MENUCOMPRA.name());
		panelPrincipal.add(menuCotizaciones, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		panelPrincipal.add(menuMisActivos, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		panelPrincipal.add(menuMisOperaciones, IdentificadoresDePaneles.MENUMISOPERACIONES.name());
		
		this.add(panelPrincipal);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
		
        this.addWindowListener(new CierreListener());
	}

	public void cambiarMenu(String identificador) {
		
		cardLayout.show(panelPrincipal, identificador);
		
	}
	
	public JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public void setPanelPrincipal(JPanel panelPrincipal) {
		this.panelPrincipal = panelPrincipal;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public MenuInicio getMenuInicio() {
		return menuInicio;
	}

	public void setMenuInicio(MenuInicio menuInicio) {
		this.menuInicio = menuInicio;
	}

	public MenuRegistracion getMenuRegistracion() {
		return menuRegistracion;
	}

	public void setMenuRegistracion(MenuRegistracion menuRegistracion) {
		this.menuRegistracion = menuRegistracion;
	}

	public MenuCompra getMenuCompra() {
		return menuCompra;
	}

	public void setMenuCompra(MenuCompra menuCompra) {
		this.menuCompra = menuCompra;
	}

	public MenuCotizaciones getMenuCotizaciones() {
		return menuCotizaciones;
	}

	public void setMenuCotizaciones(MenuCotizaciones menuCotizaciones) {
		this.menuCotizaciones = menuCotizaciones;
	}

	public MenuMisActivos getMenuMisActivos() {
		return menuMisActivos;
	}

	public void setMenuMisActivos(MenuMisActivos menuMisActivos) {
		this.menuMisActivos = menuMisActivos;
	}

	public MenuMisOperaciones getMenuMisOperaciones() {
		return menuMisOperaciones;
	}

	public void setMenuMisOperaciones(MenuMisOperaciones menuMisOperaciones) {
		this.menuMisOperaciones = menuMisOperaciones;
	}
	
}
