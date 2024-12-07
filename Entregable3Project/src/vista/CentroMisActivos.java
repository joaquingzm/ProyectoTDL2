package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CentroMisActivos extends JPanel{

	private JTable activos;
	private JPanel Balance;
	
	public CentroMisActivos() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,200)); //Habria que poner esto en el MenuMisActivos
		
		 String[] nombresColumnas = {"Icono", "Cripto", "Monto($)"};
		 
		 Object[][] datos = {
	        {new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/BTC.png")), true, 20},
	        {new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/USDT.png")), true, 24},
	        {new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/DOGE.png")), true, 15},
	        {new ImageIcon("icono4.png"), true, 2},
	        {new ImageIcon("icono5.png"), true, 1}
	     };
		 
         // Crear el modelo de la tabla con los datos y los nombres de las columnas
         MiModeloDeTabla modelo = new MiModeloDeTabla(datos, nombresColumnas);
		
		activos = new JTable(modelo);
		activos.setRowHeight(64);
		activos.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(activos);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/*class IconRenderer extends DefaultTableCellRenderer {
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
	
	}*/
}
