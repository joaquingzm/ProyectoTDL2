package vista;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

	private static Color colorBackground = Color.decode("#2B2B2B");
	private static Color colorForeground = Color.decode("#FFFFFF");
	private static Color colorBackgroundBotones = Color.decode("#3C3F41");
	private static Color colorForegroundBotones = Color.decode("#FFFFFF");
	
	
	
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
		this.setSize(700,600);
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
		
		menuInicio.setBackground(Color.decode("#2B2B2B"));
		menuRegistracion.setBackground(Color.decode("#2B2B2B"));
		menuCompra.setBackground(Color.decode("#2B2B2B"));
		menuMisActivos.setBackground(Color.decode("#2B2B2B"));
		menuCotizaciones.setBackground(Color.decode("#2B2B2B"));
		menuMisOperaciones.setBackground(Color.decode("#2B2B2B"));
		
		menuInicio.cambiarColorBackgroundBotones(colorBackgroundBotones);
		menuInicio.cambiarColorForegroundBotones(colorForegroundBotones);
		menuInicio.cambiarColorLabels(colorForeground);
		
		menuRegistracion.cambiarBackgroundColorBotones(colorBackgroundBotones);
		menuRegistracion.cambiarForegroundColorBotones(colorForegroundBotones);
		menuRegistracion.cambiarColorLabels(colorForeground);
		
		menuCompra.cambiarBackgroundColorBotones(colorBackgroundBotones);
		menuCompra.cambiarColorLabels(colorForeground);
		menuCompra.cambiarForegroundColorBotones(colorForegroundBotones);
		
		menuMisActivos.setBackground(colorBackground);
		menuMisActivos.cambiarColorBackgroundBotones(colorBackgroundBotones);
		menuMisActivos.cambiarColorForegroundBotones(colorForegroundBotones);
		menuMisActivos.cambiarColorBackgroundTablas(colorBackground);
		menuMisActivos.cambiarColorForegroundTablas(colorForeground);
		menuMisActivos.cambiarColorBackgroundEncabezado(colorBackground);
		menuMisActivos.cambiarColorForegroundEncabezado(colorForeground);
		
		menuCotizaciones.cambiarColorBackgroundBotones(colorBackgroundBotones);
		menuCotizaciones.cambiarColorForegroundBotones(colorForegroundBotones);
		menuCotizaciones.cambiarColorBackgroundTablas(colorBackground);
		menuCotizaciones.cambiarColorForegroundTablas(colorForeground);
		menuCotizaciones.cambiarColorBackgroundEncabezado(colorBackground);
		menuCotizaciones.cambiarColorForegroundEncabezado(colorForeground);
		
		menuMisOperaciones.cambiarBackgroundColorBotones(colorBackgroundBotones);
		menuMisOperaciones.cambiarForegroundColorBotones(colorForegroundBotones);
		
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
	
	public static void mostrarAviso(String titulo, String cuerpo) {
		JOptionPane.showMessageDialog(null, cuerpo, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
}
