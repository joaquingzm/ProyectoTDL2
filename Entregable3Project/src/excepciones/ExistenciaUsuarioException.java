package excepciones;

@SuppressWarnings("serial")
public class ExistenciaUsuarioException extends DataException{

	private String problemaTitulo = "Información inexistente";
	private String problemaCuerpo = "Ha habido un error porque la información ingresada no corresponde a ningun usuario.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
