package controlador.menuMisActivosListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;


import controlador.GestorDeDatosDelControlador;
import daos.ActivoCriptoDAO;
import daos.FactoryDAO;
import modelos.Criptomoneda;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CotizacionesListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosDelControlador.getFramePrincipal();
		
		List<Criptomoneda> listaCriptos;
		
		Boolean[] tieneActivo = null;
		
		try {
			listaCriptos = FactoryDAO.getCriptomonedaDAO().listarCriptomonedas();
			tieneActivo = new Boolean[listaCriptos.size()];
			
			ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
			
			for(int i=0;i<listaCriptos.size();i++) {
				if(acDAO.tieneActivoCripto(GestorDeDatosDelControlador.getIdUsuario(), listaCriptos.get(i).getSigla())) {
					tieneActivo[i] = true;
				}
				else {
					tieneActivo[i] = false;
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		
		framePrincipal.getMenuCotizaciones().actualizarTabla(tieneActivo, listaCriptos);
		
		GestorDeDatosDelControlador.comenzarTimer();
		
		framePrincipal.cambiarMenu(IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
