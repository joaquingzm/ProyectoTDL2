package modelos;

public enum InformacionDeCriptomonedas {

	BTC("Bitcoin", "BTC", 100000, 1),
	ETH("Ethereum", "ETH", 3700, 1),
	USDC("Usd-coin", "USDC", 1, 1),
	USDT("Tether", "USDT", 1, 1),
	DOGE("Dogecoin", "DOGE", 0.4, 1);

	private String nombre;
	private String sigla;
	private double precioEnDolar;
	private double volatilidad;
	
	private InformacionDeCriptomonedas(String nombre, String sigla, double precioEnDolar, double volatilidad) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.precioEnDolar = precioEnDolar;
		this.volatilidad = volatilidad;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSigla() {
		return sigla;
	}

	public double getPrecioEnDolar() {
		return precioEnDolar;
	}

	public double getVolatilidad() {
		return volatilidad;
	}
}
