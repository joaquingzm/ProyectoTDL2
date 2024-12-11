package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import vista.FramePrincipal;
import vista.menuCompra.MenuCompra;

public class ConvertirListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuCompra menuCompra = framePrincipal.getMenuCompra();
		
		double cantidadNumero = menuCompra.extraerCantidadAConvertir();
		String siglaFIAT = menuCompra.extraerSiglaDeMonedaAConvertir();
		String siglaCripto = menuCompra.extraerSiglaDeCriptomoneda();
		double precioEnDolarDeMonedaFiduciaria=0;
		double precioEnDolarDeCriptomoneda=0;
		
		
		try {
			precioEnDolarDeMonedaFiduciaria = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(siglaFIAT).getPrecioEnDolar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			precioEnDolarDeCriptomoneda = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(siglaCripto).getPrecioEnDolar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double cantidadTotalDeDolares;
		double cantidadTotalDeCripto;
		
		
	
		
		cantidadTotalDeDolares = precioEnDolarDeMonedaFiduciaria * cantidadNumero;
		
		cantidadTotalDeCripto = cantidadTotalDeDolares / precioEnDolarDeCriptomoneda;
		
		menuCompra.actualizarConversion(cantidadTotalDeCripto);
		
	}

}
