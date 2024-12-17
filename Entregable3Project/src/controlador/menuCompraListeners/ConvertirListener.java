package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeDatosDelControlador;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import excepciones.InformacionExcepciones;
import vista.FramePrincipal;
import vista.menuCompra.MenuCompra;

public class ConvertirListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuCompra menuCompra = framePrincipal.getMenuCompra();
		
		MonedaFiduciariaDAO mDAO = FactoryDAO.getMonedaFiduciariaDAO();
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		double cantidadNumero;
		
		try {
			cantidadNumero = menuCompra.extraerCantidadAConvertir();
			
		} catch(NumberFormatException exc) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.NUMBERFORMAT.getTitulo(), InformacionExcepciones.NUMBERFORMAT.getCuerpo());
			return;
		}
		
		int idFIAT = -1;
		int idCripto = -1;
		
		double precioEnDolarDeMonedaFiduciaria=0;
		double precioEnDolarDeCriptomoneda=0;
		
		try {
			idFIAT = mDAO.buscarMonedaFiduciariaId(menuCompra.extraerSiglaDeMonedaAConvertir());
			idCripto = cDAO.buscarCriptomonedaId(menuCompra.extraerSiglaDeCriptomoneda());
			
			precioEnDolarDeMonedaFiduciaria = mDAO.buscarMonedaFiduciaria(idFIAT).getPrecioEnDolar();
			precioEnDolarDeCriptomoneda = cDAO.buscarCriptomoneda(idCripto).getPrecioEnDolar();
			
		} catch (SQLException exc) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		

		double cantidadTotalDeDolares = precioEnDolarDeMonedaFiduciaria * cantidadNumero;
		
		double cantidadTotalDeCripto = cantidadTotalDeDolares / precioEnDolarDeCriptomoneda;
		
		menuCompra.actualizarConversion(cantidadTotalDeCripto);
		
	}

}
