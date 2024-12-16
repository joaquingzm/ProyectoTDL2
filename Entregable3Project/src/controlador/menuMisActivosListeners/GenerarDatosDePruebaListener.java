package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.ActivoCriptoDAO;
import daos.ActivoMonedaFiduciariaDAO;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import daos.StockDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;

public class GenerarDatosDePruebaListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			//generarActivosCripto();
			generarActivosFIAT();
			GestorDeActualizaciones.actualizarMenuMisActivos(GestorDeDatosDelControlador.getIdUsuario());
			generarStock();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
/*
	private void generarActivosCripto() throws SQLException{
		
		List<Criptomoneda> listaC = null;
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		ActivoCriptoDAO aCDAO = FactoryDAO.getActivoCriptoDAO();
	
		listaC = cDAO.listarCriptomonedas();
		
		Random random = new Random();
		ActivoCripto aC = null;
		double cantAC = 0;
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		int idCripto = 0;
		
		for(Criptomoneda c : listaC) {
			idCripto = cDAO.buscarCriptomonedaId(c);
			cantAC = (random.nextDouble() * 1000000)/c.getPrecioEnDolar();
			if(aCDAO.tieneActivoCripto(idUsuario, idCripto)) {
				aCDAO.cambiarCantidadActivoCripto(idCripto, idUsuario, cantAC);
			}
			else {
				aC = new ActivoCripto(cantAC,generarDireccion(),c);
				aCDAO.insertarActivoCripto(aC, idUsuario);
			}
		}
		
	}
	
	private String generarDireccion() {
		Random random = new Random();
		String direccion=null;
		int largo = 10;
		for (int i=0;i<largo;i++) {
			int digito = random.nextInt(10);
			direccion+=""+digito+"";
		}
		return direccion;
	}
*/
	private void generarActivosFIAT() throws SQLException{
		
		List<MonedaFiduciaria> listaMF = null;
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		ActivoMonedaFiduciariaDAO aMFDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		
		listaMF = mfDAO.listarMonedasFiduciarias();
		
		Random random = new Random();
		ActivoMonedaFiduciaria aMF = null;
		double cantAMF = 0;
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		int idFIAT = 0;
		
		for(MonedaFiduciaria mf : listaMF) {
			idFIAT = mfDAO.buscarMonedaFiduciariaId(mf);
			cantAMF = (random.nextDouble() * 1000000) / mf.getPrecioEnDolar();
			if(aMFDAO.tieneActivoMonedaFiduciaria(idUsuario, idFIAT)) {
				aMFDAO.cambiarCantidadActivoMonedaFiduciaria(idFIAT, idUsuario, cantAMF);
			}
			else {
				aMF = new ActivoMonedaFiduciaria(cantAMF,mf);
				aMFDAO.insertarActivoMonedaFiduciaria(aMF, idUsuario);
			}
		}
		
	}
	
	private void generarStock() throws SQLException {
		
		List<Criptomoneda> listaC = null;
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		StockDAO stDAO = FactoryDAO.getStockDAO();
	
		listaC = cDAO.listarCriptomonedas();
		
		Random random = new Random();
		Stock st = null;
		double cantST = 0;
		int idCripto = 0;
		
		for(Criptomoneda c : listaC) {
			idCripto = cDAO.buscarCriptomonedaId(c);
			cantST = random.nextDouble() * 1000;
			if(stDAO.existeStock(idCripto)) {
				stDAO.cambiarCantidadStock(idCripto, cantST);
			}
			else {
				st = new Stock(cantST,c);
				stDAO.insertarStock(st);
			}
		}
		
	}
}
