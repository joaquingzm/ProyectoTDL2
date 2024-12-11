package modelos;

import java.time.LocalDateTime;

public class Transaccion {
	
	private String resumen;
	private LocalDateTime fechaYHora;
	
	/**
	 * @param resumen
	 * @param fechaYHora
	 */
	
	public Transaccion(String resumen, LocalDateTime fechaYHora) {
		super();
		this.resumen = resumen;
		this.fechaYHora = fechaYHora;
	}
	
	public Transaccion() {
		
	}
	
	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public LocalDateTime getfechaYHora() {
		return fechaYHora;
	}
	
	public void setfechaYHora(LocalDateTime fechaYHora) {
		this.fechaYHora = fechaYHora;
	}
	
	public String toString() {
		
		String str = this.fechaYHora.toString() + "  "
				     + this.resumen + "\n";
		
		return str;
	}
	
	
	
}
