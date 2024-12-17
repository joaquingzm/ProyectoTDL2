package excepciones;

public enum InformacionExcepciones {

	SQL("Error con la base de datos", "Ha ocurrido un inconveniente en la comunicación con la base de datos."),
	IyO("Error de E/S", "Ha habido un problema de entrada/salida."),
	INTERRUPCION("Error por interrupción", "Han habido problemas debido a una interrupción en el momento equivocado."),
	NUMBERFORMAT("Error por formato de numero", "El formato de la cadena no corresponde a un formato de numero correcto.");
	
	private String titulo;
	private String cuerpo;
	
	private InformacionExcepciones(String titulo, String cuerpo) {
		this.titulo = titulo;
		this.cuerpo = cuerpo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	
}
