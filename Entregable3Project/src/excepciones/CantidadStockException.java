package excepciones;

public class CantidadStockException extends DataException{

	private String problemaTitulo = "Stock insuficiente";
	private String problemaCuerpo = "Ha habido un error porque el stock en el sistema no es suficiente.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
