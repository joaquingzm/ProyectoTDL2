package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
import excepciones.InformacionExcepciones;
import modelos.Transaccion;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;
import vista.menuMisOperaciones.MenuMisOperaciones;

public class OperacionesListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		MenuMisOperaciones menuMisOperaciones = GestorDeDatosDelControlador.getFramePrincipal().getMenuMisOperaciones();
		
		int idUsuario = GestorDeDatosDelControlador.getIdUsuario();
		
		List<Transaccion> listaTransacciones = null;
		
		try {
			listaTransacciones = FactoryDAO.getTransaccionDAO().listarTransacciones(idUsuario);
			
		} catch (SQLException exc) {
			FramePrincipal.mostrarAviso(InformacionExcepciones.SQL.getTitulo(), InformacionExcepciones.SQL.getCuerpo());
			return;
		}

		menuMisOperaciones.actualizarOperaciones(listaTransacciones);
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISOPERACIONES.name());
		
	}

}
