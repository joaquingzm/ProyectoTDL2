package vista;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuInicio extends JPanel{
	
	private Map<String, String> identificadoresDePaneles;
	private JPanel panelPrincipal;
	
	public MenuInicio() {
		
	}
	
	public MenuInicio(Map<String, String> identificadoresDePaneles, JPanel panelPrincipal) {
		this.identificadoresDePaneles = identificadoresDePaneles;
		this.panelPrincipal = panelPrincipal;
	}
	
	
	private JTextField email = new JTextField();
	private JLabel emailLabel = new JLabel("Email:");
	private JTextField contraseña = new JTextField();
	private JLabel contraseñaLabel = new JLabel("Contraseña:");
	private JButton registrarse = new JButton();
	private JButton iniciarSesion = new JButton();
	private GridLayout gridLayout = new GridLayout(4,2,100,100);
	
	public void iniciar() {
		iniciarSesion.setText("Iniciar sesión");
		registrarse.setText("Registrarse");
		this.setLayout(gridLayout);
		this.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		this.add(emailLabel);
		this.add(email);
		this.add(contraseñaLabel);
		this.add(contraseña);
		this.add(iniciarSesion);
		this.add(registrarse);
	}
	
	
}
