package ejercicio3;


/**
 * Esta clase representa de manera abstracta un activo, del que solo se sabrá
 * su cantidad puesto que el tipo del mismo es responsabilidad de las clases herederas.
 * @see ActivoCripto
 * @see ActivoMonedaFiduciaria
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */
public class Activo {
	
	private double cantidad;

	/**
	 * @param cantidad la cantidad del activo en cuestion.
	 */
	public Activo(double cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
	/**
	 * Constructor por defecto de la Clase Activo.
	 */
	public Activo() {
		
	}

	/**
	 * @return la cantidad de ese activo.
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad la cantidad que se tiene de un activo
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
