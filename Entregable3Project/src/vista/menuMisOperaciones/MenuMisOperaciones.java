package vista.menuMisOperaciones;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controlador.menuMisOperacionesListeners.VolverListener;
import modelos.Transaccion;

@SuppressWarnings("serial")
public class MenuMisOperaciones extends JPanel{
	
	private DefaultListModel<String> misOperacionesListModel;
	private JList<String> misOperacionesList;
	private JScrollPane misOperacionesScrollPane;
	private JButton volver;
	
	public MenuMisOperaciones() {
		
		misOperacionesListModel = new DefaultListModel<String>();
		misOperacionesList = new JList<String>(misOperacionesListModel);
		misOperacionesScrollPane = new JScrollPane(misOperacionesList);
		volver = new JButton("Volver");
		
		volver.addActionListener(new VolverListener());
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(misOperacionesScrollPane,gbc);
		
		gbc.gridy = 1;
		this.add(volver,gbc);
		
	}
	
	public void actualizarOperaciones(List<Transaccion> transaccion) {
		
		misOperacionesListModel.removeAllElements();
		for(Transaccion t : transaccion) {
			misOperacionesListModel.addElement(t.toString());
		}
	}
}