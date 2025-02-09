package excepciones;

@SuppressWarnings("serial")
public class TextFieldException extends DataException{

	private String problemaTitulo = "Campos incompletos";
	private String problemaCuerpo = "No se completó alguno/s de los campos solicitados.";
	
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	
}