package vista.menuMisActivos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import vista.MiModeloDeTabla;

@SuppressWarnings("serial")
public class CentroMisActivos extends JPanel{

	private JTable activos;
	private JScrollPane activosScrollPane;
	private MiModeloDeTabla modelo;
	private String[] nombresColumnas;
	private MonedaFiduciaria mf;
	
	public CentroMisActivos(MonedaFiduciaria mfInicial) {
		
		modelo = new MiModeloDeTabla(null, nombresColumnas);
		activos = new JTable(modelo);
		activosScrollPane = new JScrollPane(activos);
		mf = mfInicial;
		
		activos.setRowHeight(64);
		activos.setAutoCreateRowSorter(true);
		
		this.setLayout(new BorderLayout());
	    this.nombresColumnas = new String[]{"", "Cripto", "Monto()"};
		this.add(activosScrollPane, BorderLayout.CENTER);
		
	}
	
	public void cambiarColorBackgroundTabla(Color color) {
		activos.setBackground(color);
		activosScrollPane.setBackground(color);
	}
	
	public void cambiarColorForegroundTabla(Color color) {
		activos.setForeground(color);
	}
	
	public void actualizarTabla(List<ActivoCripto> listaActivosCripto, List<ActivoMonedaFiduciaria> listaActivosFIAT) {
		System.out.println(mf.getSigla());
		Object[][] datos = this.recuperarDatos(listaActivosCripto, listaActivosFIAT);
		this.nombresColumnas[2] = "Monto("+mf.getSigla()+")";
		modelo.setDataVector(datos, this.nombresColumnas);
	}
	
	private Object[][] recuperarDatos(List<ActivoCripto> listaActivosCripto, List<ActivoMonedaFiduciaria> listaActivosFIAT){
		
		int dimFilasAC = listaActivosCripto.size(); 
		int dimFilasAF = listaActivosFIAT.size();
		
		Object[][] datos = new Object[dimFilasAC + dimFilasAF][3];
		ActivoCripto aC;
		ActivoMonedaFiduciaria aF;
		Criptomoneda c;
		MonedaFiduciaria m;
		
		for(int i=0;i<dimFilasAC;i++) {
			aC = listaActivosCripto.get(i);
			c = aC.getCriptomoneda();
			datos[i][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+c.getSigla()+".png"));
			datos[i][1] = c.getNombre();
			datos[i][2] = (aC.getCantidad() * c.getPrecioEnDolar())/mf.getPrecioEnDolar();
		}
		for(int i=0;i<dimFilasAF;i++) {
			aF = listaActivosFIAT.get(i);
			m = aF.getMonedaFIAT();
			datos[i+dimFilasAC][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+m.getSigla()+".png"));
			datos[i+dimFilasAC][1] = m.getNombre();
			datos[i+dimFilasAC][2] = aF.getCantidad() * m.getPrecioEnDolar();
		}
		
		return datos;
	}
	
	public void actualizarMonedaFIAT(MonedaFiduciaria nuevaMF) {
	
		this.actualizarMonedaFIATEnTabla(nuevaMF);
		this.nombresColumnas[2] = "Monto("+nuevaMF.getSigla()+")";
		modelo.setColumnIdentifiers(nombresColumnas);
		this.mf = nuevaMF;
		
	}
	
	private void actualizarMonedaFIATEnTabla(MonedaFiduciaria nuevaMF) {
		
		for(int i=0;i<modelo.getRowCount();i++) {
			double precioEnViejaMF = (double) modelo.getValueAt(i,2);
			double precioEnNuevaMoneda = (precioEnViejaMF*this.mf.getPrecioEnDolar()/nuevaMF.getPrecioEnDolar());
			modelo.setValueAt(precioEnNuevaMoneda, i, 2);
		}
	}
	
	public String getMonedaFIATSigla() {
		return mf.getSigla();
	}
}
