package controlador.menuInicioListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.DataException;
import excepciones.ExistenciaUsuarioException;
import excepciones.TextFieldException;
import modelos.MonedaFiduciaria;
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
		
		int idUsuario;
		Usuario usuario = null;
		
		try {
			if (emailTexto.isEmpty() || contraseñaTexto.isEmpty()) throw new TextFieldException();
			
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(emailTexto, contraseñaTexto);
			
			if (idUsuario < 0) throw new ExistenciaUsuarioException();
			
			usuario = FactoryDAO.getUsuarioDAO().buscarUsuario(idUsuario);
			
			framePrincipal.getMenuMisActivos().actualizarUsuario(usuario);
			framePrincipal.getMenuCotizaciones().actualizarUsuario(usuario);
			
			GestorDeActualizaciones.actualizarMenuMisActivos(idUsuario);
			
		} catch (DataException exc1) {
			
			FramePrincipal.mostrarAviso(exc1.getProblemaTitulo(), exc1.getProblemaCuerpo());
			return;
			
		} catch (SQLException exc2) {
			FramePrincipal.mostrarAviso(exc2.getClass().getSimpleName(), exc2.getMessage());
			return;
		}
		
		GestorDeDatosDelControlador.setIdUsuario(idUsuario);
		
		menuInicio.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISACTIVOS.name());		
	}
}
