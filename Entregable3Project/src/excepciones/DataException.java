package excepciones;

public class DataException extends Exception{

	private String problemaPrincipal = "Ha habido un problema con los campos completados: ";

	public String getProblemaPrincipal() {
		return problemaPrincipal;
	}

	public void setProblemaPrincipal(String problemaPrincipal) {
		this.problemaPrincipal = problemaPrincipal;
	}
	
}
