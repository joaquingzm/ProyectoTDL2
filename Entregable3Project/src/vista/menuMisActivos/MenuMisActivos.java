package vista.menuMisActivos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controlador.menuMisActivosListeners.SelectorDeFiatListener;
import controlador.menuMisActivosListeners.CotizacionesListener;
import controlador.menuMisActivosListeners.ExportarCSVListener;
import controlador.menuMisActivosListeners.GenerarDatosDePruebaListener;
import controlador.menuMisActivosListeners.OperacionesListener;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.InformacionDeMonedasFiduciarias;
import modelos.MonedaFiduciaria;
import modelos.Usuario;
import vista.Encabezado;

@SuppressWarnings("serial")
public class MenuMisActivos extends JPanel{
	
	private Encabezado encabezado;
	private JComboBox<String> selectorFIAT;
	private CentroMisActivos centroMisActivos; 
	private JButton exportarCSV;
	private JButton generarDatosDePrueba;
	private JButton operaciones;
	private JButton cotizaciones;
	
	public MenuMisActivos() {
		
		encabezado = new Encabezado();
		selectorFIAT = new JComboBox<String>();
		centroMisActivos = new CentroMisActivos(InformacionDeMonedasFiduciarias.ARS.getMonedaFiduciaria());
		exportarCSV = new JButton();
		generarDatosDePrueba = new JButton();
		operaciones = new JButton();
		cotizaciones = new JButton();
		
		centroMisActivos.setPreferredSize(new Dimension(500,300));
		exportarCSV.setPreferredSize(new Dimension(200,30));
		generarDatosDePrueba.setPreferredSize(new Dimension(200,30));
		operaciones.setPreferredSize(new Dimension(200,30));
		cotizaciones.setPreferredSize(new Dimension(200,30));
		
		exportarCSV.setText("Exportar como CSV");
		generarDatosDePrueba.setText("Generar Datos de Prueba");
		operaciones.setText("Mis Operaciones");
		cotizaciones.setText("Cotizaciones");
		
		selectorFIAT.addActionListener(new SelectorDeFiatListener());
		exportarCSV.addActionListener(new ExportarCSVListener());
		generarDatosDePrueba.addActionListener(new GenerarDatosDePruebaListener());
		operaciones.addActionListener(new OperacionesListener());
		cotizaciones.addActionListener(new CotizacionesListener());
		
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 5, 10);
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST; 
		gbc.weightx = 1; 
		this.add(encabezado, gbc);

		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.weightx = 0; 
		this.add(selectorFIAT, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weighty = 1; 
		gbc.fill = GridBagConstraints.BOTH;
		this.add(centroMisActivos, gbc);

		gbc.gridwidth = 1; 
		gbc.weighty = 0;
		gbc.insets = new Insets(5, 5, 5, 5); 
		gbc.fill = GridBagConstraints.NONE; 

		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(exportarCSV, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(generarDatosDePrueba, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(operaciones, gbc);

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		this.add(cotizaciones, gbc);
	}
	
	public void cambiarColorBackgroundEncabezado(Color color) {
		encabezado.cambiarColorBackground(color);
	}

	public void cambiarColorForegroundEncabezado(Color color) {
		encabezado.cambiarColorForeground(color);;
	}
	
	public void cambiarColorBackgroundBotones(Color color) {
		exportarCSV.setBackground(color);
		generarDatosDePrueba.setBackground(color);
		operaciones.setBackground(color);
		cotizaciones.setBackground(color);
	}
	
	public void cambiarColorForegroundBotones(Color color) {
		exportarCSV.setForeground(color);
		generarDatosDePrueba.setForeground(color);
		operaciones.setForeground(color);
		cotizaciones.setForeground(color);
	}
	
	public void cambiarColorBackgroundTablas(Color color) {
		centroMisActivos.cambiarColorBackgroundTabla(color);
	}
	
	public void cambiarColorForegroundTablas(Color color) {
		centroMisActivos.cambiarColorForegroundTabla(color);
	}
	
	public void actualizarActivos(List<ActivoCripto> listaActivosCripto , List<ActivoMonedaFiduciaria> listaActivosFIAT) {
		centroMisActivos.actualizarTabla(listaActivosCripto, listaActivosFIAT);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
		encabezado.actualizarUsuario(usuario);
	}
	
	public String extraerSiglaSelectorFIAT() {
	
		return selectorFIAT.getSelectedItem().toString();
	}
	
	public void cargarSelectorFIAT(List<MonedaFiduciaria> listaFIATs) {
	
		DefaultComboBoxModel<String> nuevoModeloComboBox = new DefaultComboBoxModel<String>();
		for(MonedaFiduciaria m : listaFIATs) {
			nuevoModeloComboBox.addElement(m.getSigla());
		}
		selectorFIAT.setModel(nuevoModeloComboBox);
		selectorFIAT.setSelectedItem(centroMisActivos.getMonedaFIATSigla());
	}
	
	public void actualizarMonedaFIAT(MonedaFiduciaria mf) {
		this.centroMisActivos.actualizarMonedaFIAT(mf);
	}
	
	
}
