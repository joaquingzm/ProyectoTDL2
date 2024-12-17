package excepciones;

public class CompraNulaException extends DataException{

	private String problemaTitulo = "Error en la compra";
	private String problemaCuerpo = "Se intentó realizar la transacción con una cantidad nula de moneda fiduciaria.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
