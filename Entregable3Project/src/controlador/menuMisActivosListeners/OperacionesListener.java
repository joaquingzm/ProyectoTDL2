package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import daos.FactoryDAO;
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		menuMisOperaciones.actualizarOperaciones(listaTransacciones);
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUMISOPERACIONES.name());
		
	}

}
