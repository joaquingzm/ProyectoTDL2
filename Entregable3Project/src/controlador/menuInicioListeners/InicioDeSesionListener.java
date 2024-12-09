package controlador.menuInicioListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Usuario;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuInicio.MenuInicio;


public class InicioDeSesionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuInicio menuInicio = framePrincipal.getMenuInicio();
	
		String emailTexto = menuInicio.extraerEmail();
		String contraseñaTexto = menuInicio.extraerContraseña();
		
		if (emailTexto.isEmpty() || contraseñaTexto.isEmpty()) {
			menuInicio.mostrarError("Algunos de los campos solicitados no se completó.");
			return;
		}
		
		int idUsuario = -1;
		Usuario usuario = null;
		
		try {
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(emailTexto, contraseñaTexto);
			if (idUsuario < 0) {
				menuInicio.mostrarError("La información ingresada no corresponde a ningun usuario.");
				return;
			}
			usuario = FactoryDAO.getUsuarioDAO().buscarUsuario(idUsuario);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			return;
		}
		
		List<ActivoCripto> listaActivosCripto = null;
		List<ActivoMonedaFiduciaria> listaActivosFIAT = null;
		
		try {
			listaActivosCripto =FactoryDAO.getActivoCriptoDAO().listarActivosCripto(idUsuario);
			listaActivosFIAT = FactoryDAO.getActivoMonedaFiduciariaDAO().listarActivosFiduciarios(idUsuario);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		framePrincipal.getMenuMisActivos().actualizarUsuario(usuario);
		framePrincipal.getMenuCotizaciones().actualizarUsuario(usuario);
		framePrincipal.getMenuMisActivos().actualizarActivos(listaActivosCripto, listaActivosFIAT);
				
		GestorDeDatosDelControlador.setIdUsuario(idUsuario);
		
		menuInicio.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISACTIVOS.name());		
	}
}
