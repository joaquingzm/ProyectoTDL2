package excepciones;

public class CheckboxRegistracionException extends DataException{

	private String problemaTitulo = "Error con la aceptación de terminos y condiciones";
	private String problemaCuerpo = "No se aceptaron los Terminos y Condiciones.";
	
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
