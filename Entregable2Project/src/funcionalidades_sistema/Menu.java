package funcionalidades_sistema;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import comparadores.*;
import daos.FactoryDAO;
import modelos.Criptomoneda;
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
		String tipo;
		do {
			System.out.println("Ingrese el tipo de la moneda a crear, (FIAT | CRIPTO):\n ");
			tipo = scan.nextLine();
			tipo = tipo.toUpperCase();
			if ((tipo != "CRIPTO") || (tipo != "FIAT")) {
				System.out.println("Hubo un error en la eleccion del tipo.");
			}
		} while ((tipo != "CRIPTO") || (tipo != "FIAT"));
		
		
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
	}
	
	private static String listarActivos() throws SQLException{
	}
	
	private static void realizarCompraDeCriptomoneda() throws SQLException{
	}
	
	private static void realizarSwap() throws SQLException{
	}
}
