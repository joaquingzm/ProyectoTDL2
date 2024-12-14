package controlador.menuCotizacionesListeners;

import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import modelos.Criptomoneda;
import vista.FramePrincipal;
import vista.menuCotizaciones.MenuCotizaciones;

public class CompraListener {
	
	FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
	MenuCotizaciones menuCotizaciones = framePrincipal.getMenuCotizaciones();
	

	for(Criptomoneda c : listaCriptos) {
		if(c.getNombre().equals(nombreCripto)) {
			try {
				stock = FactoryDAO.getStockDAO().buscarStock(c.getSigla());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		break;	
		}
	}
	
	//framePrincipal.getMenuCompra().cargarMoneda(stock);
	
	//GestorDeDatosDelControlador.terminarTimer();
}
}
