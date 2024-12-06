package vista;

import java.util.Map;

import javax.swing.JPanel;

public class MenuMisActivos extends JPanel{
	
	private Map<String, String> identificadoresDePaneles;
	
	public MenuMisActivos() {
		
	}
	
	public MenuMisActivos(Map<String, String> identificadoresDePaneles) {
		this.identificadoresDePaneles = identificadoresDePaneles;
	}
}
