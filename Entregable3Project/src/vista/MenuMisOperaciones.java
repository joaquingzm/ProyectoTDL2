package vista;

import java.util.Map;

import javax.swing.JPanel;

public class MenuMisOperaciones extends JPanel{
	
	private Map<String, String> identificadoresDePaneles;
	
	public MenuMisOperaciones() {
		
	}
	
	public MenuMisOperaciones(Map<String, String> identificadoresDePaneles) {
		this.identificadoresDePaneles = identificadoresDePaneles;
	}
}
