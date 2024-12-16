package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.ActivoCriptoDAO;
import daos.ActivoMonedaFiduciariaDAO;
import daos.CriptomonedaDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;

public class GenerarDatosDePruebaListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		generarActivosCripto();
		generarActivosFIAT();
		GestorDeActualizaciones.actualizarMenuMisActivos(GestorDeDatosDelControlador.getIdUsuario());
		
	}

	private void generarActivosCripto() {
		
		List<Criptomoneda> listaC = null;
		CriptomonedaDAO cDAO = FactoryDAO.getCriptomonedaDAO();
		ActivoCriptoDAO aCDAO = FactoryDAO.getActivoCriptoDAO();
		
		try {
			listaC = cDAO.listarCriptomonedas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Random random = new Random();
		ActivoCripto aC = null;
		double cantAC = 0;
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		int idCripto = 0;
		
		for(Criptomoneda c : listaC) {
			idCripto = cDAO.buscarCriptomonedaId(c);
			cantAC = random.nextDouble() * 1000000;
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

	private void generarActivosFIAT(){
		
		List<MonedaFiduciaria> listaMF = null;
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		ActivoMonedaFiduciariaDAO aMFDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		
		try {
			listaMF = mfDAO.listarMonedasFiduciarias();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Random random = new Random();
		ActivoCripto aC = null;
		double cantAC = 0;
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		int idCripto = 0;
		
		for(MonedaFiduciaria mf : listaMF) {
			idCripto = mfDAO.buscarMonedaFiduciariaId(mf);
			cantAC = random.nextDouble() * 1000000;
			if(aMFDAO.tieneActivoFIAT(idUsuario, idCripto)) {
				aCDAO.cambiarCantidadActivoCripto(idCripto, idUsuario, cantAC);
			}
			else {
				aC = new ActivoCripto(cantAC,generarDireccion(),c);
				aCDAO.insertarActivoCripto(aC, idUsuario);
			}
		}
		
	}
	
	private List<A>
}
