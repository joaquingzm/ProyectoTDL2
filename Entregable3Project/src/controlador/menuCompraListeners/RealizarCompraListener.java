package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

import controlador.GestorDeDatosDelControlador;
import daos.ActivoCriptoDAO;
import daos.ActivoMonedaFiduciariaDAO;
import daos.FactoryDAO;
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
		
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		
		double cantidadDeFiat = menuCompra.extraerCantidadAConvertir();
		double stockDisponible = menuCompra.extraerStockDisponible();
		
		String siglaFiat = menuCompra.extraerSiglaDeMonedaAConvertir();
		String siglaCripto = menuCompra.extraerSiglaDeCriptomoneda();
		
		ActivoMonedaFiduciariaDAO amfDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
		
		Criptomoneda cm;
		ActivoMonedaFiduciaria amf;
		
		try {
			cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(siglaCripto); //Solo se puede comprar de las criptomonedas que uno tenga??
			amf = amfDAO.buscarActivoMonedaFiduciaria(siglaFiat, idUsuario);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			return;
		}
		

		if (amf == null) {
			menuCompra.mostrarError("Ha habido un error en la compra porque el activo fiduciario a utilizar no se encuentra entre sus activos.");
			return;
		}
		
		if (amf.getCantidad() < cantidadDeFiat) {
			menuCompra.mostrarError("Ha habido un error en la compra porque no le alcanza el dinero.");
			return;
		}
		
		
		double cantidadTotalDeDolares = cantidadDeFiat * amf.getMonedaFIAT().getPrecioEnDolar();
		
		double cantidadTotalDeCripto = cantidadTotalDeDolares / cm.getPrecioEnDolar();
		
		
		
		if (stockDisponible < cantidadTotalDeCripto) {
			
			menuCompra.mostrarError("Ha habido error en la compra porque el stock en el sistema no es suficiente.");
			return;
			
		}
		
		String resumen = cantidadTotalDeCripto + " " + siglaCripto;

		Transaccion t = new Transaccion(resumen, LocalDateTime.now());
		
		try {
			
			if (acDAO.tieneActivoCripto(idUsuario, siglaCripto)) {	

				String direccion = generarDireccion();
				acDAO.insertarActivoCripto(new ActivoCripto(cantidadTotalDeCripto, direccion, cm), idUsuario);

			} else {

				acDAO.sumarCantidadActivoCripto(siglaCripto, idUsuario,cantidadTotalDeCripto);

			}

			FactoryDAO.getStockDAO().sumarCantidadStock(siglaCripto, -cantidadTotalDeCripto);
			amfDAO.sumarCantidadActivoFiduciaria(siglaFiat, idUsuario,-cantidadDeFiat);

			FactoryDAO.getTransaccionDAO().insertarTransaccion(t, idUsuario);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			return;
		}

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
