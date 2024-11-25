package controlador;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import componentes.ListaPlanetas;
import componentes.ListaPlanetas2;
import componentes.MenuInicio;
import componentes.MenuRegistracion;
import componentes.PruebaSwing;
import singletones.MyConnection;
import singletones.MyScanner;

public class Controlador {
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame();
		JPanel panelPrincipal = new JPanel();
		MenuRegistracion panelMenuRegistracion = new MenuRegistracion();
		MenuInicio panelMenuInicio = new MenuInicio();
		CardLayout cardLayout = new CardLayout();
		
		panelMenuRegistracion.iniciar();
		panelMenuInicio.iniciar();
		panelPrincipal.setLayout(cardLayout);
		panelPrincipal.add(panelMenuRegistracion,"MenuRegistracion");
		panelPrincipal.add(panelMenuInicio,"MenuInicio");
		
		frame.setTitle("Billetera Virtual");
		frame.setSize(new Dimension(500,500));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panelPrincipal);
		frame.setVisible(true);
		
		cardLayout.show(panelPrincipal, "MenuRegistracion");
		
		
	}
}
