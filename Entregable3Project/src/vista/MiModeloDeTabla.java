package vista;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MiModeloDeTabla extends DefaultTableModel{
	
	public MiModeloDeTabla(Object[][] datos, String[] titulos) {
		super(datos, titulos);
	}
	
	public MiModeloDeTabla(String[] titulos, int cantidadDeColumnas) {
		super(titulos, cantidadDeColumnas);
	}
	
	@Override
	public Class<?> getColumnClass (int column) {
		
		if (this.getRowCount() > 0) {
			return this.getValueAt(0, column).getClass();
		}
		else return String.class;
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return column == 5 || column == 4;
		//return false;
    }

}
