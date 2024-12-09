package modelos;

/**
 * Esta clase representa una moneda, caracterizando su nombre, su sigla y su precio en dolares.
 * @see MonedaFiduciaria
 * @see Criptomoneda
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */
public class Moneda {
	
	private String nombre;
	private String sigla;
	private double precioEnDolar;
	
	/**
	 * @param nombre nombre de la moneda
	 * @param sigla sigla de la moneda
	 * @param precioEnDolar precio en dolares
	 */
	public Moneda(String nombre, String sigla, double precioEnDolar) {
		super();
		this.nombre = nombre;
		this.sigla = sigla;
		this.precioEnDolar = precioEnDolar;
	}
	
	/**
	 * Constructor por defecto de la clase Moneda
	 */
	public Moneda() {
		
	}
	
	/**
	 * @return el nombre de la moneda
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre el nombre de la moneda a almacenar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return la sigla de la moneda.
	 */
	public String getSigla() {
		return sigla;
	}
	/**
	 * @param sigla la sigla de la moneda
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	/**
	 * @return el precio de la moneda en dolares
	 */
	public double getPrecioEnDolar() {
		return precioEnDolar;
	}
	/**
	 * @param precioEnDolar el precio de la moneda en dolares
	 */
	public void setPrecioEnDolar(double precioEnDolar) {
		this.precioEnDolar = precioEnDolar;
	}

	@Override
	public String toString() {
		String str = "Nombre de moneda: "+this.getNombre()+", Sigla: "+this.getSigla()+", Precio en dolár: "+this.getPrecioEnDolar();
		return str;
	}
	
}
