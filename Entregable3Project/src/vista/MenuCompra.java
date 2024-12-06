package vista;

import java.util.Map;

import javax.swing.JPanel;

public class MenuCompra extends JPanel{
	
	private Map<String, String> identificadoresDePaneles;
	
	public MenuCompra() {
		
	}
	
	public MenuCompra(Map<String, String> identificadoresDePaneles) {
		this.identificadoresDePaneles = identificadoresDePaneles;
	}
}
