package controlador.menuCotizacionesListeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuCotizaciones.MenuCotizaciones;

public class CompraYSwapListener extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuCotizaciones menuCotizaciones = framePrincipal.getMenuCotizaciones();
		
		Point coords = arg0.getPoint();
		
		String sigla = menuCotizaciones.extraerSigla(coords);
		
		Stock stock = null;
		
		if(menuCotizaciones.seAccionoComprar(coords)) {
			System.out.println("Se accionó compra, "+sigla);
			
			try {
				stock = FactoryDAO.getStockDAO().buscarStock(sigla);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			framePrincipal.getMenuCompra().cargarMoneda(stock);
			
			GestorDeDatosDelControlador.terminarTimer();
			
			List<MonedaFiduciaria> listaFIATs = null;
			
			try {
				listaFIATs = FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		    GestorDeDatosDelControlador.getFramePrincipal().getMenuCompra().cargarSelectorFIAT(listaFIATs);
			
			framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOMPRA.name());
			
		}
		
		else if(menuCotizaciones.seAccionoSwap(coords)) {
			//acción asociada a swap
			System.out.println("Se accionó swap, "+sigla);
		}
		
	}

}
