package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeDatosDelControlador;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import vista.FramePrincipal;
import vista.menuCompra.MenuCompra;

public class ConvertirListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuCompra menuCompra = framePrincipal.getMenuCompra();
		
		MonedaFiduciariaDAO mDAO = FactoryDAO.getMonedaFiduciariaDAO();
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		
		double cantidadNumero = menuCompra.extraerCantidadAConvertir();
		
		int idFIAT = -1;
		int idCripto = -1;
		
		try {
			idFIAT = mDAO.buscarMonedaFiduciariaId(menuCompra.extraerSiglaDeMonedaAConvertir());
			idCripto = cDAO.buscarCriptomonedaId(menuCompra.extraerSiglaDeCriptomoneda());
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		
		double precioEnDolarDeMonedaFiduciaria=0;
		double precioEnDolarDeCriptomoneda=0;
		
		
		try {
			precioEnDolarDeMonedaFiduciaria = mDAO.buscarMonedaFiduciaria(idFIAT).getPrecioEnDolar();
			precioEnDolarDeCriptomoneda = cDAO.buscarCriptomoneda(idCripto).getPrecioEnDolar();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		
		double cantidadTotalDeDolares;
		double cantidadTotalDeCripto;
		
		
	
		
		cantidadTotalDeDolares = precioEnDolarDeMonedaFiduciaria * cantidadNumero;
		
		cantidadTotalDeCripto = cantidadTotalDeDolares / precioEnDolarDeCriptomoneda;
		
		menuCompra.actualizarConversion(cantidadTotalDeCripto);
		
	}

}
