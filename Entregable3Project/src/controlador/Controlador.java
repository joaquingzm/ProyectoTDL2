package controlador;

import java.sql.SQLException;

import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import daos.StockDAO;
import excepciones.InformacionExcepciones;
import modelos.Criptomoneda;
import modelos.InformacionDeCriptomonedas;
import modelos.InformacionDeMonedasFiduciarias;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import vista.FramePrincipal;

public class Controlador {
	
	public static void main(String[] args) {
		
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			crearMonedas();
		} catch (SQLException exc) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		FramePrincipal framePrincipal = new FramePrincipal();
		GestorDeDatosDelControlador.setFramePrincipal(framePrincipal);
		
		framePrincipal.setVisible(true);
		
		
	}
	
	private static void crearMonedas() throws SQLException {
		
		Criptomoneda c;
		MonedaFiduciaria m;
		
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		StockDAO stockDAO = FactoryDAO.getStockDAO();
		
		if (cDAO.estaVacia()) {
			
			for (InformacionDeCriptomonedas infoDeCripto : InformacionDeCriptomonedas.values()) {
				c = new Criptomoneda(infoDeCripto.getNombre(), infoDeCripto.getSigla(), infoDeCripto.getPrecioEnDolar(), infoDeCripto.getVolatilidad());
				cDAO.insertarCriptomoneda(c);
				stockDAO.insertarStock(new Stock(0, c));
			}
		}
		
		if(mfDAO.estaVacia()) {
			
			for (InformacionDeMonedasFiduciarias infoDeFiat : InformacionDeMonedasFiduciarias.values()) {
				m = new MonedaFiduciaria(infoDeFiat.getNombre(), infoDeFiat.getSigla(), infoDeFiat.getPrecioEnDolar(), infoDeFiat.getPaisEmisor());
				mfDAO.insertarMonedaFiduciaria(m);
			}

		}
		
	}
}
