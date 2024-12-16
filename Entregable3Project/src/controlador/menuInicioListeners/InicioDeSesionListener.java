package controlador.menuInicioListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.DataException;
import excepciones.TextFieldException;
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
		
		try {
			if (emailTexto.isEmpty() || contraseñaTexto.isEmpty()) throw new TextFieldException();
			
		} catch (DataException exc) {
			
			FramePrincipal.mostrarAviso(exc.getProblemaTitulo(), exc.getProblemaCuerpo());
			return;
		}

		
		int idUsuario;
		Usuario usuario = null;
		
		try {
			
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(emailTexto, contraseñaTexto);
			
			if (idUsuario < 0) {
				FramePrincipal.mostrarAviso("Información inexistente", "La información ingresada no corresponde a ningun usuario.");
				return;
			}
			usuario = FactoryDAO.getUsuarioDAO().buscarUsuario(idUsuario);
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		
		framePrincipal.getMenuMisActivos().actualizarUsuario(usuario);
		framePrincipal.getMenuCotizaciones().actualizarUsuario(usuario);

		try {
			GestorDeActualizaciones.actualizarMenuMisActivos(idUsuario);
			
		} catch (SQLException exc) {
			FramePrincipal.mostrarAviso(exc.getClass().getSimpleName(), exc.getMessage());
			return;
		}
		
		GestorDeDatosDelControlador.setIdUsuario(idUsuario);
		
		menuInicio.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISACTIVOS.name());		
	}
}
