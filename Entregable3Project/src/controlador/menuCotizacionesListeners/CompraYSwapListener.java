package controlador.menuCotizacionesListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import modelos.GestorDeDatosGlobales;

public class CompraYSwapListener extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent arg0) {
		JTable tabla = GestorDeDatosGlobales.getFramePrincipal().getMenuCotizaciones().getCotizacionesTable();
		
		int fila = tabla.rowAtPoint(arg0.getPoint());
		int col = tabla.columnAtPoint(arg0.getPoint());
		
		if(col == 3) {
			System.out.println("hola!"+fila);
		}
		
		if(col == 4) {
			//acción asociada a swap
		}
	}

}