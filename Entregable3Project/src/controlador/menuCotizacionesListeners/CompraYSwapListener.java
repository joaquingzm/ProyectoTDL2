package controlador.menuCotizacionesListeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.InformacionExcepciones;
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
		
		int idCripto;
		
		try {
			idCripto = FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(sigla);
			
		} catch (SQLException e) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		Stock stock = null;
		
		if(menuCotizaciones.seAccionoComprar(coords)) {
			System.out.println("Se accionó compra, "+sigla);
			GestorDeDatosDelControlador.terminarTimer();
			
			List<MonedaFiduciaria> listaFIATs = null;
			
			try {
				stock = FactoryDAO.getStockDAO().buscarStock(idCripto);
				framePrincipal.getMenuCompra().cargarMoneda(stock);
				listaFIATs = FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias();
				
			} catch (SQLException e) {
				FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
				return;
			}
			
			framePrincipal.getMenuCompra().cargarSelectorFIAT(listaFIATs);
			
			framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOMPRA.name());
			
		}
		
		else if(menuCotizaciones.seAccionoSwap(coords)) {
			//acción asociada a swap
			System.out.println("Se accionó swap, "+sigla);
		}
		
	}

}
