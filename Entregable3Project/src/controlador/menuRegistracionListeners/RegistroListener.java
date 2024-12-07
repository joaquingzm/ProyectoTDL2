package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.FactoryDAO;
import daos.UsuarioDAO;
import modelos.GestorDeDatosGlobales;
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
		
		if (nombre.getText().isEmpty() || apellido.getText().isEmpty() || email.getText().isEmpty() || contraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!terminosCondicionesCaja.isSelected()) {
			JOptionPane.showMessageDialog(null, "No se aceptaron los Terminos y Condiciones.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		UsuarioDAO usrDAO= FactoryDAO.getUsuarioDAO();
		
		if (!usrDAO.existeEmail(email.getText())) {
			JOptionPane.showMessageDialog(null, "El email propuesto esta asociado a otro usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		usrDAO.crearUsuario(nombre.getText(), apellido.getText(), email.getText(), contraseña.getText());
		
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUINICIO.name());
	}

}
