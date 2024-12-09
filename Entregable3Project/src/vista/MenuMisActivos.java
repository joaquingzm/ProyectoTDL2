package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import controlador.menuMisActivosListeners.CotizacionesListener;
import controlador.menuMisActivosListeners.ExportarCSVListener;
import controlador.menuMisActivosListeners.OperacionesListener;
import modelos.Usuario;

public class MenuMisActivos extends JPanel{
	
	private Encabezado encabezado;
	private CentroMisActivos centroMisActivos; 
	
	private JButton exportarCSV;
	private JButton operaciones;
	private JButton cotizaciones;
	
	public MenuMisActivos(Encabezado encabezado) {
		
		
		centroMisActivos = new CentroMisActivos();
		
		exportarCSV = new JButton();
		exportarCSV.setPreferredSize(new Dimension(200,30));
		operaciones = new JButton();
		operaciones.setPreferredSize(new Dimension(200,30));
		cotizaciones = new JButton();
		cotizaciones.setPreferredSize(new Dimension(200,30));
		
		exportarCSV.setText("Exportar como CSV");
		operaciones.setText("Mis Operaciones");
		cotizaciones.setText("Cotizaciones");
		
		exportarCSV.addActionListener(new ExportarCSVListener());
		operaciones.addActionListener(new OperacionesListener());
		cotizaciones.addActionListener(new CotizacionesListener());
		
		this.setLayout(new GridBagLayout());
		//Ver si va o no
		//this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(encabezado,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(centroMisActivos,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(exportarCSV,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		this.add(operaciones,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(cotizaciones,gbc);
	}
	
	public Encabezado getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}

}
