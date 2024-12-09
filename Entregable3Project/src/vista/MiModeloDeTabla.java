package vista;

import javax.swing.table.DefaultTableModel;

public class MiModeloDeTabla extends DefaultTableModel{
	
	public MiModeloDeTabla(Object[][] datos, String[] titulos) {
		super(datos, titulos);
	}
	
	public MiModeloDeTabla(String[] titulos, int cantidadDeColumnas) {
		super(titulos, cantidadDeColumnas);
	}
	
	@Override
	public Class<?> getColumnClass (int column) {
		
		if (this.getRowCount() > 0  /*&& this.getValueAt(0, column) != null*/) {
			return this.getValueAt(0, column).getClass();
		}
		else return String.class;
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        // Deshabilitar la edición de todas las columnas excepto la tercera (Monto)
        return false;
    }

}
