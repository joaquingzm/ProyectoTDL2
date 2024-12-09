package controlador.menuCotizacionesListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import modelos.GestorDeDatosGlobales;

public class CompraYSwapListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
