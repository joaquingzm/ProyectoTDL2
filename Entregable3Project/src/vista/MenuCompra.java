package vista;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;
import modelos.MonedaFiduciaria;
import modelos.Stock;

public class MenuCompra extends JPanel{
	
	private JLabel stockDisponible;
	private JLabel precioDeCompra;
	private JLabel textCantidadDeFIAT;
	private JTextField cantidadDeFIAT;
	private JComboBox<String> selectorFIAT;
	private JButton convertir;
	private JLabel textEquilavenciaEnFIAT;
	private JButton realizarCompra;
	private JButton cancelar;
	
	public MenuCompra() {
	
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500,200));

		stockDisponible = new JLabel("Stock disponible: ");
		precioDeCompra = new JLabel("Precio de compra: ");
		textCantidadDeFIAT = new JLabel("Quiero comprar con ");
		textCantidadDeFIAT.setPreferredSize(new Dimension(130,30 ));
		cantidadDeFIAT = new JTextField();
		cantidadDeFIAT.setPreferredSize(new Dimension(200,30));
		selectorFIAT = new JComboBox();
		cargarSelectorFIAT();
		convertir = new JButton("Convertir");
//		convertir.addActionListener(new ConvertirListener());
		textEquilavenciaEnFIAT = new JLabel("Equivale a...");
		realizarCompra = new JButton("Realizar Compra");
//		realizarCompra.addActionListener(new RealizarCompraListener());
		cancelar = new JButton("Cancelar");
//		cancelar.addActionListener(new CancelarListener());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.gridwidth = 4;
		this.add(stockDisponible,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(precioDeCompra,gbc);
		//gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(textCantidadDeFIAT,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(cantidadDeFIAT,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(selectorFIAT,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(convertir,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(textEquilavenciaEnFIAT,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(realizarCompra,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		this.add(cancelar,gbc);
	}
	
	private void cargarSelectorFIAT() {
		List<MonedaFiduciaria> listaFIATs = GestorDeDatosGlobales.getListaMonedasFiduciaria();
	
		Vector<String> arraySiglas = new Vector<String>();
		
		for(MonedaFiduciaria m : listaFIATs) {
			arraySiglas.add(m.getSigla());
		}
		
		selectorFIAT = new JComboBox<String>(arraySiglas);
	}
	
	public void cargarMoneda(Stock stock) {
		System.out.println("HOLA"+stock.toString());
		Criptomoneda c = stock.getCriptomoneda();
		
		stockDisponible.setText("Stock disponible: "+stock.getCantidad()+"("+c.getSigla()+")");

		precioDeCompra.setText("Precio de compra: "+c.getSigla()+c.getPrecioEnDolar());
		
	}
}
