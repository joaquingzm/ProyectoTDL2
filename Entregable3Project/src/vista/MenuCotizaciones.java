package vista;

import java.util.Map;

import javax.swing.JPanel;

public class MenuCotizaciones extends JPanel{

	private Map<String, String> identificadoresDePaneles;
	
	public MenuCotizaciones() {
		
	}
	
	public MenuCotizaciones(Map<String, String> identificadoresDePaneles) {
		this.identificadoresDePaneles = identificadoresDePaneles;
	}
	
}
