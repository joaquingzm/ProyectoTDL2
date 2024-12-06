package vista;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenuInicio extends JPanel{
	
	private JPanel panelPrincipal;
	private JTextField email = new JTextField();
	private JLabel emailLabel = new JLabel("Email:");
	private JTextField contraseña = new JPasswordField();
	private JLabel contraseñaLabel = new JLabel("Contraseña:");
	private JButton registro = new JButton();
	private JButton inicioDeSesion = new JButton();
	private GridLayout gridLayout = new GridLayout(4,2,100,100);
	
	public MenuInicio() {
		
	}
	
	public MenuInicio(JPanel panelPrincipal) {
		
		this.panelPrincipal = panelPrincipal;
		this.setLayout(gridLayout);
		this.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
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
