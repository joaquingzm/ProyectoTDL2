package vista;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.GestorDeDatosGlobales;
import modelos.MonedaFiduciaria;

public class MenuCompra extends JPanel{
	
	private JLabel stock;
	private JLabel precioDeCompra;
	private JLabel textCantidadDeFIAT;
	private JTextField cantidadDeFIAT;
	private JComboBox<String> selectorFIAT;
	private JButton convertir;
	private JTextField equivalenciaEnFIAT;
	
	public MenuCompra() {
	
		this.setLayout(new GridBagLayout());

		stock = new JLabel("Stock disponible: ");
		precioDeCompra = new JLabel("Precio de compra: ");
		textCantidadDeFIAT = new JLabel("Quiero comprar con ");
		textCantidadDeFIAT.setPreferredSize(new Dimension(200,30 ))
		cantidadDeFIAT = new JTextField();
		selectorFIAT = new JComboBox();
		cargarSelectorFIAT();
		convertir = new JButton("Convertir");
//		convertir.addActionListener(new ConvertirListener());
		equivalenciaEnFIAT = new JTextField();
		equivalenciaEnFIAT.setPreferredSize(new Dimension(200,30));
	}
	
	private void cargarSelectorFIAT() {
		List<MonedaFiduciaria> listaFIATs = GestorDeDatosGlobales.getListaMonedasFiduciaria();
	
		Vector<String> arraySiglas = new Vector<String>();
		
		for(MonedaFiduciaria m : listaFIATs) {
			arraySiglas.add(m.getSigla());
		}
		
		selectorFIAT = new JComboBox<String>(arraySiglas);
	}
}
