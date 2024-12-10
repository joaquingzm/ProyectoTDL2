package controlador.FramePrincipalListeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controlador.GestorDeDatosDelControlador;
import singletones.MyConnection;

public class CierreListener extends WindowAdapter{

	
	@Override
    public void windowClosing(WindowEvent e) {
        MyConnection.cerrarCon();
        GestorDeDatosDelControlador.terminarTimer();
    }
	
}
