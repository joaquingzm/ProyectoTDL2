package controlador.menuCotizacionesListeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;

import daos.FactoryDAO;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;
import modelos.Stock;
import vista.FramePrincipal;

public class CompraYSwapListener extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		JTable tabla = GestorDeDatosGlobales.getFramePrincipal().getMenuCotizaciones().getCotizacionesTable();
		
		int fila = tabla.rowAtPoint(arg0.getPoint());
		int col = tabla.columnAtPoint(arg0.getPoint());
		
		Stock stock = null;
		
		if(col == 3) {
			String nombreCripto = tabla.getValueAt(fila, 1).toString();
			List<Criptomoneda> listaCriptos = GestorDeDatosGlobales.getListaCriptos();

			for(Criptomoneda c : listaCriptos) {
				if(c.getNombre().equals(nombreCripto)) {
					try {
						stock = FactoryDAO.getStockDAO().buscarStock(c.getSigla());
						System.out.println("Entro");			
					} catch (SQLException e) {
						e.printStackTrace();
					}
				break;	
				}
			}
			
			framePrincipal.getMenuCompra().cargarMoneda(stock);
		}
		
		if(col == 4) {
			//acción asociada a swap
		}
	}

}
