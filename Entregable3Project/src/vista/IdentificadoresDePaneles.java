package vista;

public enum IdentificadoresDePaneles {

	MENUINICIO("Menú Inicio"), MENUREGISTRACION("Menú Registración"), MENUMISACTIVOS("Menú Mis Activos"), MENUMISOPERACIONES("Menú Mis Operaciones"), MENUCOMPRA("Menú Compra"), MENUCOTIZACIONES("Menú Cotizaciones");
	
	private final String titulo;
	
	IdentificadoresDePaneles(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
}
