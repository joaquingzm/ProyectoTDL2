package componentes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class ListaPlanetas extends JFrame {
	JList lista;
	JButton imprimir;
	public void init() {
		String[] items = { "Mercurio", "Tierra", "Saturno", "Jupiter" };
		lista = new JList(items);
		lista.setVisibleRowCount(4);
		imprimir = new JButton("Imprimir Selección");
		imprimir.addActionListener(new MyActionListener());
		this.setLayout(new FlowLayout());
		this.add(new JScrollPane(lista));
		this.add(imprimir);
	}
	private class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(lista.getSelectedValue());
		}

	}
	
}