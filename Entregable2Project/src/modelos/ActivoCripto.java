package modelos;

/**
 * Esta clase representa un activo criptomoneda, el cual hereda las caracteristicas
 * de un Activo e incluye la direccion especifica de una criptomoneda moneda y la 
 * criptomoneda en si.
 * @see Activo
 * @see Billetera
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */
public class ActivoCripto extends Activo{

	private String direccion;
	private Criptomoneda criptomoneda;
	
	/**
	 * @param cantidad la cantidad de la criptomoneda en cuestion.
	 * @param direccion la dirección propia y unica de la criptomoneda en cuestion.
	 * @param criptomoneda la criptomoneda que caracteriza principalmente esta clase.
	 */
	public ActivoCripto(double cantidad, String direccion, Criptomoneda criptomoneda) {
		super(cantidad);
		this.direccion = direccion;
		this.criptomoneda = criptomoneda;
	}
	
	/**
	 * Constructor por defecto de la clase ActivoCripto.
	 */
	public ActivoCripto() {
		
	}
	
	/**
	 * @return la direccion especifica de la criptomoneda.
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion la direccion a colocar para esa criptomoneda.
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return la criptomoneda.
	 */
	public Criptomoneda getCriptomoneda() {
		return criptomoneda;
	}
	/**
	 * @param criptomoneda la criptomoneda propia de cada instancia.
	 */
	public void setCriptomoneda(Criptomoneda criptomoneda) {
		this.criptomoneda = criptomoneda;
	}


	
	
}
