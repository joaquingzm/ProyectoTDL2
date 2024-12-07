package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class CentroMisActivos extends JPanel{

	private JTable activos;
	private JPanel Balance;
	
	public CentroMisActivos() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,200));
		
		 String[] nombresColumnas = {"Icono", "Cripto", "Monto"};
		 Object[][] datos = {
	        {new ImageIcon("icono1.png"), "Dato 1-2", "Dato 1-3"},
	        {new ImageIcon("icono2.png"), "Dato 2-2", "Dato 2-3"},
	        {new ImageIcon("icono3.png"), "Dato 3-2", "Dato 3-3"},
	        {new ImageIcon("icono4.png"), "Dato 4-2", "Dato 4-3"},
	        {new ImageIcon("icono5.png"), "Dato 5-2", "Dato 5-3"}
	     };
		 	
         // Crear el modelo de la tabla con los datos y los nombres de las columnas
         DefaultTableModel modelo = new DefaultTableModel(datos, nombresColumnas);
		
		activos = new JTable(modelo);
		
		TableColumnModel columnModel = activos.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new IconRenderer());
		
		JScrollPane scrollPane = new JScrollPane(activos);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	class IconRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {	        
	 
	    	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        if (value instanceof Icon) {
	            setIcon((Icon) value);
	        } else {
	            setIcon(null);  
	        	}

	        return this;
	    }
	
	}
}
