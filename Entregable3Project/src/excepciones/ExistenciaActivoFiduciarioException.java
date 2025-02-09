package excepciones;

@SuppressWarnings("serial")
public class ExistenciaActivoFiduciarioException extends DataException{

	private String problemaTitulo = "Error con la existencia de un activo fiduciario";
	private String problemaCuerpo = "No se posee el activo fiduciario propuesto.";
	
	@Override
	public String getProblemaCuerpo() {
		return problemaCuerpo;
	}

	@Override
	public String getProblemaTitulo() {
		return problemaTitulo;
	}

}
