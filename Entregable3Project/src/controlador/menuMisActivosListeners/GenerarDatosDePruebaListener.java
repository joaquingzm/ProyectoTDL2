package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.ActivoMonedaFiduciariaDAO;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import daos.StockDAO;
import excepciones.InformacionExcepciones;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import vista.FramePrincipal;

public class GenerarDatosDePruebaListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			generarActivosFIAT();
			GestorDeActualizaciones.actualizarMenuMisActivos(GestorDeDatosDelControlador.getIdUsuario());
			generarStock();
		} catch (SQLException e) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
	}

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
