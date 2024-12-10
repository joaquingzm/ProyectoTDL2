package modelos;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import vista.FramePrincipal;

public class GestorDeDatosGlobales {

	private static int IdUsuario;
	private static FramePrincipal framePrincipal;
	private static Timer timer = null;
	private static List<Criptomoneda> listaCriptos;
	private static List<MonedaFiduciaria> listaMonedasFiduciaria;

	public static int getIdUsuario() {
		return IdUsuario;
	}

	public static void setIdUsuario(int idUsuario) {
		GestorDeDatosGlobales.IdUsuario = idUsuario;
	}

	public static FramePrincipal getFramePrincipal() {
		return framePrincipal;
	}

	public static void setFramePrincipal(FramePrincipal framePrincipal) {
		GestorDeDatosGlobales.framePrincipal = framePrincipal;
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

	public static List<Criptomoneda> getListaCriptos() {
		return listaCriptos;
	}

	public static void setListaCriptos(List<Criptomoneda> listaCriptos) {
		GestorDeDatosGlobales.listaCriptos = listaCriptos;
	}

	public static List<MonedaFiduciaria> getListaMonedasFiduciaria() {
		return listaMonedasFiduciaria;
	}

	public static void setListaMonedasFiduciaria(List<MonedaFiduciaria> listaMonedasFiduciaria) {
		GestorDeDatosGlobales.listaMonedasFiduciaria = listaMonedasFiduciaria;
	}
}
