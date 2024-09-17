package ejercicio3;

/**
 * Esta clase abstrae una moneda fiduciaria, como por ejeplo el dolar o el euro.
 * @see Moneda
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */

public class MonedaFiduciaria extends Moneda{

	/**
	 * @param nombre el nombre de la moneda fiduciaria.
	 * @param sigla la sigla que identifiquen a esa moneda fiduciaria.
	 * @param precioEnDolar el precio en dolares de la moneda fiduciaria.
	 */
	public MonedaFiduciaria(String nombre, String sigla, double precioEnDolar) {
		super(nombre, sigla, precioEnDolar);
	}

	/**
	 * Constructor por defecto de la clase MonedaFiduciaria.
	 */
	public MonedaFiduciaria() {
		
	}
	
}
