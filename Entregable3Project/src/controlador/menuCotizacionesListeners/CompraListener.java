package controlador.menuCotizacionesListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.InformacionExcepciones;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CompraListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		String sigla = arg0.getActionCommand();
		
		int idCripto;

		try {
			idCripto = FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(sigla);
			
		} catch (SQLException e) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		Stock stock = null;
		
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

}
