package sistema;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import comparadores.*;
import daos.ActivoCriptoDAO;
import daos.FactoryDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import modelos.Transaccion;
import singletones.MyScanner;

public class Menu {
	
	public static void comenzar() throws SQLException{
		
		boolean finalizar = false;
		String eleccion = null;
		Scanner scan = MyScanner.getScan();
		String menu = "\n-- Elija una opción -- \n"
				+ "1) Crear Moneda\n"
				+ "2) Listar monedas\n"
				+ "3) Generar Stock\n"
				+ "4) Listar Stock\n"
				+ "5) Generar mis activos\n"
				+ "6) Listar mis activos\n"
				+ "7) Realizar compra de criptomoneda\n"
				+ "8) Realizar swap\n"
				+ "9) Finalizar";

		
		while(!finalizar) {
			System.out.println(menu);
			eleccion = scan.nextLine();
			eleccion.toLowerCase();
			switch (eleccion) {
			
				case "1":
			    case "crear moneda":
			    	crearMoneda();
			    	break;
			        
			    case "2":
			    case "listar monedas":
			    	System.out.println(listarMonedas());
			    	break;
			        
			    case "3":
			    case "generar stock":
			        generarStock();
			        break;
			        
			    case "4":
			    case "listar stock":
			        System.out.println(listarStock());
			        break;
			      
			    case "5":
			    case "generar mis activos":
			        generarActivos();
			        break;
			        
			    case "6":
			    case "listar mis activos":
			        System.out.println(listarActivos());
			        break;
			        
			    case "7":
			    case "realizar compra de criptomoneda":
			        realizarCompraDeCriptomoneda();
			    	break;
			        
			    case "8":
			    case "realizar swap":
			        realizarSwap();
			        break;
			        
			    case "9":
			    case "finalizar":
			    	finalizar = true;
			    	break;
			    	
			    default:
			        System.out.println("Opción no válida. Por favor, elige una opción del menú.");
			        break;
			}
		}
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
		String nombre, sigla, eleccion;
		double precioEnDolar;
		boolean finalizar = false;
		
		do{
			System.out.println("\nIngrese la sigla de la moneda:");
			sigla = scan.nextLine();
			if(tipo.equals("FIAT")) {
				if(FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla)==null) {
					finalizar = true;
				}
				else {
					System.out.println("La moneda fiduciaria de sigla "+sigla+" ya existe, (Reintentar/Salir).");
					do {
						eleccion = scan.nextLine();
						eleccion.toLowerCase();
						if(!eleccion.equals("salir") && !eleccion.equals("reintentar")) {
							System.out.println("Por favor elegir una opción válida, (Reintentar/Salir).");
						}
						if(eleccion.equals("salir")) {
							return;
						}
					}while (!eleccion.equals("salir") && !eleccion.equals("reintentar"));
				}
			}
			else {
				if(FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla)==null) {
					finalizar = true;
				}
				else {
					System.out.println("La criptomoneda de sigla "+sigla+" ya existe, (Reintentar/Salir).");
					do {
						eleccion = scan.nextLine();
						eleccion.toLowerCase();
						if(!eleccion.equals("salir") && !eleccion.equals("reintentar")) {
							System.out.println("Por favor elegir una opción válida, (Reintentar/Salir).");
						}
						if(eleccion.equals("salir")) {
							return;
						}
					}while (!eleccion.equals("salir") && !eleccion.equals("reintentar"));
				}
			}
			
		}while(!finalizar);
	
		System.out.println("\nIngrese el nombre de la moneda:");
		nombre = scan.nextLine();
		System.out.println("\nIngrese el precio en dolares de la moneda:");
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
			System.out.println("\nIngrese el tipo, (FIAT | CRIPTO):");
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
					System.out.println("Hubo un error en la eleccion del tipo.\nVuelva a intentarlo.");
			}
		}
		
		return tipo;
		
	}
	
	private static void crearMonedaFiat(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String paisEmisor;
		
		System.out.println("\nIngrese el pais emisor de la moneda fiduciaria:");
		paisEmisor = scan.nextLine();
		System.out.println();
		MonedaFiduciaria mf = new MonedaFiduciaria(nombre,sigla,precioEnDolar,paisEmisor);
		
		if (confirmacionDelUsuario(mf)) {
			FactoryDAO.getMonedaFiduciariaDAO().insertarMonedaFiduciaria(mf);
			System.out.println("\nLa moneda Fiduciaria se ha creado exitosamente.");
		}
		else {
			System.out.println("\nLa moneda Fiduciaria no se ha creado.\n");
		}
	}
	
	private static void crearMonedaCripto(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		double volatilidad, stockDisponible;
		
		do {
			
			System.out.println("\nIngrese la volatilidad de la moneda, (1..100):");
			volatilidad = scan.nextDouble();
			scan.nextLine();
			System.out.println();
			if ((volatilidad < 0) || (volatilidad > 100)) System.out.println("\nHubo un error al ingresar la volatilidad de la moneda.\n\nVuelva a intentarlo.\n\n");
			
		} while ((volatilidad < 0) || (volatilidad > 100));
		
		do {
			
			System.out.println("Ingrese el stock disponible de la moneda:");
			stockDisponible = scan.nextDouble();
			scan.nextLine();
			if (stockDisponible < 0) System.out.println("\nHubo un error al ingresar el stock disponible de la moneda.\n\nVuelva a intentarlo.\n\n");
			
		} while (stockDisponible < 0);
		
		Criptomoneda cm = new Criptomoneda(nombre,sigla,precioEnDolar,volatilidad);
		
		if (confirmacionDelUsuario(cm)) {
			FactoryDAO.getCriptomonedaDAO().insertarCriptomoneda(cm);
			FactoryDAO.getStockDAO().insertarStock(new Stock(stockDisponible, cm));
			
			System.out.println("La criptomoneda se ha creado exitosamente.\n");
		}
		else {
			System.out.println("La criptomoneda no se ha creado.\n");
		}
		
	}
	
	private static boolean confirmacionDelUsuario(Object o) {
		
		String decision;
		Scanner scan = MyScanner.getScan();
		boolean confirmacion = false, controlador = false;
		
		System.out.println("Los datos son:\n" + o.toString() + "\n");
		
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
	
	private static String listarMonedas() throws SQLException{
		String opciones = "\nListar tomando como criterio de orden la cantidad o sigla?(PrecioEnDolar/Sigla/Salir)\n";
		Scanner scan = MyScanner.getScan();
		Comparator<MonedaFiduciaria> cFiat = null;
		Comparator<Criptomoneda> cCripto = null;
		String str = null;
		boolean terminar = false;
		String entrada = null;
		
		
		while(!terminar) {
			System.out.println(opciones);
			entrada = scan.nextLine();
			entrada.toLowerCase();
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
					return "Saliendo...\n";
				default:
					System.out.println("Por favor, elegir una opción adecuada\n");
			}
		}
		
		LinkedList<MonedaFiduciaria> listaMonedasFiat = (LinkedList<MonedaFiduciaria>) FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias(cFiat);
		LinkedList<Criptomoneda> listaCriptomonedas = (LinkedList<Criptomoneda>) FactoryDAO.getCriptomonedaDAO().listarCriptomonedas(cCripto);
		str="Monedas Fiduciarias: \n";
		for(MonedaFiduciaria e : listaMonedasFiat) {
			str+=e.toString()+"\n";
		}
		str+="\n\nCriptomonedas; \n";
		for(Criptomoneda e : listaCriptomonedas) {
			str+=e.toString()+"\n";
		}
		return str;
	}
	
	private static void generarStock() throws SQLException{
		String eleccion;
		Scanner scan = MyScanner.getScan();
		String sigla = null;
		Random random = new Random();
		double cantidadStock = random.nextDouble();
		Stock stock = null;
		boolean terminar = false;
		
		while(!terminar) {
			System.out.println("\nIngrese la sigla del stock a generar de manera aleatoria: ");
			sigla = scan.nextLine();
			stock = FactoryDAO.getStockDAO().buscarStock(sigla);
			if(stock != null) {
				FactoryDAO.getStockDAO().cambiarCantidadStock(sigla, cantidadStock);
				System.out.println("\nCantidad de stock generada: "+cantidadStock);
				terminar = true;
			}	
			else {
				System.out.println("\nPor favor, elegir una sigla adecuada. Salir? (Si/No)");
				eleccion = scan.nextLine();
				eleccion.toLowerCase();
				if (eleccion.equals("si")) {
					return;
				}
			}
		}
	}
	
	private static String listarStock() throws SQLException{
		String opciones = "\nListar tomando como criterio de orden la cantidad o sigla?(Cantidad/Sigla/Salir)\n";
		Scanner scan = MyScanner.getScan();
		Comparator<Stock> c = null;
		String str = "Stocks: \n";
		boolean terminar = false;
		String entrada = null;
		
		while(!terminar) {
			System.out.println(opciones);
			entrada = scan.nextLine();
			entrada.toLowerCase();
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
					return "Saliendo...\n";
				default:
					System.out.println("\nPor favor, elegir una opción adecuada");
			}
		}
		
		LinkedList<Stock> listaStocks = (LinkedList<Stock>) FactoryDAO.getStockDAO().listarStock(c);
		
		for(Stock e : listaStocks) {
			str+=e.toString();
		}
		str+="\n";
		return str;
	}
	
	private static void generarActivos() throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String tipo = confirmacionDeTipo();
		String sigla, direccion, eleccion = null;
		double cantidad;
		Criptomoneda cm = null;
		MonedaFiduciaria mf = null;
		boolean seCreo = false;
		boolean finalizar = false;
		
		do{
			System.out.println("\nIngrese la sigla de la moneda:");
			sigla = scan.nextLine();
			if(tipo.equals("FIAT")) {
				if(FactoryDAO.getActivoMonedaFiduciariaDAO().buscarActivoMonedaFiduciaria(sigla)==null) {
					finalizar = true;
				}
				else {
					System.out.println("El activo de moneda fiduciaria de sigla "+sigla+" ya existe, (Reintentar/Salir).");
					do {
						eleccion = scan.nextLine();
						eleccion.toLowerCase();
						if(!eleccion.equals("salir") && !eleccion.equals("reintentar")) {
							System.out.println("Por favor elegir una opción válida, (Reintentar/Salir).");
						}
						if(eleccion.equals("salir")) {
							return;
						}
					}while (!eleccion.equals("salir") && !eleccion.equals("reintentar"));
				}
			}
			else {
				if(FactoryDAO.getActivoCriptoDAO().buscarActivoCripto(sigla)==null) {
					finalizar = true;
				}
				else {
					System.out.println("El activo de criptomoneda de sigla "+sigla+" ya existe, (Reintentar/Salir).");
					do {
						eleccion = scan.nextLine();
						eleccion.toLowerCase();
						if(!eleccion.equals("salir") && !eleccion.equals("reintentar")) {
							System.out.println("Por favor elegir una opción válida, (Reintentar/Salir).");
						}
						if(eleccion.equals("salir")) {
							return;
						}
					}while (!eleccion.equals("salir") && !eleccion.equals("reintentar"));
				}
			}
			
		}while(!finalizar);
		
		if (tipo.equals("FIAT")) mf = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla);
		else cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
		
		if ((mf == null) && (cm == null)) {
			System.out.println("\nERROR, no se ha encontrado la moneda en el stock de monedas.");
			return;
		}
		
		do {
			
			System.out.println("\nIngrese la cantidad del activo correspondiente: ");
			cantidad = scan.nextDouble();
			scan.nextLine();
			if (cantidad < 0) System.out.println("\nHubo un error al ingresar el stock disponible de la moneda.\n\nVuelva a intentarlo.\n\n");
			
		} while (cantidad < 0);
		
		if (tipo.equals("CRIPTO")) {
			
			System.out.println("\nIngrese la direccion de la criptomoneda: ");
			direccion = scan.nextLine();
			
			if (confirmacionDelUsuario(cm)) {
				FactoryDAO.getActivoCriptoDAO().insertarActivoCripto(new ActivoCripto(cantidad, direccion, cm));
				seCreo = true;
			}
			
		} else {
			
			if (confirmacionDelUsuario(mf)) {
				FactoryDAO.getActivoMonedaFiduciariaDAO().insertarActivoMonedaFiduciaria(new ActivoMonedaFiduciaria(cantidad, mf));
				seCreo = true;
			}
			
		}
		
		if (seCreo) System.out.println("\nEl activo se creó exitosamente.");
		else {
			System.out.println("\nEl activo no se ha creado.");
		}
	}
	
	private static String listarActivos() throws SQLException{
		String opciones = "\nListar tomando como criterio de orden la cantidad o sigla?(Cantidad/Sigla/Salir)\n";
		Scanner scan = MyScanner.getScan();
		Comparator<ActivoMonedaFiduciaria> cMF = null;
		Comparator<ActivoCripto> cCripto = null;
		String str = null;
		boolean terminar = false;
		String entrada = null;
		
		while(!terminar) {
			System.out.println(opciones);
			entrada = scan.nextLine();
			entrada.toLowerCase();
			switch (entrada) {
				case "cantidad":
					cCripto = new ComparadorActivoCriptoCantidad();
					cMF = new ComparadorActivoMonedaFiduciariaCantidad();
					terminar = true;
					break;
				case "sigla":
					cCripto = new ComparadorActivoCriptoSigla();
					cMF = new ComparadorActivoMonedaFiduciariaSigla();
					terminar = true;
					break;
				case "salir":
					return "Saliendo...\n";
				default:
					System.out.println("Por favor, elegir una opción adecuada");
			}
		}
		
		List<ActivoMonedaFiduciaria> listaActivosMonedaFiat = FactoryDAO.getActivoMonedaFiduciariaDAO().listarActivosFiduciarios(cMF);
		LinkedList<ActivoCripto> listaActivosCripto = (LinkedList<ActivoCripto>) FactoryDAO.getActivoCriptoDAO().listarActivosCripto(cCripto);
		
		str="Activos Monedas Fiduciarias: \n";
		for(ActivoMonedaFiduciaria e : listaActivosMonedaFiat) {
			str+=e.toString()+"\n";
		}
		str+="\n\nActivos Criptomonedas; \n";
		for(ActivoCripto e : listaActivosCripto) {
			str+=e.toString()+"\n";
		}
		return str;
	}
	
	private static void realizarCompraDeCriptomoneda() throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String siglaDeCriptoAComprar, siglaDeFiatAUtilizar;
		double montoFiduciario, montoCompradoDeCripto;
		
		System.out.println("\nIngrese la sigla de la criptomoneda a comprar: ");
		siglaDeCriptoAComprar = scan.nextLine();
		System.out.println("\nIngrese la sigla de la moneda fiduciaria a utilizar en la compra: ");
		siglaDeFiatAUtilizar = scan.nextLine();
		System.out.println("\nIngrese la cantidad de moneda fiduciaria a utilizar: ");
		montoFiduciario = scan.nextDouble();
		
		ActivoMonedaFiduciaria amf = FactoryDAO.getActivoMonedaFiduciariaDAO().buscarActivoMonedaFiduciaria(siglaDeFiatAUtilizar);
		
		if ((amf == null) ||  (amf.getCantidad() < montoFiduciario)) {
			System.out.println("\nHa habido un error en la compra, porque no le alcanza el dinero o porque la moneda fiduciaria a utilizar no se encuentra disponible.");
			return;
		}
		
		Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(siglaDeCriptoAComprar);
		
		if (cm == null) {
			System.out.println("\nHa habido un error en la compra, porque la Criptomoneda no se encuentra registrada en el sistema.");
			return;
		}
		
		montoCompradoDeCripto = (montoFiduciario*amf.getMonedaFIAT().getPrecioEnDolar()) / cm.getPrecioEnDolar();
		
		String resumen = "\nSe han comprado " + montoCompradoDeCripto + " de " + siglaDeCriptoAComprar 
				+ " por " + montoFiduciario + " de " + siglaDeFiatAUtilizar + ".";
		
		Transaccion t = new Transaccion(resumen, LocalDate.now());
		
		if (confirmacionDelUsuario(t)) {
			
			ActivoCripto ac = FactoryDAO.getActivoCriptoDAO().buscarActivoCripto(siglaDeCriptoAComprar);
			
			if (ac == null) {
				
				CrearActivoCriptoPorCompra(montoCompradoDeCripto, cm);
				
			} else {
				
				FactoryDAO.getActivoCriptoDAO().sumarCantidadActivoCripto(cm.getSigla(), montoCompradoDeCripto);
				
			}
			
			FactoryDAO.getStockDAO().sumarCantidadStock(siglaDeCriptoAComprar, -montoCompradoDeCripto);
			FactoryDAO.getActivoMonedaFiduciariaDAO().sumarCantidadActivoFiduciaria(siglaDeFiatAUtilizar, -montoFiduciario);
			
			FactoryDAO.getTransaccionDAO().insertarTransaccion(t);
			System.out.println("\nSe ha completado la compra.");
			
		} else {
			
			System.out.println("\nSe ha cancelado la compra.");
			
		}
		
	}
	
	private static void CrearActivoCriptoPorCompra(double montoComprado, Criptomoneda cm) throws SQLException{
		
		String direccion = "RANDOM";
		ActivoCripto ac = new ActivoCripto(montoComprado, direccion, cm);
		FactoryDAO.getActivoCriptoDAO().insertarActivoCripto(ac);
	
	}

	private static void realizarSwap() throws SQLException{
	
		Scanner scan = MyScanner.getScan();		
		String siglaActivoCriptoAIntercambiar, siglaActivoCriptoIntercambiado;
		double cantidadAIntercambiar, cantidadIntercambiada;
		ActivoCripto activoCriptoAIntercambiar = null, activoCriptoIntercambiado = null;
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
		
		System.out.println("\nIngrese la sigla de la criptomoneda a intercambiar:");
		siglaActivoCriptoAIntercambiar = scan.nextLine();
		
		activoCriptoAIntercambiar = acDAO.buscarActivoCripto(siglaActivoCriptoAIntercambiar);
		
		if (activoCriptoAIntercambiar == null) {
			System.out.println("\nHa habido un error porque no existe entre sus activos el activo cripto a intercambiar.");
			return;
		}
		
		System.out.println("\nIngrese la sigla de la criptomoneda a la que desea intercambiar:");
		siglaActivoCriptoIntercambiado = scan.nextLine();
		
		activoCriptoIntercambiado = acDAO.buscarActivoCripto(siglaActivoCriptoIntercambiado);
		
		if (activoCriptoIntercambiado == null) {
			System.out.println("\nHa habido un error porque no existe entre sus activos el activo cripto al cual desea intercambiar.");
			return;
		}
		
		do {
			
			System.out.println("\nIngrese la cantidad de " + siglaActivoCriptoAIntercambiar + " que quiere intercambiar por " + siglaActivoCriptoIntercambiado + ": ");
			cantidadAIntercambiar = scan.nextDouble();
			if (cantidadAIntercambiar < 0) System.out.println("\nHa habido un error en el ingreso de la cantidad a intercambiar.");
			
		} while (cantidadAIntercambiar < 0);
		
		if (activoCriptoAIntercambiar.getCantidad() < cantidadAIntercambiar) {
			
			System.out.println("\nHa habido un error porque no cuenta con las suficientes criptomonedas para continuar con el intercambio.");
			return;
			
		}
		
		cantidadIntercambiada = (cantidadAIntercambiar * activoCriptoAIntercambiar.getCriptomoneda().getPrecioEnDolar()) / activoCriptoIntercambiado.getCriptomoneda().getPrecioEnDolar();
		
		String resumen = "Se han intercambiado " + cantidadAIntercambiar + " de " + siglaActivoCriptoAIntercambiar 
				+ " por " + cantidadIntercambiada + " de " + siglaActivoCriptoIntercambiado + ".";
		
		Transaccion t = new Transaccion(resumen, LocalDate.now());
		
		if (confirmacionDelUsuario(t)) {
			
			acDAO.sumarCantidadActivoCripto(siglaActivoCriptoAIntercambiar, -cantidadAIntercambiar);
			acDAO.sumarCantidadActivoCripto(siglaActivoCriptoIntercambiado, cantidadIntercambiada);
			FactoryDAO.getTransaccionDAO().insertarTransaccion(t);
			System.out.println("\nSe ha completado el intercambio.");
			
		} else {
			
			System.out.println("\nSe ha cancelado el intercambio.");
			
		}
		
	}
}
