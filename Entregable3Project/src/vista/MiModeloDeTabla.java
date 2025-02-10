package vista;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MiModeloDeTabla extends DefaultTableModel{
	
	private List<Integer> columnasEditables;
	
	public MiModeloDeTabla(Object[][] datos, String[] titulos) {
		super(datos, titulos);
	}
	
	public MiModeloDeTabla(String[] titulos, int cantidadDeColumnas) {
		super(titulos, cantidadDeColumnas);
	}
	
	public void setColumnasEditables(List<Integer> columnasEditables) {
		this.columnasEditables = new LinkedList<Integer>();
		for(Integer i : columnasEditables) {
			this.columnasEditables.add(i);
		}
	}
	
	public List<Integer> getColumnasEditables() {
		return columnasEditables;
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

		if(this.columnasEditables!=null) {
			for(Integer i : this.columnasEditables) {
				if(column==i)return true;
			}
		}
        return false;
    }

}
