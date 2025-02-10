package vista.menuCotizaciones;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import controlador.menuCotizacionesListeners.CompraListener;
import controlador.menuCotizacionesListeners.SwapListener;
import controlador.menuCotizacionesListeners.VolverListener;
import modelos.Criptomoneda;
import modelos.Usuario;
import vista.Encabezado;
import vista.MiModeloDeTabla;

@SuppressWarnings("serial")
public class MenuCotizaciones2 extends JPanel {

	private MiModeloDeTabla cotizacionesTableModel;
	private JScrollPane cotizacionesScrollPane;
	private JTable cotizacionesTable;
	private Encabezado encabezado;
	private JButton volver;
	private String[] nombresColumnas;
	private ButtonRenderer buttonRendererC;
	private ButtonEditor buttonEditorC;
	private ButtonRenderer buttonRendererS;
	private ButtonEditor buttonEditorS;

	public MenuCotizaciones2() {
		
		nombresColumnas = new String[]{"","Cripto","Sigla","Precio(USD)","BotonC","BotonS"};
		cotizacionesTableModel = new MiModeloDeTabla(null,nombresColumnas);
		encabezado = new Encabezado();
		volver = new JButton("Volver");
		cotizacionesTable = new JTable(cotizacionesTableModel);	
		
		buttonRendererC = new ButtonRenderer();
		buttonEditorC = new ButtonEditor(new JCheckBox(),cotizacionesTableModel,new CompraListener());
		buttonRendererS = new ButtonRenderer();
		buttonEditorS = new ButtonEditor(new JCheckBox(),cotizacionesTableModel,new SwapListener());
		
		LinkedList<Integer> columnasEditables = new LinkedList<Integer>();
		columnasEditables.add(4);
		columnasEditables.add(5);
		cotizacionesTableModel.setColumnasEditables(columnasEditables);
		
		cotizacionesTable.getColumn("BotonC").setCellRenderer(buttonRendererC);
		cotizacionesTable.getColumn("BotonC").setCellEditor(buttonEditorC);
		cotizacionesTable.getColumn("BotonS").setCellRenderer(buttonRendererS);
		cotizacionesTable.getColumn("BotonS").setCellEditor(buttonEditorS);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		cotizacionesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		cotizacionesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		cotizacionesTable.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..
		cotizacionesTable.getTableHeader().setResizingAllowed(false);
		cotizacionesTable.getTableHeader().setReorderingAllowed(false);
		
		cotizacionesScrollPane = new JScrollPane(cotizacionesTable);
		
		volver.setPreferredSize(new Dimension(200,30));
		cotizacionesTable.setRowHeight(64);
		cotizacionesScrollPane.setPreferredSize(new Dimension(500,200));
		cotizacionesTable.getColumnModel().getColumn(2).setMinWidth(0);
		cotizacionesTable.getColumnModel().getColumn(2).setMaxWidth(0);
		cotizacionesTable.getColumnModel().getColumn(2).setWidth(0);

		volver.addActionListener(new VolverListener());
		
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
		encabezado.cambiarColorBackgroundBotones(color);
		buttonRendererC.setBackground(color);
		buttonEditorC.getButton().setBackground(color);
		buttonRendererS.setBackground(color);
		buttonEditorS.getButton().setBackground(color);
	}
	
	public void cambiarColorForegroundBotones(Color color) {
		volver.setForeground(color);
		encabezado.cambiarColorForegroundBotones(color);
		buttonRendererC.setForeground(color);
		buttonEditorC.getButton().setForeground(color);
		buttonRendererS.setForeground(color);
		buttonEditorS.getButton().setForeground(color);
	}
	
	public void cambiarColorBackgroundTablas(Color color) {
		cotizacionesTable.setBackground(color);
		cotizacionesTable.getTableHeader().setBackground(color);
		cotizacionesScrollPane.setBackground(color);
		cotizacionesScrollPane.getViewport().setBackground(color);
	}
	
	public void cambiarColorForegroundTablas(Color color) {
		cotizacionesTable.setForeground(color);
		cotizacionesTable.getTableHeader().setForeground(color);
		cotizacionesScrollPane.setForeground(color);
		cotizacionesScrollPane.getViewport().setForeground(color);
	}
	
	public void actualizarTabla(Boolean[] tieneActivo, List<Criptomoneda> listaCriptos) {
		
		this.revalidate();
		this.repaint();
		
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
			datos[i][4] = "Comprar";
			if(tieneActivo[i]) {
				datos[i][5] = "Swap";
			}
			else {
				datos[i][5] = "";
			}
		}
		
        cotizacionesTableModel.setRowCount(0);

        for (Object[] fila : datos) {
            cotizacionesTableModel.addRow(fila);
        }
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

	public String extraerSigla(int fila) {
		return cotizacionesTableModel.getValueAt(fila, 2).toString();
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

	@SuppressWarnings("serial")
	class ButtonRenderer extends JButton implements TableCellRenderer {

	    public ButtonRenderer() {
	        setOpaque(true);
	        setBackground(Color.BLUE); // Color de fondo por defecto
	        setForeground(Color.WHITE);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
            setForeground(getForeground());
            setBackground(getBackground());
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}

	@SuppressWarnings("serial")
	class ButtonEditor extends DefaultCellEditor {

	    protected JButton button;
	    private String label;

	    public ButtonEditor(JCheckBox checkBox, MiModeloDeTabla table, ActionListener listener) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.setBackground(Color.BLUE);
	        button.setForeground(Color.WHITE);
	        button.addActionListener(listener);
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	    	button.setForeground(button.getForeground());
	        button.setBackground(button.getBackground());
	        label = (value == null) ? "" : value.toString();
	        button.setText(label);
	        String siglaMoneda = table.getModel().getValueAt(row, 2).toString();
	        button.setActionCommand(siglaMoneda);
	        return button;
	    }
	    
	    @Override
	    public Object getCellEditorValue() {
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        return super.stopCellEditing();
	    }
	    
	    public JButton getButton() {
	    	return this.button;
	    }
	}
	
	


