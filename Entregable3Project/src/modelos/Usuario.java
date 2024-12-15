package modelos;

public class Usuario {
	
	private Persona persona;
	private String email;
	private String password;
	private boolean acepta_terminos;
	
	public Usuario(Persona persona, String email, String password, boolean acepta_terminos) {
		this.setPersona(persona);
		this.setEmail(email);
		this.setPassword(password);
		this.setAcepta_terminos(acepta_terminos);
	}
	
	public Persona getPersona() {
		return persona;
	}
	
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getAcepta_terminos() {
		return acepta_terminos;
	}
	
	public void setAcepta_terminos(boolean acepta_terminos) {
		this.acepta_terminos = acepta_terminos;
	}
	
	
}
