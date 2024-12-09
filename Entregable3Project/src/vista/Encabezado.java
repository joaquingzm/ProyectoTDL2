package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.encabezadoListeners.CerrarSesionListener;
import modelos.Persona;
import modelos.Usuario;

public class Encabezado extends JPanel{

	private JLabel icono;
	private JLabel nombreYApellido;
	private JButton cerrarSesion;
	
	public Encabezado() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.setPreferredSize(new Dimension(200,50));
		
		icono = new JLabel(); //Pasarle un ImageIcon al constructor
		cerrarSesion = new JButton("Cerrar sesion");
		nombreYApellido = new JLabel("");
		cerrarSesion.addActionListener(new CerrarSesionListener());
		
		this.add(icono);
		this.add(nombreYApellido);
		this.add(cerrarSesion);
	}

	public void actualizarUsuario(Usuario usuario) {
		Persona persona = usuario.getPersona();
		nombreYApellido.setText(persona.getNombre()+" "+persona.getApellido());
	}
	public JLabel getIcono() {
		return icono;
	}

	public void setIcono(JLabel icono) {
		this.icono = icono;
	}

	public JLabel getNombreYApellido() {
		return nombreYApellido;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido.setText(nombreYApellido);;
	}
	
}
