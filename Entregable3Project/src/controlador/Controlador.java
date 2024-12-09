package controlador;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import daos.FactoryDAO;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;
import modelos.MonedaFiduciaria;
import vista.FramePrincipal;

public class Controlador {
	
	public static void main(String[] args) {
		
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FramePrincipal framePrincipal = new FramePrincipal();
		GestorDeDatosGlobales.setFramePrincipal(framePrincipal);
		
		framePrincipal.setVisible(true);
		
		
	}
	
	private void crearMonedas() throws SQLException {
		
		List<Criptomoneda> listaCriptos = new LinkedList<Criptomoneda>();
		List<MonedaFiduciaria> listaFIATs = new LinkedList<MonedaFiduciaria>();
		
		GestorDeDatosGlobales.setListaCriptos(listaCriptos);
		GestorDeDatosGlobales.setListaMonedasFiduciaria(listaFIATs);
		
		Criptomoneda c;
		MonedaFiduciaria m;
		
		c = new Criptomoneda("Bitcoin", "BTC", 1, 0);
		listaCriptos.add(c);
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(c);
		c = new Criptomoneda("Ethereum", "ETH", 1, 0);
		listaCriptos.add(c);
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(c);
		c = new Criptomoneda("Usdc", "USDC", 1, 0);
		listaCriptos.add(c);
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(c);
		c = new Criptomoneda("Tether", "USDT", 1, 0);
		listaCriptos.add(c);
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(c);
		c = new Criptomoneda("Dogecoin", "DOGE", 1, 0);
		listaCriptos.add(c);
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(c);
			
		
		m = new MonedaFiduciaria("Peso argentino","ARS$",(1/1036), "Argentina");
		listaFIATs.add(m);
		m = new MonedaFiduciaria("Dolar estadounidense","US$",1, "Estados Unidos");
		listaFIATs.add(m);
		m = new MonedaFiduciaria("Real brasilero","R$",0.16, "Brasil");
		listaFIATs.add(m);
		
	}
}
