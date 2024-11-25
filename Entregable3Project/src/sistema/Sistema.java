package sistema;

import java.awt.Button;
import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import componentes.ListaPlanetas;
import componentes.ListaPlanetas2;
import componentes.PruebaSwing;
import singletones.MyConnection;
import singletones.MyScanner;

public class Sistema {
	public static void main(String[] args) {
		/*
		PruebaSwing frame = new PruebaSwing();
		frame.setLayout(new CardLayout());
		JPanel card1 = new JPanel();
		card1.setName("Cosa");
		card1.add(new JButton("boton"));
		frame.add(card1,"botones");
		frame.setVisible(true);
		*/
		
		/*
		ListaPlanetas lp = new ListaPlanetas();
		lp.init();
		lp.setSize(300, 300);
		
		lp.setVisible(true);
		*/
		
		ListaPlanetas2 planetas = new ListaPlanetas2();
		planetas.init();
		planetas.pack();
		planetas.setVisible(true);
		
		/*
		try {
			MetodosDelSistema.creaciónDeTablasEnBD();
			Menu.comenzar();
			MyConnection.cerrarCon();
			MyScanner.cerrarScan();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
