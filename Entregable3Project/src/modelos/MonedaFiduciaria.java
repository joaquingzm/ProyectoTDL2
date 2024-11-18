package modelos;

/**
 * Esta clase abstrae una moneda fiduciaria, como por ejeplo el dolar o el euro.
 * @see Moneda
 * @author Joaquin Guzman y Octavio Tomas Isabella Valenzi
 * @version 1.0
 */

public class MonedaFiduciaria extends Moneda{

	private String paisEmisor;
	
	/**
	 * @param nombre el nombre de la moneda fiduciaria.
	 * @param sigla la sigla que identifiquen a esa moneda fiduciaria.
	 * @param precioEnDolar el precio en dolares de la moneda fiduciaria.
	 * @param paisEmisor el pais que emite la moneda fiduciaria.
	 */
	public MonedaFiduciaria(String nombre, String sigla, double precioEnDolar, String paisEmisor) {
		super(nombre, sigla, precioEnDolar);
		this.paisEmisor = paisEmisor;
	}

	/**
	 * Constructor por defecto de la clase MonedaFiduciaria.
	 */
	public MonedaFiduciaria() {
		
	}

	/**
	 * @return el pais que emite la moneda fiduciaria
	 */
	public String getPaisEmisor() {
		return paisEmisor;
	}

	/**
	 * @param paisEmisor el pais que emite la moneda fiduciaria
	 */
	public void setPaisEmisor(String paisEmisor) {
		this.paisEmisor = paisEmisor;
	}
	
	@Override
	public String toString() {
		String str = super.toString()+", Pais Emisor: "+this.getPaisEmisor();
		return str;
	}
	
}
