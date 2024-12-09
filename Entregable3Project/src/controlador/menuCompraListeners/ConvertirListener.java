package controlador.menuCompraListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import daos.FactoryDAO;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;

public class ConvertirListener implements ActionListener{

	private JTextField cantidad;
	private JComboBox<String> sigla;
	private JLabel mensaje;
	private Criptomoneda criptomonedaElegida;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		double cantidadNumero = Double.parseDouble(cantidad.getText()), precioEnDolarDeMonedaFiduciaria = 1, cantidadTotalDeDolares, cantidadTotalDeCripto;
		String siglaText = (String) sigla.getSelectedItem();
		
		List<MonedaFiduciaria> fiats = null;
		
		try {
			
			fiats = FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias();
			
		} catch (SQLException exc) {
			
			exc.printStackTrace();
			return;
		}
		
		for (MonedaFiduciaria mf : fiats) {
			if (mf.getSigla().equals(siglaText)) {
				precioEnDolarDeMonedaFiduciaria = mf.getPrecioEnDolar();
				break;
			}
		}
		
		cantidadTotalDeDolares = precioEnDolarDeMonedaFiduciaria * cantidadNumero;
		
		cantidadTotalDeCripto = cantidadTotalDeDolares / criptomonedaElegida.getPrecioEnDolar();
		
		mensaje.setText("Equivale a " + cantidadTotalDeCripto + criptomonedaElegida.getSigla());
		
	}

}
