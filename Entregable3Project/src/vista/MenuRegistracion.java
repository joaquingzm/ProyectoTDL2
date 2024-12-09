package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.menuRegistracionListeners.RegistroListener;
import controlador.menuRegistracionListeners.VolverListener;
import modelos.GestorDeDatosGlobales;

public class MenuRegistracion extends JPanel {
	
	private JTextField nombre;
	private JLabel nombreLabel;
	private JTextField apellido;
	private JLabel apellidoLabel;
	private JTextField email;
	private JLabel emailLabel;
	private JTextField contraseña;
	private JLabel contraseñaLabel;
	private JButton registrar;
	private JCheckBox terminosCondicionesCaja;
	private JLabel terminosCondicionesLabel;
	private JButton volver;
	
	
	public MenuRegistracion() {
		
		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(200,30));
		nombreLabel = new JLabel("Nombre");
		apellido = new JTextField();
		apellido.setPreferredSize(new Dimension(200,30));
		apellidoLabel = new JLabel("Apellido");
		email = new JTextField();
		email.setPreferredSize(new Dimension(200,30));
		emailLabel = new JLabel("Email:");
		contraseña = new JPasswordField();
		contraseña.setPreferredSize(new Dimension(200,30));
		contraseñaLabel = new JLabel("Contraseña:");
		registrar = new JButton("Registrar");
		registrar.setPreferredSize(new Dimension(200,30));
		terminosCondicionesCaja = new JCheckBox();
		terminosCondicionesLabel = new JLabel("Acepto terminos y condiciones");
		volver = new JButton("Volver");
		volver.setPreferredSize(new Dimension(200,30));
		
		registrar.addActionListener(new RegistroListener());
		volver.addActionListener(new VolverListener());
		
		this.setLayout(new GridBagLayout());
		
		this.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10,10,10,10);		
		gbc.weightx = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(nombreLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(nombre,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(apellidoLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(apellido,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(emailLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(email,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(contraseñaLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(contraseña,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(terminosCondicionesCaja,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(terminosCondicionesLabel,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(registrar,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		this.add(volver,gbc);
	}


	public JTextField getNombre() {
		return nombre;
	}


	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}


	public JLabel getNombreLabel() {
		return nombreLabel;
	}


	public void setNombreLabel(JLabel nombreLabel) {
		this.nombreLabel = nombreLabel;
	}


	public JTextField getApellido() {
		return apellido;
	}


	public void setApellido(JTextField apellido) {
		this.apellido = apellido;
	}


	public JLabel getApellidoLabel() {
		return apellidoLabel;
	}


	public void setApellidoLabel(JLabel apellidoLabel) {
		this.apellidoLabel = apellidoLabel;
	}


	public JTextField getEmail() {
		return email;
	}


	public void setEmail(JTextField email) {
		this.email = email;
	}


	public JLabel getEmailLabel() {
		return emailLabel;
	}


	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}


	public JTextField getContraseña() {
		return contraseña;
	}


	public void setContraseña(JTextField contraseña) {
		this.contraseña = contraseña;
	}


	public JLabel getContraseñaLabel() {
		return contraseñaLabel;
	}


	public void setContraseñaLabel(JLabel contraseñaLabel) {
		this.contraseñaLabel = contraseñaLabel;
	}


	public JButton getRegistrar() {
		return registrar;
	}


	public void setRegistrar(JButton registrar) {
		this.registrar = registrar;
	}


	public JCheckBox getTerminosCondicionesCaja() {
		return terminosCondicionesCaja;
	}


	public void setTerminosCondicionesCaja(JCheckBox terminosCondicionesCaja) {
		this.terminosCondicionesCaja = terminosCondicionesCaja;
	}


	public JLabel getTerminosCondicionesLabel() {
		return terminosCondicionesLabel;
	}


	public void setTerminosCondicionesLabel(JLabel terminosCondicionesLabel) {
		this.terminosCondicionesLabel = terminosCondicionesLabel;
	}


	public JButton getVolver() {
		return volver;
	}


	public void setVolver(JButton volver) {
		this.volver = volver;
	}
	

}
