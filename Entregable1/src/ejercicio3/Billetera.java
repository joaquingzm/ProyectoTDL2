package ejercicio3;

import java.util.LinkedList;

/**
 * Esta clase representa una Billetera de criptomonedas, con el tipo de moneda fiduciaria elegida por el usuario y
 * dos colecciones con los activos criptomonedas y activos fiduciarios respectivamente.
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */
public class Billetera {
	
	private MonedaFiduciaria fiatElegido;
	private LinkedList<ActivoCripto> activosCripto;
	private LinkedList<ActivoMonedaFiduciaria> activosFiduciarios;
	
	/**
	 * @param fiatElegido el tipo de moneda fiduciaria que el usuario prefiere.
	 * @param activosCripto la lista de los diferentes activos criptomoneda que el usuario posee.
	 * @param activosFiduciarios la lista de los diferentes activos fiduciarios que el usuario posee.
	 */
	public Billetera(MonedaFiduciaria fiatElegido, LinkedList<ActivoCripto> activosCripto,
			LinkedList<ActivoMonedaFiduciaria> activosFiduciarios) {
		super();
		this.fiatElegido = fiatElegido;
		this.activosCripto = activosCripto;
		this.activosFiduciarios = activosFiduciarios;
	}
	
	/**
	 * Constructor por defecto de la clase Billetera
	 */
	public Billetera() {
		
	}
	/**
	 * @return el tipo de moneda fiuciaria a utilizar segun preferencia del usuario.
	 */
	public MonedaFiduciaria getFiatElegido() {
		return fiatElegido;
	}
	/**
	 * @param fiatElegido el tipo de moneda fiduciaria elegida por el usuario.
	 */
	public void setFiatElegido(MonedaFiduciaria fiatElegido) {
		this.fiatElegido = fiatElegido;
	}
	/**
	 * @return una lista que almacena los activos criptomoneda que posee el usuario.
	 */
	public LinkedList<ActivoCripto> getActivosCripto() {
		return new LinkedList<ActivoCripto> (activosCripto);
	}
	/**
	 * @param activosCripto el conjunto de activos cripto que el usuario posee.
	 */
	public void setActivosCripto(LinkedList<ActivoCripto> activosCripto) {
		this.activosCripto = activosCripto;
	}
	/**
	 * @return una lista con los diferentes activos fiduciarios que el usuario tiene.
	 */
	public LinkedList<ActivoMonedaFiduciaria> getActivosFiduciarios() {
		return new LinkedList<ActivoMonedaFiduciaria>(activosFiduciarios);
	}
	/**
	 * @param activosFiduciarios el conjunto de activos fiduciarios a almacenar.
	 */
	public void setActivosFiduciarios(LinkedList<ActivoMonedaFiduciaria> activosFiduciarios) {
		this.activosFiduciarios = activosFiduciarios;
	}
	
	
	
}
