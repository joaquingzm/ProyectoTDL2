package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.encabezado.CerrarSesionListener;
import daos.FactoryDAO;
import modelos.GestorDeDatosGlobales;

public class Encabezado extends JPanel{

	private JLabel icono;
	private JLabel nombreYApellido;
	private JButton cerrarSesion;
	
	public Encabezado() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		icono = new JLabel(); //Pasarle un ImageIcon al constructor
		nombreYApellido = new JLabel(FactoryDAO.getPersonaDAO().getNombreYApellido(FactoryDAO.getUsuarioDAO().getIdPersona(GestorDeDatosGlobales.getIdUsuario())));
		cerrarSesion = new JButton("Cerrar sesión");
		
		cerrarSesion.addActionListener(new CerrarSesionListener());
		
		this.add(icono);
		this.add(nombreYApellido);
		this.add(cerrarSesion);
		
	
	}

}
