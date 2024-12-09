package modelos;


import vista.FramePrincipal;

public class GestorDeDatosGlobales {

	private static int IdUsuario;
	private static FramePrincipal framePrincipal;

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
}
