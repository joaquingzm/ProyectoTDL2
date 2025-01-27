package controlador;


import java.util.Timer;
import java.util.TimerTask;

import vista.FramePrincipal;

public class GestorDeDatosDelControlador {

	private static int IdUsuario;
	private static FramePrincipal framePrincipal;
	private static Timer timer = null;
	private static double porcentajeComision = 2;

	public static int getIdUsuario() {
		return IdUsuario;
	}

	public static void setIdUsuario(int idUsuario) {
		GestorDeDatosDelControlador.IdUsuario = idUsuario;
	}

	public static FramePrincipal getFramePrincipal() {
		return framePrincipal;
	}

	public static void setFramePrincipal(FramePrincipal framePrincipal) {
		GestorDeDatosDelControlador.framePrincipal = framePrincipal;
	}
	
	public static void comenzarTimer(int delay) {
        if (timer == null) {
            timer = new Timer(); 
            TimerTask updaterCotizaciones = new UpdaterCotizaciones();
            timer.schedule(updaterCotizaciones,delay,60000);
        }
	}
	
	public static void terminarTimer() {
		
	       if (timer != null) {
	            timer.cancel();
	            timer = null;
	        } 
	}

	public static double getPorcentajeComision() {
		return porcentajeComision;
	}

	public static void setPorcentajeComision(double porcentajeComision) {
		GestorDeDatosDelControlador.porcentajeComision = porcentajeComision;
	}
}
