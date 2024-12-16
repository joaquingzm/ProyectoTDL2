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
		
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		ActivoMonedaFiduciariaDAO amfDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
		StockDAO stDAO = FactoryDAO.getStockDAO();

		double cantidadDeFiat = menuCompra.extraerCantidadAConvertir();
		double stockDisponible = 0;
		
		String siglaFiat = menuCompra.extraerSiglaDeMonedaAConvertir();
		String siglaCripto = menuCompra.extraerSiglaDeCriptomoneda();

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
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		
		//-- Chequeo de condiciones

		if (amf == null) {
			FramePrincipal.mostrarAviso("No existe activo fiduciario", "Ha habido un error en la compra porque el activo fiduciario a utilizar no se encuentra entre sus activos.");
			return;
		}
		
		if (amf.getCantidad() < cantidadDeFiat) {
			FramePrincipal.mostrarAviso("Dinero insuficiente", "Ha habido un error en la compra porque no le alcanza el dinero.");
			return;
		}
		
		
		try {
			
			stockDisponible = stDAO.buscarStock(idCripto).getCantidad();
			
		} catch (SQLException exc) {

			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		
		double cantidadTotalDeDolares = cantidadDeFiat * amf.getMonedaFIAT().getPrecioEnDolar();
		double cantidadTotalDeCripto = cantidadTotalDeDolares / cm.getPrecioEnDolar();
		

		if (stockDisponible < cantidadTotalDeCripto) {
			
			FramePrincipal.mostrarAviso("Stock insuficiente", "Ha habido error en la compra porque el stock en el sistema no es suficiente.");
			return;
			
		}
		//--
		
		//-- Calculo de la compra y almacenamiento en la base de datos
		
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
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		//--
		
		//-- Se reflejan los cambios en los menues correspondientes
		try {
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
	
	private static String generarDireccion() {
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
