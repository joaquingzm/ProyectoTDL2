package vista.menuCotizaciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlador.menuCotizacionesListeners.CompraYSwapListener;
import controlador.menuCotizacionesListeners.VolverListener;
import modelos.Criptomoneda;
import modelos.Usuario;
import vista.Encabezado;
import vista.MiModeloDeTabla;

@SuppressWarnings("serial")
public class MenuCotizaciones extends JPanel {

	private MiModeloDeTabla cotizacionesTableModel;
	private JScrollPane cotizacionesScrollPane;
	private JTable cotizacionesTable;
	private Encabezado encabezado;
	private JButton volver;
	private String[] nombresColumnas;


	public MenuCotizaciones(){
		
		nombresColumnas = new String[]{"","Cripto","Sigla","Precio(USD)","",""};
		cotizacionesTableModel = new MiModeloDeTabla(null,nombresColumnas);
		encabezado = new Encabezado();
		volver = new JButton("Volver");
		cotizacionesTable = new JTable(cotizacionesTableModel);	
		cotizacionesScrollPane = new JScrollPane(cotizacionesTable);
		
		volver.setPreferredSize(new Dimension(200,30));
		cotizacionesTable.setRowHeight(64);
		cotizacionesScrollPane.setPreferredSize(new Dimension(500,200));
		cotizacionesTable.getColumnModel().getColumn(2).setMinWidth(0);
		cotizacionesTable.getColumnModel().getColumn(2).setMaxWidth(0);
		cotizacionesTable.getColumnModel().getColumn(2).setWidth(0);

		volver.addActionListener(new VolverListener());
		cotizacionesTable.addMouseListener(new CompraYSwapListener());
		
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 5, 10); // Espaciado
		gbc.weightx = 1; 
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Encabezado: alineado a la izquierda
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(encabezado, gbc);

		// ScrollPane para la tabla: tamaño normalizado y expansión
		cotizacionesScrollPane.setPreferredSize(new Dimension(500, 300)); // Tamaño fijo
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weighty = 1; // Expansión vertical
		gbc.fill = GridBagConstraints.BOTH;
		this.add(cotizacionesScrollPane, gbc);

		// Botón "Volver": alineado a la izquierda
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weighty = 0; 
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		this.add(volver, gbc);


	}
	
	public void cambiarColorBackgroundEncabezado(Color color) {
		encabezado.cambiarColorBackground(color);
	}

	public void cambiarColorForegroundEncabezado(Color color) {
		encabezado.cambiarColorForeground(color);;
	}
	
	public void cambiarColorBackgroundBotones(Color color) {
		volver.setBackground(color);
	}
	
	public void cambiarColorForegroundBotones(Color color) {
		volver.setForeground(color);
	}
	
	public void cambiarColorBackgroundTablas(Color color) {
		cotizacionesTable.setBackground(color);
	}
	
	public void cambiarColorForegroundTablas(Color color) {
		cotizacionesTable.setForeground(color);
	}
	

	public void actualizarTabla(Boolean[] tieneActivo, List<Criptomoneda> listaCriptos) {
				
		int dimFilas = listaCriptos.size();
		cotizacionesTableModel.setRowCount(dimFilas);
		
		Object[][] datos = new Object[dimFilas][6];
		Criptomoneda c;
		
		
		
		for(int i=0;i<dimFilas;i++) {
			c = listaCriptos.get(i);
			datos[i][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+c.getSigla()+".png"));
			datos[i][1] = c.getNombre();
			datos[i][2] = c.getSigla();
			datos[i][3] = c.getPrecioEnDolar();
			datos[i][4] = "    Comprar";
			if(tieneActivo[i]) {
				datos[i][5] = "    Swap";
			}
			else {
				datos[i][5] = "";
			}
		}
		cotizacionesTableModel.setDataVector(datos, nombresColumnas);
	}


	public void actualizarPrecios(Map<Integer, Double> preciosCriptomonedas) {
		
		/*Recibe una LinkedHashMap, por lo cual el Map conserva el orden de inserción y, por ende, se respeta el mismo orden en el que 
		 * se subieron las criptomonedas a la base de datos (ese orden se encuentra en la clase Controlador)*/

		int i=0;
		
		for (int llave : preciosCriptomonedas.keySet()) {
			cotizacionesTableModel.setValueAt(preciosCriptomonedas.get(llave), i, 3); 
			i++;
		}
		
		
	}
	
	public String extraerSigla(Point coords) {
		return cotizacionesTableModel.getValueAt(cotizacionesTable.rowAtPoint(coords), 2).toString();
	}

	public boolean seAccionoComprar(Point coords) {
		int col = cotizacionesTable.columnAtPoint(coords);
		return (col == 4) ? true:false;
	}

	public boolean seAccionoSwap(Point coords) {
		int col = cotizacionesTable.columnAtPoint(coords);
		int fila = cotizacionesTable.rowAtPoint(coords);
		return (col == 5 && cotizacionesTable.getValueAt(fila, col) != null) ? true:false;
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
		encabezado.actualizarUsuario(usuario);
	}
}
