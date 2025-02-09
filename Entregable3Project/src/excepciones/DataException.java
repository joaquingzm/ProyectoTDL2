package excepciones;

@SuppressWarnings("serial")
public abstract class DataException extends Exception{

	public abstract String getProblemaCuerpo();

	public abstract String getProblemaTitulo();
	
}
