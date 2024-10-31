package funcionalidades_sistema;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import comparadores.ComparadorCriptomonedaPrecioEnDolar;
import comparadores.ComparadorCriptomonedaSigla;
import comparadores.ComparadorMonedaFiduciariaPrecioEnDolar;
import comparadores.ComparadorMonedaFiduciariaSigla;
import comparadores.ComparadorStockCantidad;
import comparadores.ComparadorStockSigla;
import daos.FactoryDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.Moneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import singletones.MyScanner;

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
		String tipo = confirmacionDeTipo();
		String nombre, sigla;
		double precioEnDolar;
		
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
	
	private static String confirmacionDeTipo() {
		
		Scanner scan = MyScanner.getScan();
		boolean esTipo = false;
		String tipo = null;
		
		while (!esTipo) {
			System.out.println("Ingrese el tipo, (FIAT | CRIPTO):\n");
			tipo = scan.nextLine();
			tipo = tipo.toUpperCase();
			
			switch (tipo) {
				case "FIAT":
					esTipo = true;
					break;
				case "CRIPTO":
					esTipo = true;
					break;
				default:
					System.out.println("Hubo un error en la eleccion del tipo.\n\nVuelva a intentarlo.\n\n");
			}
		}
		
		System.out.println("\n\n");
		return tipo;
		
	}
	
	private static void crearMonedaFiat(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String paisEmisor, decision;
		
		System.out.println("Ingrese el pais emisor de la moneda fiduciaria:\n");
		paisEmisor = scan.nextLine();
		
		MonedaFiduciaria mf = new MonedaFiduciaria(nombre,sigla,precioEnDolar,paisEmisor);
		
		if (confirmacionMonedas(mf)) {
			FactoryDAO.getMonedaFiduciariaDAO().insertarMonedaFiduciaria(mf);
			System.out.println("La moneda Fiduciaria se ha creado exitosamente.");
		}
		
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
		
		if (confirmacionMonedas(cm)) {
			FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(cm);
			FactoryDAO.getStockDAO().insertarStock(new Stock(stockDisponible, cm));
			
			System.out.println("La criptomoneda se ha creado exitosamente.");
		}
		
		
	}
	
	private static boolean confirmacionMonedas(Moneda m) {
		
		String decision;
		Scanner scan = MyScanner.getScan();
		boolean confirmacion = false, controlador = false;
		
		System.out.println("Los datos de la moneda son:\n" + m.toString() + "\n\n");
		
		while (!controlador) {
			System.out.println("¿Desea Continuar? (SI/NO): ");
			decision = scan.nextLine();
			decision = decision.toUpperCase();
			
			switch (decision) {
				case "SI":
					confirmacion = true;
					controlador = true;
					break;
				case "NO":
					controlador = true;
					break;
				default:
					System.out.println("Hubo un error al ingresar la respuesta.\n\n");
			}
		}
		
		return confirmacion;
		
	}
	
	//Para hacer más fácil esto, considerar meter el toString en monedas
	private static String listarMonedas() throws SQLException{
		String opciones = "Listar tomando como criterio de orden la cantidad o sigla?(PrecioEnDolar/Sigla/Salir)\n";
		Scanner in = MyScanner.getScan();
		Comparator<MonedaFiduciaria> cFiat = null;
		Comparator<Criptomoneda> cCripto = null;
		String str = null;
		boolean terminar = false;
		
		String entrada = in.toString();
		entrada.toLowerCase();
		while(!terminar) {
			switch (entrada) {
				case "precioendolar":
					cFiat = new ComparadorMonedaFiduciariaPrecioEnDolar();
					cCripto = new ComparadorCriptomonedaPrecioEnDolar();
					terminar = true;
					break;
				case "sigla":
					cFiat = new ComparadorMonedaFiduciariaSigla();
					cCripto = new ComparadorCriptomonedaSigla();
					terminar = true;
					break;
				case "salir":
					return entrada;
				default:
					System.out.println("Por favor, elegir una opción adecuada");
			}
		}
		
		LinkedList<MonedaFiduciaria> listaMonedasFiat = (LinkedList<MonedaFiduciaria>) FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias(cFiat);
		LinkedList<Criptomoneda> listaCriptomonedas = (LinkedList<Criptomoneda>) FactoryDAO.getCriptomonedaDAO().listarCriptomonedas(cCripto);
		//Quedó medio chancho, ver que hago
		str+="Monedas Fiduciarias: "
		for(MonedaFiduciaria e : listaMonedasFiat) {
			str+=e.toString();
		}
		
		return str;
	}
	
	private static void generarStock() throws SQLException{
		
	}
	
	private static String listarStock() throws SQLException{
		String opciones = "Listar tomando como criterio de orden la cantidad o sigla?(Cantidad/Sigla/Salir)\n";
		Scanner in = MyScanner.getScan();
		Comparator<Stock> c = null;
		String str = null;
		boolean terminar = false;
		
		String entrada = in.toString();
		entrada.toLowerCase();
		while(!terminar) {
			switch (entrada) {
				case "cantidad":
					c = new ComparadorStockCantidad();
					terminar = true;
					break;
				case "sigla":
					c = new ComparadorStockSigla();
					terminar = true;
					break;
				case "salir":
					return entrada;
				default:
					System.out.println("Por favor, elegir una opción adecuada");
			}
		}
		
		LinkedList<Stock> listaStocks = (LinkedList<Stock>) FactoryDAO.getStockDAO().listarStock(c);
		
		for(Stock e : listaStocks) {
			str+=e.toString();
		}
		
		return str;
	}
	
	private static void generarActivos() throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String tipo = confirmacionDeTipo();
		String sigla, direccion = null;
		double cantidad;
		Moneda m = null;
		
		System.out.println("Ingrese la sigla del activo a generar: ");
		sigla = scan.nextLine();
		
		if (tipo.equals("FIAT")) m = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla);
		else m = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
		
		if (m == null) {
			System.out.println("ERROR, no se ha encontrado la moneda en el stock de monedas.");
			return;
		}
		
		do {
			
			System.out.println("Ingrese la cantidad del activo correspondiente: ");
			cantidad = scan.nextDouble();
			scan.nextLine();
			if (cantidad < 0) System.out.println("Hubo un error al ingresar el stock disponible de la moneda.\n\nVuelva a intentarlo.\n\n");
			
		} while (cantidad < 0);
		
		if (tipo.equals("CRIPTO")) {
			System.out.println("Ingrese la direccion de la criptomoneda: ");
			direccion = scan.nextLine();
		}
		
		if (confirmacionMonedas(m)) {
			if (tipo.equals("FIAT")) FactoryDAO.getActivoMonedaFiduciariaDAO().insertarActivoMonedaFiduciaria(new ActivoMonedaFiduciaria(cantidad, (MonedaFiduciaria) m));	
			else FactoryDAO.getActivoCriptoDAO().insertarActivoCripto(new ActivoCripto(cantidad, direccion, (Criptomoneda) m));
		}
	}
	
	private static String listarActivos() throws SQLException{
	}
	
	private static void realizarCompraDeCriptomoneda() throws SQLException{
	}
	
	private static void realizarSwap() throws SQLException{
	}
}
