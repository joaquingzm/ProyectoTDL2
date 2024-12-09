package vista;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.encabezadoListeners.CerrarSesionListener;

public class Encabezado extends JPanel{

	private JLabel icono;
	private JLabel nombreYApellido;
	private JButton cerrarSesion;
	
	public Encabezado() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		icono = new JLabel(); //Pasarle un ImageIcon al constructor
		nombreYApellido = new JLabel("");
		//nombreYApellido = new JLabel(FactoryDAO.getPersonaDAO().buscarPersona(FactoryDAO.getUsuarioDAO().getIdPersona(GestorDeDatosGlobales.getIdUsuario())).getNombre()+FactoryDAO.getPersonaDAO().buscarPersona(FactoryDAO.getUsuarioDAO().getIdPersona(GestorDeDatosGlobales.getIdUsuario())).getApellido());
		cerrarSesion = new JButton("Cerrar sesión");
		
		cerrarSesion.addActionListener(new CerrarSesionListener());
		
		this.add(icono);
		this.add(nombreYApellido);
		this.add(cerrarSesion);
	}

	public JLabel getIcono() {
		return icono;
	}
	
	public JLabel getNombreYApellido() {
		return nombreYApellido;
	}
}
