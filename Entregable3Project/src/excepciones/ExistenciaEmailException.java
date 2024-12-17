package excepciones;

public class ExistenciaEmailException extends DataException{

	private String problemaTitulo = "Existe el Email";
	private String problemaCuerpo = "Ha habido un error porque el email ingresado ya le pertenece a un usuario.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
