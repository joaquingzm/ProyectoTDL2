package vista.menuCompra;

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

import controlador.menuCompraListeners.CancelarListener;
import controlador.menuCompraListeners.ConvertirListener;
import controlador.menuCompraListeners.RealizarCompraListener;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;

@SuppressWarnings("serial")
public class MenuCompra extends JPanel{
	
	private JLabel stockDisponible;
	private JLabel sigla;
	private JLabel precioDeCompra;
	private JLabel textCantidadDeFIAT;
	private JTextField cantidadDeFIAT;
	private JComboBox<String> selectorFIAT;
	private JButton convertir;
	private JLabel textEquilavenciaEnFIAT;
	private JButton realizarCompra;
	private JButton cancelar;
	
	public MenuCompra() {
	
		stockDisponible = new JLabel("Stock disponible: ");
		sigla = new JLabel("");
		precioDeCompra = new JLabel("Precio de compra: ");
		textCantidadDeFIAT = new JLabel("Quiero comprar con ");
		cantidadDeFIAT = new JTextField();
		selectorFIAT = new JComboBox<String>();
		convertir = new JButton("Convertir");
		textEquilavenciaEnFIAT = new JLabel("Equivale a... ");
		realizarCompra = new JButton("Realizar Compra");
		cancelar = new JButton("Cancelar");
		
		textCantidadDeFIAT.setPreferredSize(new Dimension(130,30 ));
		cantidadDeFIAT.setPreferredSize(new Dimension(200,30));
		
		convertir.addActionListener(new ConvertirListener());
		realizarCompra.addActionListener(new RealizarCompraListener());
		cancelar.addActionListener(new CancelarListener());

		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500,200));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.gridwidth = 4;
		this.add(stockDisponible,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(sigla,gbc);
		
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
	
	private void cargarSelectorFIAT(List<MonedaFiduciaria> listaFIATs) {

		Vector<String> arraySiglas = new Vector<String>();
		
		for(MonedaFiduciaria m : listaFIATs) {
			arraySiglas.add(m.getSigla());
		}
		
		selectorFIAT = new JComboBox<String>(arraySiglas);
	}
	
	public void cargarMoneda(Stock stock) {
		
		Criptomoneda c = stock.getCriptomoneda();
		
		stockDisponible.setText("Stock disponible: "+stock.getCantidad()+"("+c.getNombre()+")");
		sigla.setText(c.getSigla());
		precioDeCompra.setText("Precio de compra: "+c.getSigla()+c.getPrecioEnDolar());
		
	}
	
	public Double extraerCantidadAConvertir() {
		
		return Double.parseDouble(cantidadDeFIAT.getText());
		
	}
	
	public String extraerSiglaDeMonedaAConvertir() {
		
		return selectorFIAT.getSelectedItem().toString();
	}
	
	public String extraerSiglaDeCriptomoneda() {
		
		return sigla.getText();
	}
	
	public void actualizarConversion(Double cantidad) {
		
		textEquilavenciaEnFIAT.setText("Equivale a... "+cantidad+" "+sigla.getText());
	}
	
	
}
