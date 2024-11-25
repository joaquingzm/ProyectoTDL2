package componentes;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListaPlanetas2 extends JFrame {
	private JList lista = new JList();
	private DefaultListModel modelo = new DefaultListModel();
	private JButton agregar= new JButton("Agregaar");;
	private JButton borrar = new JButton("Borrar");
	private JTextField texto = new JTextField(15);
	public void init() {
		String[] items = { "Mercurio","Tierra","Saturno","Jupiter"};
		for (String item : items) {
			modelo.addElement(item);
		}
		lista.setModel(modelo);
		lista.setVisibleRowCount(4);
		agregar.addActionListener(new AgregarItemListener());
		borrar.addActionListener(new BorrarItemListener());
		this.setLayout(new FlowLayout());
		this.add(new JScrollPane(lista));
		this.add(texto);
		this.add(agregar);
		this.add(borrar);
	}
	private class AgregarItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String nuevoItem = texto.getText();
			modelo.addElement(nuevoItem);
			texto.setText("");
		}
	}
	private class BorrarItemListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			int indice = lista.getSelectedIndex();
			modelo.remove(indice);
		}
	}
}
