package modelos;

import java.time.LocalDate;

public class Transaccion {
	
	private String resumen;
	private LocalDate fecha;
	
	/**
	 * @param resumen
	 * @param fecha
	 */
	
	public Transaccion(String resumen, LocalDate fecha) {
		super();
		this.resumen = resumen;
		this.fecha = fecha;
	}
	
	public Transaccion() {
		
	}
	
	public String getResumen() {
		return resumen;
	}
	
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	
	public LocalDate getFechaHora() {
		return fecha;
	}
	
	public void setFechaHora(LocalDate fechaHora) {
		this.fecha = fechaHora;
	}
	
	
	
}
