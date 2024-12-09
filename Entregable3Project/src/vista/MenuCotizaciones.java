package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.menuCotizacionesListeners.CompraYSwapListener;
import controlador.menuCotizacionesListeners.VolverListener;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;

public class MenuCotizaciones extends JPanel {

	private MiModeloDeTabla cotizacionesTableModel;
	private JScrollPane cotizacionesScrollPane;
	private JTable cotizacionesTable;
	private Encabezado encabezado;
	private JButton volver;
	private String[] nombresColumnas;

	public MenuCotizaciones(){
		
		this.setLayout(new GridBagLayout());
		
		this.nombresColumnas = new String[]{".","Cripto","Precio de Compra",".","."};
		
		cotizacionesTableModel = new MiModeloDeTabla(null,nombresColumnas);
		
		encabezado = new Encabezado();
		volver = new JButton("Volver");
		volver.setPreferredSize(new Dimension(200,30));
		cotizacionesTable = new JTable(cotizacionesTableModel);
		
		cotizacionesTable.setRowHeight(64);
		
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

	public void actualizarTabla(LinkedList<Criptomoneda> listaCriptos,Boolean[] tieneActivo) {
		
		int dimFilas = listaCriptos.size();
		cotizacionesTableModel.setRowCount(dimFilas);
		
		Object[][] datos = new Object[dimFilas][5];
		Criptomoneda c;
		
		
		
		for(int i=0;i<dimFilas;i++) {
			c = listaCriptos.get(i);
			//Ver que onda esto del icono
			if(c.getRutaIcono() == null) {
				datos[i][0] = new ImageIcon();
			}
			else {
				datos[i][0] = new ImageIcon(getClass().getClassLoader().getResource(c.getRutaIcono()));
			}
			datos[i][1] = c.getNombre();
			datos[i][2] = c.getPrecioEnDolar();
			datos[i][3] = "Comprar";
			if(tieneActivo[i]) {
				datos[i][4] = "Swap";
			}
			else {
				datos[i][4] = "";
			}
		}
		cotizacionesTableModel.setDataVector(datos, nombresColumnas);
	}


	public void actualizarPrecios(Map<String,Double> preciosCriptomonedas) {
		
		int dimFilas = preciosCriptomonedas.size();

		LinkedList<Criptomoneda> listaCriptos = GestorDeDatosGlobales.getListaCriptos;
		
		for(int i=0;i<dimFilas;i++) {
			cotizacionesTableModel.setValueAt(preciosCriptomonedas.get(listaCriptos.get(i).getNombre().toUpperCase()), i, dimFilas); 
		}
	}
	
	public MiModeloDeTabla getCotizacionesTableModel() {
		return cotizacionesTableModel;
	}


	public void setCotizacionesTableModel(MiModeloDeTabla cotizacionesTableModel) {
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
