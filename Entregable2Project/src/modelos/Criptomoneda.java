package modelos;

/**
 * Esta clase modela una criptomoneda, como puede ser BitCoin o USDT.
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @see Moneda
 * @version 1.0
 */

public class Criptomoneda extends Moneda{

	private double volatilidad;
	
	/**
	 * @param nombre el nombre de la criptomoneda.
	 * @param sigla la sigla de la criptomoneda.
	 * @param precioEnDolar el precio en dolares de la criptomoneda.
	 */
	public Criptomoneda(String nombre, String sigla, double precioEnDolar, double volatilidad) {
		super(nombre, sigla, precioEnDolar);
		this.volatilidad = volatilidad;
	}

	/**
	 * Constructor por defecto de la clase Criptomoneda.
	 */
	public Criptomoneda() {
		
	}
	
	public double getVolatilidad() {
		return volatilidad;
	}

	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
	}
	
	@Override
	public String toString() {
		String str = super.toString()+", Criptomoneda:{ Volatilidad: "+this.getVolatilidad()+" }";
		return str;
	}
	
}
