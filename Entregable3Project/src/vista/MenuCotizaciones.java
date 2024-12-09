package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.menuCotizacionesListeners.CompraYSwapListener;
import controlador.menuCotizacionesListeners.VolverListener;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;

public class MenuCotizaciones extends JPanel {

	private CotizacionesTableModel cotizacionesTableModel;
	private JScrollPane cotizacionesScrollPane;
	private JTable cotizacionesTable;
	private Encabezado encabezado;
	private JButton volver;


	public MenuCotizaciones(Encabezado encabezado){
		
		this.setLayout(new GridBagLayout());
		
		String[] nombresColumnas = {"","Cripto","Precio de Compra","",""};
		
		cotizacionesTableModel = new CotizacionesTableModel(nombresColumnas);
		
		volver = new JButton("Volver");
		volver.setPreferredSize(new Dimension(200,30));
		cotizacionesTable = new JTable(cotizacionesTableModel);
		
		cotizacionesTable.setRowHeight(64);
		cotizacionesTable.setAutoCreateRowSorter(true);
		
		cotizacionesScrollPane = new JScrollPane(cotizacionesTable);
		cotizacionesScrollPane.setPreferredSize(new Dimension(500,200));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		volver.addActionListener(new VolverListener());
		cotizacionesTable.addMouseListener(new CompraYSwapListener());
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		
		gbc.gridwidth = 2;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(encabezado,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(cotizacionesScrollPane,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(volver,gbc);
		
	}


	public CotizacionesTableModel getCotizacionesTableModel() {
		return cotizacionesTableModel;
	}


	public void setCotizacionesTableModel(CotizacionesTableModel cotizacionesTableModel) {
		this.cotizacionesTableModel = cotizacionesTableModel;
	}


	public JScrollPane getCotizacionesScrollPane() {
		return cotizacionesScrollPane;
	}


	public void setCotizacionesScrollPane(JScrollPane cotizacionesScrollPane) {
		this.cotizacionesScrollPane = cotizacionesScrollPane;
	}


	public JTable getCotizacionesTable() {
		return cotizacionesTable;
	}


	public void setCotizacionesTable(JTable cotizacionesTable) {
		this.cotizacionesTable = cotizacionesTable;
	}


	public Encabezado getEncabezado() {
		return encabezado;
	}


	public void setEncabezado(Encabezado encabezado) {
		this.encabezado = encabezado;
	}


	public JButton getVolver() {
		return volver;
	}


	public void setVolver(JButton volver) {
		this.volver = volver;
	}


}
