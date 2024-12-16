package excepciones;

public class TextFieldException extends DataException{

	private String problemaTitulo = "Error en rellenar campos";
	private String problemaCuerpo = "No se completó alguno de los campos solicitados.";
	
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	
}