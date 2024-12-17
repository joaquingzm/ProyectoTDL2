package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.encabezadoListeners.CerrarSesionListener;
import modelos.Persona;
import modelos.Usuario;

@SuppressWarnings("serial")
public class Encabezado extends JPanel{

	private JLabel icono;
	private JLabel nombreYApellido;
	private JButton cerrarSesion;
	
	public Encabezado() {

		this.setLayout(new GridBagLayout());
		
		this.setPreferredSize(new Dimension(400,70));
		
		icono = new JLabel(); //Pasarle un ImageIcon al constructor
		cerrarSesion = new JButton("Cerrar sesion");
		nombreYApellido = new JLabel("");
		cerrarSesion.addActionListener(new CerrarSesionListener());
		
		ImageIcon userIcon = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/USER.png"));
		Image userIconEscalado = userIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); 
		
		icono.setIcon(new ImageIcon(userIconEscalado));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,5,5,5);
		
		gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridheight = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        this.add(icono, gbc);

        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.gridheight = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.weightx = 1.0; 
        this.add(nombreYApellido, gbc);

        gbc.gridx = 1; 
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.weightx = 0; 
        this.add(cerrarSesion, gbc);
	}

	public void cambiarColorBackground(Color color) {
		this.setBackground(color);
		nombreYApellido.setBackground(color);
		this.cerrarSesion.setBackground(color);
	}
	
	public void cambiarColorForeground(Color color) {
		this.setForeground(color);
		nombreYApellido.setForeground(color);
		this.cerrarSesion.setForeground(color);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		Persona persona = usuario.getPersona();
		nombreYApellido.setText(persona.getNombre()+"   "+persona.getApellido());
	}
	
}
