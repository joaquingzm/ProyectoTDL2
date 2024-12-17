package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.ActivoCriptoDAO;
import daos.ActivoMonedaFiduciariaDAO;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import daos.StockDAO;
import excepciones.CantidadActivoFiduciarioException;
import excepciones.CantidadStockException;
import excepciones.CompraNulaException;
import excepciones.DataException;
import excepciones.ExistenciaActivoFiduciarioException;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.Transaccion;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuCompra.MenuCompra;

public class RealizarCompraListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuCompra menuCompra = framePrincipal.getMenuCompra();
		
		double cantidadDeFiat;
		
		try {
			cantidadDeFiat = menuCompra.extraerCantidadAConvertir();
			
		} catch(NumberFormatException exc) {
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		try {
			if (cantidadDeFiat == 0) throw new CompraNulaException();
			
		} catch (DataException exc) {
			FramePrincipal.mostrarAviso(exc.getProblemaTitulo(), exc.getProblemaCuerpo());
			return;
		}
		
		double stockDisponible = 0;
		
		String siglaFiat = menuCompra.extraerSiglaDeMonedaAConvertir();
		String siglaCripto = menuCompra.extraerSiglaDeCriptomoneda();
		
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		ActivoMonedaFiduciariaDAO amfDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
		StockDAO stDAO = FactoryDAO.getStockDAO();

		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		int idFIAT;
		int idCripto;
		Criptomoneda cm;
		ActivoMonedaFiduciaria amf;
		
		try {
			
			idFIAT = mfDAO.buscarMonedaFiduciariaId(siglaFiat);
			idCripto = cDAO.buscarCriptomonedaId(siglaCripto);
			
			cm = cDAO.buscarCriptomoneda(idCripto);
			amf = amfDAO.buscarActivoMonedaFiduciaria(idFIAT, idUsuario);
			
			stockDisponible = stDAO.buscarStock(idCripto).getCantidad();
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		
		//-- Chequeo de condiciones
		
		double cantidadTotalDeDolares;
		double cantidadTotalDeCripto;
		
		try {
			
			if (amf == null) throw new ExistenciaActivoFiduciarioException();
			if (amf.getCantidad() < cantidadDeFiat) throw new CantidadActivoFiduciarioException();
			
			cantidadTotalDeDolares = cantidadDeFiat * amf.getMonedaFIAT().getPrecioEnDolar();
			cantidadTotalDeCripto = cantidadTotalDeDolares / cm.getPrecioEnDolar();
			
			if (stockDisponible < cantidadTotalDeCripto) throw new CantidadStockException();
			
		} catch (DataException exc) {
			FramePrincipal.mostrarAviso(exc.getProblemaTitulo(), exc.getProblemaCuerpo());
			return;
		}
		
		//--
		
		//-- Calculo de la compra, almacenamiento en la base de datos y se reflejan los cambios en los menues correspondientes
		
		String resumen = "Compra " +cantidadTotalDeCripto + " " + siglaCripto;

		Transaccion t = new Transaccion(resumen, LocalDateTime.now());
		
		try {
			
			if (!acDAO.tieneActivoCripto(idUsuario, idCripto)) {	

				String direccion = generarDireccion();
				acDAO.insertarActivoCripto(new ActivoCripto(cantidadTotalDeCripto, direccion, cm), idUsuario);

			} else {

				acDAO.sumarCantidadActivoCripto(idCripto, idUsuario,cantidadTotalDeCripto);

			}

			amfDAO.sumarCantidadActivoFiduciaria(idFIAT, idUsuario,-cantidadDeFiat);
			stDAO.sumarCantidadStock(idCripto, -cantidadTotalDeCripto);

			FactoryDAO.getTransaccionDAO().insertarTransaccion(t, idUsuario);
			
			GestorDeActualizaciones.actualizarMenuCotizaciones();
			GestorDeActualizaciones.actualizarMenuMisActivos(idUsuario);
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		//--
		
		GestorDeDatosDelControlador.comenzarTimer();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
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
}
