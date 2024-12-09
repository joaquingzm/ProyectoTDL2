package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.menuInicioListeners.InicioDeSesionListener;
import controlador.menuInicioListeners.RegistroListener;


public class MenuInicio extends JPanel{
	
	private JButton registro;
	private JTextField email;
	private JLabel emailLabel;
	private JTextField contraseña;
	private JLabel contraseñaLabel;
	private JButton inicioDeSesion;
	
	public MenuInicio() {
		
		email = new JTextField();
		email.setPreferredSize(new Dimension(200,30));
		emailLabel = new JLabel("Email:");
		contraseña = new JPasswordField();
		contraseña.setPreferredSize(new Dimension(200,30));
		contraseñaLabel = new JLabel("Contraseña:");
		inicioDeSesion = new JButton();
		inicioDeSesion.setPreferredSize(new Dimension(200,30));
		registro = new JButton();
		registro.setPreferredSize(new Dimension(200,30));
		
		inicioDeSesion.setText("Iniciar sesión");
		registro.setText("Registrarse");
		
		inicioDeSesion.addActionListener(new InicioDeSesionListener());
		registro.addActionListener(new RegistroListener());
		
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(emailLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(email,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(contraseñaLabel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(contraseña,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(inicioDeSesion,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		this.add(registro,gbc);
	}

	public JButton getRegistro() {
		return registro;
	}

	public void setRegistro(JButton registro) {
		this.registro = registro;
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

	public JButton getInicioDeSesion() {
		return inicioDeSesion;
	}

	public void setInicioDeSesion(JButton inicioDeSesion) {
		this.inicioDeSesion = inicioDeSesion;
	}
}
 