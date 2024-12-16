package vista.menuMisActivos;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.menuMisActivosListeners.CotizacionesListener;
import controlador.menuMisActivosListeners.ExportarCSVListener;
import controlador.menuMisActivosListeners.GenerarDatosDePruebaListener;
import controlador.menuMisActivosListeners.OperacionesListener;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Usuario;
import vista.Encabezado;

@SuppressWarnings("serial")
public class MenuMisActivos extends JPanel{
	
	private Encabezado encabezado;
	private CentroMisActivos centroMisActivos; 
	private JButton exportarCSV;
	private JButton generarDatosDePrueba;
	private JButton operaciones;
	private JButton cotizaciones;
	
	public MenuMisActivos() {
		
		centroMisActivos = new CentroMisActivos();
		encabezado = new Encabezado();
		exportarCSV = new JButton();
		generarDatosDePrueba = new JButton();
		operaciones = new JButton();
		cotizaciones = new JButton();
		
		centroMisActivos.setPreferredSize(new Dimension(500,200));
		exportarCSV.setPreferredSize(new Dimension(200,30));
		generarDatosDePrueba.setPreferredSize(new Dimension(200,30));
		operaciones.setPreferredSize(new Dimension(200,30));
		cotizaciones.setPreferredSize(new Dimension(200,30));
		
		exportarCSV.setText("Exportar como CSV");
		generarDatosDePrueba.setText("Generar Datos de Prueba");
		operaciones.setText("Mis Operaciones");
		cotizaciones.setText("Cotizaciones");
		
		exportarCSV.addActionListener(new ExportarCSVListener());
		generarDatosDePrueba.addActionListener(new GenerarDatosDePruebaListener());
		operaciones.addActionListener(new OperacionesListener());
		cotizaciones.addActionListener(new CotizacionesListener());
		
		this.setLayout(new GridBagLayout());

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
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(exportarCSV,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(generarDatosDePrueba,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(operaciones,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(cotizaciones,gbc);
	}
	
	public void actualizarActivos(List<ActivoCripto> listaActivosCripto , List<ActivoMonedaFiduciaria> listaActivosFIAT) {
		
		centroMisActivos.actualizarTabla(listaActivosCripto, listaActivosFIAT);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
		encabezado.actualizarUsuario(usuario);
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarExito(String exito) {
		JOptionPane.showMessageDialog(null, exito, "EXITO", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
