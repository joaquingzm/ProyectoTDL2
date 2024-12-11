package excepciones;

public class TextFieldRegistracionException extends DataException{

	private String problemaTitulo = "Error en rellenar campos";
	private String problemaCuerpo = "No se completó alguno de los campos solicitados.";
	
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}
	
	public String getProblemaTotalCuerpo() {
		return this.getProblemaPrincipal() + problemaCuerpo;
	}

	
}