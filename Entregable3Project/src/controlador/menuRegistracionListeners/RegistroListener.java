package controlador.menuRegistracionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import daos.PersonaDAO;
import daos.UsuarioDAO;
import excepciones.CheckboxException;
import excepciones.DataException;
import excepciones.ExistenciaEmailException;
import excepciones.InformacionExcepciones;
import excepciones.TextFieldException;
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
		
		UsuarioDAO usrDAO= FactoryDAO.getUsuarioDAO();
		
		try {
			if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty()) throw new TextFieldException();
			if (!aceptoTerminosCondiciones) throw new CheckboxException();
			if (usrDAO.existeEmail(email)) throw new ExistenciaEmailException();
			
		} catch (DataException exc1) {
			FramePrincipal.mostrarAviso(exc1.getProblemaTitulo(), exc1.getProblemaCuerpo());
			return;
			
		} catch (SQLException exc2) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		
		PersonaDAO pDAO = FactoryDAO.getPersonaDAO();
		Persona persona = new Persona(nombre,apellido);
		Usuario usuario = new Usuario(persona,email,contraseña,aceptoTerminosCondiciones);
		int IdPersona;
		
		try {
			
			IdPersona = pDAO.buscarId(persona);
			
			if (IdPersona < 0) {
				pDAO.insertarPersona(persona);
			}
			
			usrDAO.insertarUsuario(usuario);
			
		} catch (SQLException exc) {
			
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}
		
		menuRegistracion.realizarAccionesDeSalidaDelMenu();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUINICIO.name());	
		
	}

}
