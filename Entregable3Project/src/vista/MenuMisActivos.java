package vista;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class MenuMisActivos extends JPanel{
	
	private Encabezado encabezado;
	private CentroMisActivos centroMisActivos; 
	private BorderLayout borderLayout;
	

	public MenuMisActivos() {
		
		this.encabezado = new Encabezado();
		this.centroMisActivos = new CentroMisActivos();
		
		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		
		
		
	}
}
