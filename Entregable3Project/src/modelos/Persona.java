package modelos;

public class Persona {
	
	private String nombres;
	private String apellidos;
	
	public Persona(String nombres, String apellidos) {
		this.setNombres(nombres);
		this.setApellidos(apellidos);
	}
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
}
