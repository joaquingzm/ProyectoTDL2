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
		
		registrar.addActionListener(new RegistroListener(nombre, apellido, email, contraseña, terminosCondicionesCaja));
		volver.addActionListener(new VolverListener());
		
		this.setLayout(new GridBagLayout());
		
		//Desconozco si esto hace algo, pareciera que no
		this.setBorder(BorderFactory.createTitledBorder(null, "Título del Panel", TitledBorder.LEFT, TitledBorder.ABOVE_TOP)); 
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
	

}
