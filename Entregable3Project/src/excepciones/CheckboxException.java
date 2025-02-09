package excepciones;

@SuppressWarnings("serial")
public class CheckboxException extends DataException{

	private String problemaTitulo = "Error con la aceptación de terminos y condiciones";
	private String problemaCuerpo = "No se aceptaron los Terminos y Condiciones.";
	
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}
	

	
}
