package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;

public class CentroMisActivos extends JPanel{

	private JTable activos;
	private MiModeloDeTabla modelo;
	private String[] nombresColumnas;
	
	public CentroMisActivos() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500,200));
		
	    this.nombresColumnas = new String[]{"", "Cripto", "Monto($)"};
		
        modelo = new MiModeloDeTabla(null, nombresColumnas);
		
		activos = new JTable(modelo);
		activos.setRowHeight(64);
		activos.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPane = new JScrollPane(activos);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void actualizarTabla(LinkedList<ActivoCripto> listaActivosCripto, LinkedList<ActivoMonedaFiduciaria> listaActivosFIAT) {
		
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
			System.out.println("vista/iconos/"+c.getSigla()+".png");
			datos[i][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+c.getSigla()+".png"));
			datos[i][1] = c.getNombre();
			datos[i][2] = aC.getCantidad();
		}
		
		for(int i=0;i<dimFilasAF;i++) {
			aF = listaActivosFIAT.get(i);
			m = aF.getMonedaFIAT();
			System.out.println("vista/iconos/"+m.getSigla()+".png");
			datos[i+dimFilasAC][0] = new ImageIcon(getClass().getClassLoader().getResource("vista/iconos/"+m.getSigla()+".png"));
			datos[i+dimFilasAC][1] = m.getNombre();
			datos[i+dimFilasAC][2] = aF.getCantidad();
		}
		modelo.setDataVector(datos, this.nombresColumnas);
	}
	
}
