package controlador;


import java.util.Timer;
import java.util.TimerTask;

import vista.FramePrincipal;

public class GestorDeDatosDelControlador {

	private static int IdUsuario;
	private static FramePrincipal framePrincipal;
	private static Timer timer = null;

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
	
	public static void comenzarTimer() {
        if (timer == null) {
            timer = new Timer(); 
            TimerTask updaterCotizaciones = new UpdaterCotizaciones();
            timer.schedule(updaterCotizaciones,0,60000);
        }
	}
	
	public static void terminarTimer() {
		
	       if (timer != null) {
	            timer.cancel();
	            timer = null;
	        } 
	}
}
