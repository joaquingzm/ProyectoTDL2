package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.menuInicioListeners.RegistroListener;

public class MenuInicio extends JPanel{
	
	private JPanel panelPrincipal;
	private JTextField email;
	private JLabel emailLabel;
	private JTextField contraseña;
	private JLabel contraseñaLabel;
	private JButton registro;
	private JButton inicioDeSesion;
	private BorderLayout borderLayout;
	private JPanel panelDeInicioDeSesion;
	private JPanel panelRegistro;
	
	public MenuInicio() {
		
	}
	
	public MenuInicio(JPanel panelPrincipal) {
		
		this.panelPrincipal = panelPrincipal;
		email = new JTextField();
		emailLabel = new JLabel("Email:");
		contraseña = new JPasswordField();
		contraseñaLabel = new JLabel("Contraseña:");
		registro = new JButton();
		inicioDeSesion = new JButton();
		borderLayout = new BorderLayout();
		panelDeIni

		registro.addActionListener(new RegistroListener(this.panelPrincipal));
		
		this.setLayout(borderLayout);
		
		this.add(emailLabel);
		this.add(email);
		this.add(contraseñaLabel);
		this.add(contraseña);
		this.add(inicioDeSesion);
		this.add(registro);
	
		inicioDeSesion.setText("Iniciar sesión");
		registro.setText("Registrarse");
	}
}
