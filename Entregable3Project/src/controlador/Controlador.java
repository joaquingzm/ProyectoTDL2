package controlador;

import java.sql.SQLException;

import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import modelos.Criptomoneda;
import modelos.InformacionDeCriptomonedas;
import modelos.InformacionDeMonedasFiduciarias;
import modelos.MonedaFiduciaria;
import vista.FramePrincipal;

public class Controlador {
	
	public static void main(String[] args) {
		
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			crearMonedas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		if (cDAO.estaVacia()) {
			
			for (InformacionDeCriptomonedas infoDeCripto : InformacionDeCriptomonedas.values()) {
				c = new Criptomoneda(infoDeCripto.getNombre(), infoDeCripto.getSigla(), infoDeCripto.getPrecioEnDolar(), infoDeCripto.getVolatilidad());
				cDAO.insertarCriptomoneda(c);
			}

			for (InformacionDeMonedasFiduciarias infoDeFiat : InformacionDeMonedasFiduciarias.values()) {
				m = new MonedaFiduciaria(infoDeFiat.getNombre(), infoDeFiat.getSigla(), infoDeFiat.getPrecioEnDolar(), infoDeFiat.getPaisEmisor());
				mfDAO.insertarMonedaFiduciaria(m);
			}

		}
		
	}
}
