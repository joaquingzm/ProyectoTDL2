package controlador;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import comparadores.ComparadorActivoCriptoCantidad;
import comparadores.ComparadorActivoCriptoSigla;
import comparadores.ComparadorActivoMonedaFiduciariaCantidad;
import comparadores.ComparadorActivoMonedaFiduciariaSigla;
import comparadores.ComparadorCriptomonedaPrecioEnDolar;
import comparadores.ComparadorCriptomonedaSigla;
import comparadores.ComparadorMonedaFiduciariaPrecioEnDolar;
import comparadores.ComparadorMonedaFiduciariaSigla;
import comparadores.ComparadorStockCantidad;
import comparadores.ComparadorStockSigla;
import daos.ActivoCriptoDAO;
import daos.ActivoMonedaFiduciariaDAO;
import daos.FactoryDAO;
import daos.StockDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import modelos.Stock;
import modelos.Transaccion;
import singletones.MyScanner;

public class Menu {
	
	private static String opcionIncorrecta = "Opción no válida. Por favor, elige nuevamente.";
	private static String fiat = "FIAT";
	private static String cripto = "CRIPTO";
	private static String monedaExiste = "Hubo un error porque la moneda a crear ya existe.";
	private static String ingresarSiglaMoneda = "Ingrese la sigla de la moneda";
	private static String ingresarNombreMoneda = "Ingrese el nombre de la moneda";
	private static String ingresarPrecioMoneda = "Ingrese el precio en dolares de la moneda";
	private static String ingresarTipoMoneda = "Ingrese el tipo, (FIAT | CRIPTO)";
	private static String ingresarPaisMoneda = "Ingrese el pais emisor de la moneda fiduciaria";
	private static String operacionExitosa = "La operación ha tenido exito.";
	private static String operacionCancelada = "La operación ha fracasado.";


	
	protected static void comenzar() throws SQLException{
		
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
			eleccion = eleccion.toUpperCase();
			switch (eleccion) {
			
				case "1":
			    case "CREAR MONEDA":
			    	crearMoneda();
			    	break;
			        
			    case "2":
			    case "LISTAR MONEDAS":
			    	System.out.println(listarMonedas());
			    	break;
			        
			    case "3":
			    case "GENERAR STOCK":
			        generarStock();
			        break;
			        
			    case "4":
			    case "LISTAR STOCK":
			        System.out.println(listarStock());
			        break;
			      
			    case "5":
			    case "GENERAR MIS ACTIVOS":
			        generarActivos();
			        break;
			        
			    case "6":
			    case "LISTAR MIS ACTIVOS":
			        System.out.println(listarActivos());
			        break;
			        
			    case "7":
			    case "REALIZAR COMPRA DE CRIPTOMONEDAS":
			        realizarCompraDeCriptomoneda();
			    	break;
			        
			    case "8":
			    case "REALIZAR SWAP":
			        realizarSwap();
			        break;
			        
			    case "9":
			    case "FINALIZAR":
			    	finalizar = true;
			    	break;
			    	
			    default:
			        System.out.println(opcionIncorrecta);
			        break;
			}
		}
	}
	
	private static void crearMoneda() throws SQLException{
		Scanner scan = MyScanner.getScan();
		String tipo = confirmacionDeTipo();
		String nombre, sigla;
		double precioEnDolar;
		
		
		System.out.println("\n" + ingresarSiglaMoneda + ": ");
		sigla = scan.nextLine();
		
		if(tipo.equals(fiat)) {
			if(FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla) != null) { //HACER EL EXISTE_MONEDA EN VEZ DE BUSCAR
				System.out.println(monedaExiste);
				return;
			}

		}
		
		else {
			if(FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla) != null) {
				System.out.println(monedaExiste);
				return;
			}
		}
	
		System.out.println("\n" + ingresarNombreMoneda + ": ");
		nombre = scan.nextLine();
		System.out.println("\n" + ingresarPrecioMoneda + ": ");
		precioEnDolar = scan.nextDouble();
		scan.nextLine();
		
		if (tipo.equals(fiat)) crearMonedaFiat(nombre, sigla, precioEnDolar);
		else crearMonedaCripto(nombre, sigla, precioEnDolar);
	}
	
	private static String confirmacionDeTipo() {
		
		Scanner scan = MyScanner.getScan();
		boolean esTipo = false;
		String tipo = null;
		
		while (!esTipo) {
			System.out.println("\n" + ingresarTipoMoneda + ": ");
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
					System.out.println(opcionIncorrecta);
			}
		}
		
		return tipo;
		
	}
	
	private static void crearMonedaFiat(String nombre, String sigla, double precioEnDolar) throws SQLException{
		
		Scanner scan = MyScanner.getScan();
		String paisEmisor;
		
		System.out.println("\n" + ingresarPaisMoneda + ": ");
		paisEmisor = scan.nextLine();
		System.out.println();
		MonedaFiduciaria mf = new MonedaFiduciaria(nombre,sigla,precioEnDolar,paisEmisor);
		
		if (confirmacionDelUsuario(mf)) {
			FactoryDAO.getMonedaFiduciariaDAO().insertarMonedaFiduciaria(mf);
			System.out.println("\n" + operacionExitosa);
		}
		else {
			System.out.println("\n" + operacionCancelada);
		}
	}
	
	//FALTA ARREGLAR CON LOS STRING A PARTIR DE ACÁ
	
	//HAY QUE ESCRIBIR EN LOS LISTAR LAS RESPECTIVAS LINEAS CON LOS COMPARATOR
	
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
			System.out.println("Se ha cancelado la creación de la criptomoneda.\n");
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
		
		LinkedList<MonedaFiduciaria> listaMonedasFiat = (LinkedList<MonedaFiduciaria>) FactoryDAO.getMonedaFiduciariaDAO().listarMonedasFiduciarias();
		LinkedList<Criptomoneda> listaCriptomonedas = (LinkedList<Criptomoneda>) FactoryDAO.getCriptomonedaDAO().listarCriptomonedas();
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
		double cantidadStock = random.nextDouble() * 1000000;
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
		
		LinkedList<Stock> listaStocks = (LinkedList<Stock>) FactoryDAO.getStockDAO().listarStock();
		
		for(Stock e : listaStocks) {
			str+=e.toString();
		}
		str+="\n";
		return str;
	}
	
	private static String generarDireccion() {
		Random random = new Random();
		String direccion=null;
		int largo = 10;
		for (int i=0;i<largo;i++) {
			int digito = random.nextInt(10);
			direccion+=""+digito+"";
		}
		return direccion;
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
			
			direccion = generarDireccion();
			
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
		
		List<ActivoMonedaFiduciaria> listaActivosMonedaFiat = FactoryDAO.getActivoMonedaFiduciariaDAO().listarActivosFiduciarios();
		LinkedList<ActivoCripto> listaActivosCripto = (LinkedList<ActivoCripto>) FactoryDAO.getActivoCriptoDAO().listarActivosCripto();
		
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
		StockDAO stockDAO = FactoryDAO.getStockDAO();
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoMonedaFiduciariaDAO amfDAO = FactoryDAO.getActivoMonedaFiduciariaDAO();
		
		System.out.println("\nIngrese la sigla de la criptomoneda a comprar: ");
		siglaDeCriptoAComprar = scan.nextLine();
		System.out.println("\nIngrese la sigla de la moneda fiduciaria a utilizar en la compra: ");
		siglaDeFiatAUtilizar = scan.nextLine();
		System.out.println("\nIngrese la cantidad de moneda fiduciaria a utilizar: ");
		montoFiduciario = scan.nextDouble();
		scan.nextLine();
		
		ActivoMonedaFiduciaria amf = amfDAO.buscarActivoMonedaFiduciaria(siglaDeFiatAUtilizar);
		

		if (amf == null) {
			System.out.println("\nHa habido un error en la compra porque el activo fiduciario a utilizar no se encuentra entre sus activos.");
			return;
		}
		
		if (amf.getCantidad() < montoFiduciario) {
			System.out.println("\nHa habido un error en la compra porque no le alcanza el dinero.");
			return;
		}
		
		Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(siglaDeCriptoAComprar);
		
		if (cm == null) {
			System.out.println("\nHa habido un error en la compra, porque la Criptomoneda no se encuentra registrada en el sistema.");
			return;
		}
		
		montoCompradoDeCripto = (montoFiduciario * amf.getMonedaFIAT().getPrecioEnDolar()) / cm.getPrecioEnDolar();
		
		Stock stock = stockDAO.buscarStock(siglaDeCriptoAComprar);
		
		if (stock.getCantidad() < montoCompradoDeCripto) {
			
			System.out.println("Ha habido error en la compra porque la el stock en el sistema no es suficiente.\n\n");
			return;
			
		}
		
		String resumen = "\nSe han comprado " + montoCompradoDeCripto + " de " + siglaDeCriptoAComprar 
				+ " por " + montoFiduciario + " de " + siglaDeFiatAUtilizar + ".";

		
		Transaccion t = new Transaccion(resumen, LocalDate.now());
		
		if (confirmacionDelUsuario(t)) {
			
			ActivoCripto ac = acDAO.buscarActivoCripto(siglaDeCriptoAComprar);
			
			if (ac == null) {
				
				CrearActivoCriptoPorCompra(montoCompradoDeCripto, cm);
				
			} else {
				
				acDAO.sumarCantidadActivoCripto(siglaDeCriptoAComprar, montoCompradoDeCripto);
				
			}
			
			stockDAO.sumarCantidadStock(siglaDeCriptoAComprar, -montoCompradoDeCripto);
			amfDAO.sumarCantidadActivoFiduciaria(siglaDeFiatAUtilizar, -montoFiduciario);
			
			FactoryDAO.getTransaccionDAO().insertarTransaccion(t);
			System.out.println("\nSe ha completado la compra.");
			
		} else {
			
			System.out.println("\nSe ha cancelado la compra.");
			
		}
		
	}
	
	private static void CrearActivoCriptoPorCompra(double montoComprado, Criptomoneda cm) throws SQLException{
		
		String direccion = generarDireccion();
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
			scan.nextLine();
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
