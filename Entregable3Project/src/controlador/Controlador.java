package controlador;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;
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
		GestorDeDatosGlobales.setFramePrincipal(framePrincipal);
		
		framePrincipal.setVisible(true);
		
		
	}
	
	private static void crearMonedas() throws SQLException {
		
		List<Criptomoneda> listaCriptos = new LinkedList<Criptomoneda>();
		List<MonedaFiduciaria> listaFIATs = new LinkedList<MonedaFiduciaria>();
		
		Criptomoneda c;
		MonedaFiduciaria m;
		
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		
		c = new Criptomoneda("Bitcoin", "BTC", 1, 0);
		listaCriptos.add(c);
		if (cDAO.estaVacia()) {
			cDAO.insertarCriptomoneda(c);
			c = new Criptomoneda("Ethereum", "ETH", 1, 0);
			listaCriptos.add(c);
			cDAO.insertarCriptomoneda(c);
			c = new Criptomoneda("Usd-coin", "USDC", 1, 0);
			listaCriptos.add(c);
			cDAO.insertarCriptomoneda(c);
			c = new Criptomoneda("Tether", "USDT", 1, 0);
			listaCriptos.add(c);
			cDAO.insertarCriptomoneda(c);
			c = new Criptomoneda("Dogecoin", "DOGE", 1, 0);
			listaCriptos.add(c);
			cDAO.insertarCriptomoneda(c);
			
			m = new MonedaFiduciaria("Peso argentino","ARS",(1/1036), "Argentina");
			listaFIATs.add(m);
			mfDAO.insertarMonedaFiduciaria(m);
			m = new MonedaFiduciaria("Dolar estadounidense","USD",1, "Estados Unidos");
			listaFIATs.add(m);
			mfDAO.insertarMonedaFiduciaria(m);
			m = new MonedaFiduciaria("EURO","EUR",1.056, "Brasil");
			listaFIATs.add(m);
			mfDAO.insertarMonedaFiduciaria(m);
		} else {
			c = new Criptomoneda("Ethereum", "ETH", 1, 0);
			listaCriptos.add(c);
			c = new Criptomoneda("Usd-coin", "USDC", 1, 0);
			listaCriptos.add(c);
			c = new Criptomoneda("Tether", "USDT", 1, 0);
			listaCriptos.add(c);
			c = new Criptomoneda("Dogecoin", "DOGE", 1, 0);
			listaCriptos.add(c);
			
			m = new MonedaFiduciaria("Peso argentino","ARS",(1/1036), "Argentina");
			listaFIATs.add(m);
			m = new MonedaFiduciaria("Dolar estadounidense","USD",1, "Estados Unidos");
			listaFIATs.add(m);
			m = new MonedaFiduciaria("EURO","EUR",1.056, "Brasil");
			listaFIATs.add(m);
		}
		
		GestorDeDatosGlobales.setListaCriptos(listaCriptos);
		GestorDeDatosGlobales.setListaMonedasFiduciaria(listaFIATs);
		
	}
}
