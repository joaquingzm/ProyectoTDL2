package vista.menuInicio;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.menuInicioListeners.InicioDeSesionListener;
import controlador.menuInicioListeners.RegistroListener;


@SuppressWarnings("serial")
public class MenuInicio extends JPanel{
	
	private JButton registro;
	private JTextField email;
	private JLabel emailLabel;
	private JTextField contraseña;
	private JLabel contraseñaLabel;
	private JButton inicioDeSesion;
	
	public MenuInicio() {
		
		email = new JTextField();
		emailLabel = new JLabel("Email:");
		contraseña = new JPasswordField();
		contraseñaLabel = new JLabel("Contraseña:");
		inicioDeSesion = new JButton();
		registro = new JButton();
		
		email.setPreferredSize(new Dimension(200,30));
		contraseña.setPreferredSize(new Dimension(200,30));
		inicioDeSesion.setPreferredSize(new Dimension(200,30));
		registro.setPreferredSize(new Dimension(200,30));
		
		inicioDeSesion.setText("Iniciar sesión");
		registro.setText("Registrarse");
		
		inicioDeSesion.addActionListener(new InicioDeSesionListener());
		registro.addActionListener(new RegistroListener());
		
		this.setLayout(new GridBagLayout());		
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

	public void realizarAccionesDeSalidaDelMenu() {
		email.setText("");
		contraseña.setText("");
	}
	public String extraerEmail() {
		return email.getText();
	}
	
	public String extraerContraseña() {
		return contraseña.getText();
	}

}
 