package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daos.FactoryDAO;
import modelos.GestorDeDatosGlobales;
import modelos.Usuario;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.MenuInicio;


public class InicioDeSesionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		MenuInicio menuInicio = framePrincipal.getMenuInicio();
		
		JTextField email = menuInicio.getEmail();
		JTextField contraseña = menuInicio.getContraseña();
		
		String emailTexto = email.getText();
		String contraseñaTexto = contraseña.getText();
		
		if (emailTexto.isEmpty() || contraseñaTexto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int idUsuario = -1;
		Usuario usuario = null;
		
		try {
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(emailTexto, contraseñaTexto);
			if (idUsuario < 0) {
				JOptionPane.showMessageDialog(null, "La información ingresada no corresponde a ningun usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			usuario = FactoryDAO.getUsuarioDAO().buscarUsuario(idUsuario);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			return;
		}
		
		
		GestorDeDatosGlobales.getFramePrincipal().actualizarEncabezados(usuario);
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		GestorDeDatosGlobales.setIdUsuario(idUsuario);
		
		email.setText("");
		contraseña.setText("");
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		
	}
}
