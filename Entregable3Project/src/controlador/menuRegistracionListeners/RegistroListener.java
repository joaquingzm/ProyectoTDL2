package controlador.menuRegistracionListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegistroListener implements ActionListener{

	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JTextField contraseña;
	private JCheckBox terminosCondicionesCaja;
	private JPanel panelPrincipal;
	private CardLayout cardLayout;
	
	public RegistroListener(JTextField nombre, JTextField apellido, JTextField email, JTextField contraseña,
			JCheckBox terminosCondicionesCaja, JPanel panelPrincipal) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
		this.terminosCondicionesCaja = terminosCondicionesCaja;
		this.panelPrincipal = panelPrincipal;
		this.cardLayout = (CardLayout) this.panelPrincipal.getLayout();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (nombre.getText().isEmpty() || apellido.getText().isEmpty() || email.getText().isEmpty() || contraseña.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados no se completó.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!terminosCondicionesCaja.isSelected()) {
			JOptionPane.showMessageDialog(null, "No se aceptaron los Terminos y Condiciones.", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
	}

}
