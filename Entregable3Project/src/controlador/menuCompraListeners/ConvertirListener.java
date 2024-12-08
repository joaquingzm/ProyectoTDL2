package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConvertirListener implements ActionListener{

	private JTextField cantidad;
	private JComboBox sigla;
	private JLabel mensaje;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		double cantidadNumero = Double.parseDouble(cantidad.getText());
		String siglaText = (String) sigla.getSelectedItem();
		
		
		
	}

}
