package excepciones;

public class CantidadActivoFiduciarioException extends DataException{

	private String problemaTitulo = "Dinero insuficiente";
	private String problemaCuerpo = "Ha habido un error porque el dinero es insuficiente.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
