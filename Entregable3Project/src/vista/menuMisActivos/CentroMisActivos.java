package vista.menuMisActivos;

import java.awt.BorderLayout;
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
	private MiModeloDeTabla modelo;
	private String[] nombresColumnas;
	
	public CentroMisActivos() {
		
		modelo = new MiModeloDeTabla(null, nombresColumnas);
		activos = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(activos);
		
		activos.setRowHeight(64);
		activos.setAutoCreateRowSorter(true);
		
		this.setLayout(new BorderLayout());
	    this.nombresColumnas = new String[]{"", "Cripto", "Monto($)"};
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void actualizarTabla(List<ActivoCripto> listaActivosCripto, List<ActivoMonedaFiduciaria> listaActivosFIAT) {
		
		Object[][] datos = this.recuperarDatos(listaActivosCripto, listaActivosFIAT);
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
			datos[i][2] = aC.getCantidad();
		}
		for(int i=0;i<dimFilasAF;i++) {
			aF = listaActivosFIAT.get(i);
			m = aF.getMonedaFIAT();
			datos[i+dimFilasAC][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+m.getSigla()+".png"));
			datos[i+dimFilasAC][1] = m.getNombre();
			datos[i+dimFilasAC][2] = aF.getCantidad();
		}
		
		return datos;
	}
	
}
