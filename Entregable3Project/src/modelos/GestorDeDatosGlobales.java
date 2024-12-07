package modelos;

import javax.swing.JPanel;

public class GestorDeDatosGlobales {

	private static int IdUsuario;
	private static JPanel panelPrincipal;

	public static int getIdUsuario() {
		return IdUsuario;
	}

	public static void setIdUsuario(int idUsuario) {
		GestorDeDatosGlobales.IdUsuario = idUsuario;
	}

	public static JPanel getPanelPrincipal() {
		return panelPrincipal;
	}

	public static void setPanelPrincipal(JPanel panelPrincipal) {
		GestorDeDatosGlobales.panelPrincipal = panelPrincipal;
	}
	
}
