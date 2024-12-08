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
import modelos.Usuario;
import vista.IdentificadoresDePaneles;

public class RegistroListener implements ActionListener{

	private String nombre;
	private String apellido;
	private String email;
	private String contraseña;
	private boolean aceptoTerminosCondiciones;
	
	public RegistroListener(String nombre, String apellido, String email, String contraseña,
			boolean aceptoTerminosCondiciones) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
		this.aceptoTerminosCondiciones = aceptoTerminosCondiciones;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
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
			
			IdPersona = pDAO.buscarId(nombre, apellido);
			
			if (IdPersona < 0) {
				Persona p = new Persona(nombre, apellido); 
				IdPersona = pDAO.insertarPersona(p);
			}
			
			usrDAO.insertarUsuario(IdPersona, email, contraseña, aceptoTerminosCondiciones);
			
		} catch (SQLException exc) {
			
			exc.printStackTrace();  //Que hariamos aca????????????
		}
		
		
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}

}
