package funcionalidades_sistema;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import comparadores.ComparadorStockSigla;
import daos.FactoryDAO;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import singletones.MyScanner;
import singletones.MyStatement;

public class Menu {
	
	public static void comenzar() throws SQLException{
		boolean salir = false;
		String menu = "-- Elija una opción -- \n"
				+ "1) Crear Moneda\n"
				+ "2) Listar monedas\n"
				+ "3) Generar Stock\n"
				+ "4) Listar Stock\n"
				+ "5) Generar mis activos\n"
				+ "6) Listar mis activos\n"
				+ "7) Realizar compra de criptomoneda"
				+ "8) Realizar swap\n";
		
	}
	
	/* Considerar separar estos métodos en paquetes, por ejemplo
	 * podría ser más ordenada tener los métodos que suponen
	 * una operación como comprar o swappear en un paquete distinto
	 * al que se encargue de simplemente imprimir (o capaz estos métodos
	 * de imprimir dejarlos acá)
	 */
	
	private static void crearMoneda() throws SQLException{
		Scanner scan = MyScanner.getScan();
		String tipo, nombre, sigla;
		double precioEnDolar;
		
		do {
			System.out.println("Ingrese el tipo de la moneda a crear, (FIAT | CRIPTO):\n ");
			tipo = scan.nextLine();
			tipo = tipo.toUpperCase();
			if ((tipo != "CRIPTO") || (tipo != "FIAT")) {
				System.out.println("Hubo un error en la eleccion del tipo.\n\nVuelva a intentarlo.\n\n");
			}
		} while ((tipo != "CRIPTO") || (tipo != "FIAT"));
		
		System.out.println("Ingrese el nombre de la moneda:\n");
		nombre = scan.nextLine();
		System.out.println("Ingrese la sigla de la moneda:\n");
		sigla = scan.nextLine();
		System.out.println("Ingrese el precio en dolares de la moneda:\n");
		precioEnDolar = scan.nextDouble();
		scan.nextLine();
		
		if (tipo.equals("FIAT")) crearMonedaFiat(nombre, sigla, precioEnDolar);
		else crearMonedaCripto(nombre, sigla, precioEnDolar);
		
	}
	
	private static void crearMonedaFiat(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String paisEmisor;
		
		System.out.println("Ingrese el pais emisor de la moneda:\n");
		paisEmisor = scan.nextLine();
		
		FactoryDAO.getMonedaFiduciariaDAO().insertarMonedaFiduciaria(new MonedaFiduciaria(nombre,sigla,precioEnDolar,paisEmisor));
		
	}
	
	private static void crearMonedaCripto(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		double volatilidad, stockDisponible;
		
		do {
			
			System.out.println("Ingrese la volatilidad de la moneda, (1..100):\n");
			volatilidad = scan.nextDouble();
			scan.nextLine();
			if ((volatilidad < 0) && (volatilidad > 100)) System.out.println("Hubo un error al ingresar la volatilidad de la moneda.\n\nVuelva a intentarlo.\n\n");
			
		} while ((volatilidad < 0) && (volatilidad > 100));
		
		do {
			System.out.println("Ingrese el stock disponible de la moneda:\n");
			stockDisponible = scan.nextDouble();
			scan.nextLine();
			if (stockDisponible < 0) System.out.println("Hubo un error al ingresar el stock disponible de la moneda.\n\nVuelva a intentarlo.\n\n");
		} while (stockDisponible < 0);
		
		Criptomoneda cm = new Criptomoneda(nombre,sigla,precioEnDolar,volatilidad);
		
		FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(cm);
		FactoryDAO.getStockDAO().insertarStock(new Stock(stockDisponible, cm));
		
	}
	
	//Para hacer más fácil esto, considerar meter el toString en monedas
	private static String listarMonedas() throws SQLException{
	}
	
	private static void generarStock() throws SQLException{
		
	}
	
	private static String listarStock() throws SQLException{
		String str = "Listar tomando como criterio de orden la cantidad o sigla?(Cantidad/Sigla)\n";
		Scanner in = MyScanner.getScan();
		Comparator<Stock> c = new ComparadorStockSigla();
		LinkedList<Stock> listaStocks = (LinkedList<Stock>) FactoryDAO.getStockDAO().listarStock(c);
		
	}
	
	private static void generarActivos() throws SQLException{
	}
	
	private static String listarActivos() throws SQLException{
	}
	
	private static void realizarCompraDeCriptomoneda() throws SQLException{
	}
	
	private static void realizarSwap() throws SQLException{
	}
}
