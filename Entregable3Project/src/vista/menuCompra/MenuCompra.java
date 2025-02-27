package vista.menuCompra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

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
	private JLabel comisionEnCripto;
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
		comisionEnCripto = new JLabel("");
		precioDeCompra = new JLabel("Precio de compra: ");
		textCantidadDeFIAT = new JLabel("Quiero comprar con ");
		cantidadDeFIAT = new JTextField();
		selectorFIAT = new JComboBox<String>();
		convertir = new JButton("Convertir");
		textEquilavenciaEnFIAT = new JLabel("Equivale a... ");
		realizarCompra = new JButton("Realizar Compra");
		cancelar = new JButton("Cancelar");
		
		convertir.addActionListener(new ConvertirListener());
		realizarCompra.addActionListener(new RealizarCompraListener());
		cancelar.addActionListener(new CancelarListener());

		comisionEnCripto.setPreferredSize(new Dimension(200,10));
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(500,200));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(10,10,10,10);
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1; 
		gbc.weightx = 0;
		gbc.insets = new Insets(10, 10, 10, 5); 
		gbc.anchor = GridBagConstraints.WEST; 
		this.add(stockDisponible, gbc);

		gbc.gridx = 1; 
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 5, 10, 10); 
		gbc.weightx = 1; 
		gbc.anchor = GridBagConstraints.WEST;
		this.add(sigla, gbc);

		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.insets = new Insets(10, 10, 10, 10); 
		gbc.anchor = GridBagConstraints.EAST; 
		this.add(comisionEnCripto, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(precioDeCompra,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(textCantidadDeFIAT,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(cantidadDeFIAT,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(selectorFIAT,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		this.add(convertir,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		this.add(textEquilavenciaEnFIAT,gbc);
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(realizarCompra,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		this.add(cancelar,gbc);
	}
	
	public void cambiarColorLabels(Color color) {
		stockDisponible.setForeground(color);;
		sigla.setForeground(color);
		comisionEnCripto.setForeground(color);
		precioDeCompra.setForeground(color);;
		textCantidadDeFIAT.setForeground(color);;
		textEquilavenciaEnFIAT.setForeground(color);;
	}
	
	public void cambiarBackgroundColorBotones(Color color) {
		convertir.setBackground(color);
		realizarCompra.setBackground(color);
		cancelar.setBackground(color);
		selectorFIAT.setBackground(color);
	}
	
	public void cambiarForegroundColorBotones(Color color) {
		convertir.setForeground(color);
		realizarCompra.setForeground(color);
		cancelar.setForeground(color);
		selectorFIAT.setForeground(color);
	}
	
	public void cargarSelectorFIAT(List<MonedaFiduciaria> listaFIATs) {
	
		selectorFIAT.removeAllItems();
		for(MonedaFiduciaria m : listaFIATs) {
			selectorFIAT.addItem(m.getSigla());
		}
		
	}
	
	public void cargarMoneda(Stock stock) {
		
		Criptomoneda c = stock.getCriptomoneda();
		
		stockDisponible.setText("Stock disponible: "+stock.getCantidad()+"("+c.getNombre()+")");
		sigla.setText(c.getSigla());
		precioDeCompra.setText("Precio de compra: $"+c.getPrecioEnDolar());
		
	}
	
	public Double extraerCantidadAConvertir() throws NumberFormatException{
		
		Double cantidadAConvertir = Double.parseDouble(cantidadDeFIAT.getText());
		return cantidadAConvertir;
	}
	
	public String extraerSiglaDeMonedaAConvertir() {
		
		return selectorFIAT.getSelectedItem().toString();
	}
	
	public String extraerSiglaDeCriptomoneda() {
		
		return sigla.getText();
	}
	
	public void actualizarConversion(double cantidad) {
		
		textEquilavenciaEnFIAT.setText("Equivale a... "+cantidad+" "+sigla.getText());
	}
	
	public void actualizarComision(double comisionEnCripto) {
		this.comisionEnCripto.setText("Comisión("+sigla.getText()+"): "+comisionEnCripto);
	}
	
	public void realizarAccionesDeSalidaDelMenu() {
		comisionEnCripto.setText("");
		cantidadDeFIAT.setText("");
		textEquilavenciaEnFIAT.setText("Equivale a... ");
	}
}
