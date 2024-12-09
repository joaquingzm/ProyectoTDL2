package vista;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import modelos.Criptomoneda;

public class CotizacionesTableModel extends DefaultTableModel{
	
	private String[] titulos;
	
	public CotizacionesTableModel(String[] titulos) {
		super(null, titulos);
		this.titulos = titulos;
	}
	

	public void actualizarTabla(LinkedList<Criptomoneda> listaCriptos,Boolean[] tieneActivo) {
		
		int dimFilas = listaCriptos.size();
		
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
				System.out.println(tieneActivo[i]);
				datos[i][4] = "Swap";
			}
		}
		
		this.setDataVector(datos, this.titulos);
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        // Deshabilitar la edición de todas las columnas excepto la tercera (Monto)
        return false;
    }
}
