package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.HeadlessException;
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

	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JTextField contraseña;
	private JCheckBox terminosCondicionesCaja;
	
	public RegistroListener(JTextField nombre, JTextField apellido, JTextField email, JTextField contraseña,
			JCheckBox terminosCondicionesCaja) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
		this.terminosCondicionesCaja = terminosCondicionesCaja;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nombreTexto = nombre.getText();
		String apellidoTexto = apellido.getText();
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
		
		//CAMBIAR POR NUESTRA EXCEPCION !!!!
		try {
			if (usrDAO.existeEmail(emailTexto)) {
				throw new SQLException();
			}
		} catch (SQLException e1) {
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
		
		nombre.setText("");
		apellido.setText("");
		email.setText("");
		contraseña.setText("");
		terminosCondicionesCaja.setSelected(false);
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}

}
