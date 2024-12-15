package controlador.menuInicioListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeActualizaciones;
import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
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
		Usuario usuario = ;
		
		try {
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(usuario);
			if (idUsuario < 0) {
				menuInicio.mostrarError("La información ingresada no corresponde a ningun usuario.");
				return;
			}
			usuario = FactoryDAO.getUsuarioDAO().buscarUsuario(idUsuario);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			return;
		}
		
		
		framePrincipal.getMenuMisActivos().actualizarUsuario(usuario);
		framePrincipal.getMenuCotizaciones().actualizarUsuario(usuario);

		try {
			GestorDeActualizaciones.actualizarMenuMisActivos(idUsuario);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		GestorDeDatosDelControlador.setIdUsuario(idUsuario);
		
		menuInicio.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISACTIVOS.name());		
	}
}
