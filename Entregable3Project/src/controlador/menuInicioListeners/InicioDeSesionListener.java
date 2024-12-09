package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import daos.FactoryDAO;
import modelos.GestorDeDatosGlobales;
import vista.IdentificadoresDePaneles;


public class InicioDeSesionListener implements ActionListener{

	private JTextField email;
	private JTextField contraseña;
	
	public InicioDeSesionListener(JTextField email, JTextField contraseña) {
		this.email = email;
		this.contraseña = contraseña;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String emailTexto = email.getText();
		String contraseñaTexto = contraseña.getText();
		
		if (emailTexto.isEmpty() || contraseñaTexto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int idUsuario = -1;
		
		try {
			
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(emailTexto, contraseñaTexto);
			
			if (idUsuario < 0) {
				JOptionPane.showMessageDialog(null, "La información ingresada no corresponde a ningun usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			
		}
		
		JPanel panelPrincipal = GestorDeDatosGlobales.getPanelPrincipal();
		CardLayout cardLayout = (CardLayout) panelPrincipal.getLayout();
		
		GestorDeDatosGlobales.setIdUsuario(idUsuario);
		
		email.setText("");
		contraseña.setText("");
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		
	}
}
