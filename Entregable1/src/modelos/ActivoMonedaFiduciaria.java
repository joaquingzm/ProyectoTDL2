package modelos;


/**
 * Esta clase representa un activo fiduciario con las caracteristicas de un Activo y, ademas, 
 * incluye una moneda fiduciaria.
 * @see Activo
 * @see Billetera
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */

public class ActivoMonedaFiduciaria extends Activo{

	private MonedaFiduciaria monedaFIAT;

	/**
	 * @param cantidad la cantidad del activo fiduciario.
	 * @param monedaFIAT la moneda fiduciario instanciada, con las propiedades de una moneda fiduciaria.
	 */
	public ActivoMonedaFiduciaria(double cantidad, MonedaFiduciaria monedaFIAT) {
		super(cantidad);
		this.monedaFIAT = monedaFIAT;
	}
	
	/**
	 * Constructor por defecto de la Clase ActivoMonedaFiduciaria.
	 */
	public ActivoMonedaFiduciaria() {
		
	}

	/**
	 * @return la moneda fiduciaria que caracterice a esta clase.
	 */
	public MonedaFiduciaria getMonedaFIAT() {
		return monedaFIAT;
	}

	/**
	 * @param monedaFIAT la moneda fiduciaria que caracteriza a esta clase.
	 */
	public void setMonedaFIAT(MonedaFiduciaria monedaFIAT) {
		this.monedaFIAT = monedaFIAT;
	}
	
	
	
	
}
