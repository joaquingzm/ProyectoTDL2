package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

public class MenuRegistracion extends JPanel {
	
	private JPanel panelPrincipal;
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
	
	public MenuRegistracion() {
		
	}
	
	public MenuRegistracion(JPanel panelPrincipal) {
		
		this.panelPrincipal = panelPrincipal;
		nombre = new JTextField();
		nombreLabel = new JLabel("Nombre");
		apellido = new JTextField();
		apellidoLabel = new JLabel("Apellido");
		email = new JTextField();
		emailLabel = new JLabel("Email:");
		contraseña = new JPasswordField();
		contraseñaLabel = new JLabel("Contraseña:");
		registrar = new JButton("Registrar");
		terminosCondicionesCaja = new JCheckBox();
		terminosCondicionesLabel = new JLabel("Acepto terminos y condiciones");
		
		registrar.addActionListener(new RegistroListener(nombre, apellido, email, contraseña, terminosCondicionesCaja, panelPrincipal));
		
		
		this.setBorder(BorderFactory.createTitledBorder(null, "Título del Panel", TitledBorder.CENTER, TitledBorder.TOP)); 
		//BorderFactory.createEmptyBorder(40,40,40,40)
		this.setLayout(new GridLayout(6,2,100,100));
		this.add(nombreLabel);
		this.add(nombre);
		this.add(apellidoLabel);
		this.add(apellido);
		this.add(emailLabel);
		this.add(email);
		this.add(contraseñaLabel);
		this.add(contraseña);
		
		this.add(terminosCondicionesCaja);
		this.add(terminosCondicionesLabel);
		this.add(registrar);
		
	
	}
	

}
