package controlador.menuRegistracionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import daos.PersonaDAO;
import daos.UsuarioDAO;
import modelos.Persona;
import modelos.Usuario;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuRegistracion.MenuRegistracion;

public class RegistroListener implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuRegistracion menuRegistracion = framePrincipal.getMenuRegistracion();
		
		String nombre = menuRegistracion.extraerNombre();
		String apellido = menuRegistracion.extraerApellido();
		String email = menuRegistracion.extraerEmail();
		String contraseña = menuRegistracion.extraerContraseña();
		boolean aceptoTerminosCondiciones = menuRegistracion.seAceptaronTerminosYCondiciones();
		
		if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
			menuRegistracion.mostrarError("Algunos de los campos solicitados no se completó.");
			return;
		}
		
		if (!aceptoTerminosCondiciones) {
			menuRegistracion.mostrarError("No se aceptaron los Terminos y Condiciones.");
			return;
		}

		UsuarioDAO usrDAO= FactoryDAO.getUsuarioDAO();
		
		//CAMBIAR POR NUESTRA EXCEPCION !!!!
		try {
			if (usrDAO.existeEmail(email)) {
				throw new SQLException();
			}
		} catch (SQLException e1) {
			menuRegistracion.mostrarError("El email propuesto esta asociado a otro usuario.");
			return;
		}
		
		PersonaDAO pDAO = FactoryDAO.getPersonaDAO();
		Persona persona = new Persona(nombre,apellido);
		Usuario usuario = new Usuario(persona,email,contraseña,aceptoTerminosCondiciones);
		int IdPersona = -1;
		
		try {
			
			IdPersona = pDAO.buscarId(persona);
			
			if (IdPersona < 0) {
				Persona p = new Persona(nombre, apellido); 
				IdPersona = pDAO.insertarPersona(p);
			}
			
			usrDAO.insertarUsuario(usuario);
			
		} catch (SQLException exc) {
			
			exc.printStackTrace();  //Que hariamos aca????????????
		}
		
		menuRegistracion.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUINICIO.name());	}

}
