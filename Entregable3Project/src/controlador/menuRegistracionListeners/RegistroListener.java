package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.FactoryDAO;
import daos.PersonaDAO;
import daos.UsuarioDAO;
import modelos.GestorDeDatosGlobales;
import modelos.Persona;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.MenuRegistracion;

public class RegistroListener implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		MenuRegistracion menuRegistracion = framePrincipal.getMenuRegistracion();
		
		JTextField nombre = menuRegistracion.getNombre();
		JTextField apellido = menuRegistracion.getApellido();
		JTextField email = menuRegistracion.getEmail();
		JTextField contraseña = menuRegistracion.getContraseña();
		JCheckBox terminosCondicionesCaja = menuRegistracion.getTerminosCondicionesCaja();
		
		String nombreTexto = nombre.getText();
		String apellidoTexto = nombre.getText();
		String emailTexto = email.getText();
		String contraseñaTexto = contraseña.getText();
		boolean aceptoTerminosCondiciones = terminosCondicionesCaja.isSelected();
		
		if (nombreTexto.isEmpty() || apellidoTexto.isEmpty() || emailTexto.isEmpty() || contraseñaTexto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!aceptoTerminosCondiciones) {
			JOptionPane.showMessageDialog(null, "No se aceptaron los Terminos y Condiciones.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		UsuarioDAO usrDAO= FactoryDAO.getUsuarioDAO();
		
		if (!usrDAO.existeEmail(email)) {
			JOptionPane.showMessageDialog(null, "El email propuesto esta asociado a otro usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		PersonaDAO pDAO = FactoryDAO.getPersonaDAO();
		int IdPersona = -1;
		
		try {
			
			IdPersona = pDAO.buscarId(nombreTexto, apellidoTexto);
			
			if (IdPersona < 0) {
				Persona p = new Persona(nombreTexto, apellidoTexto); 
				IdPersona = pDAO.insertarPersona(p);
			}
			
			usrDAO.insertarUsuario(IdPersona, emailTexto, contraseñaTexto, aceptoTerminosCondiciones);
			
		} catch (SQLException exc) {
			
			exc.printStackTrace();  //Que hariamos aca????????????
		}
		
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}

}
