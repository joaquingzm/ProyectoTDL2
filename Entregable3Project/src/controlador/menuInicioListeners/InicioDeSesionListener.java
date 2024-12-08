package controlador.menuInicioListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import daos.FactoryDAO;
import modelos.GestorDeDatosGlobales;
import vista.IdentificadoresDePaneles;


public class InicioDeSesionListener implements ActionListener{

	private String email;
	private String contraseña;
	
	public InicioDeSesionListener(String email, String contraseña) {
		this.email = email;
		this.contraseña = contraseña;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (email.isEmpty() || contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int idUsuario = -1;
		
		try {
			
			idUsuario = FactoryDAO.getUsuarioDAO().buscarId(email, contraseña);
			
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
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUMISACTIVOS.name());
		
	}
}
