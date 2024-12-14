package modelos;

public enum InformacionDeMonedasFiduciarias {

	ARS("Peso argentino","ARS",(1/1036), "Argentina"),
	USD("Dolar estadounidense","USD",1, "Estados Unidos"),
	EUR("Euro","EUR",1.056, "EUROPA");

	private String nombre;
	private String sigla;
	private double precioEnDolar;
	private String paisEmisor;
	
	private InformacionDeMonedasFiduciarias(String nombre, String sigla, double precioEnDolar, String paisEmisor) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.precioEnDolar = precioEnDolar;
		this.paisEmisor = paisEmisor;
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

	public String getPaisEmisor() {
		return paisEmisor;
	}


	
}
