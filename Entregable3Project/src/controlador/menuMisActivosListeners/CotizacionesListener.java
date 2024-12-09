package controlador.menuMisActivosListeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import daos.FactoryDAO;
import modelos.Criptomoneda;
import modelos.GestorDeDatosGlobales;
import modelos.MonedaFiduciaria;
import vista.FramePrincipal;
import vista.IdentificadoresDePaneles;

public class CotizacionesListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		FramePrincipal framePrincipal = GestorDeDatosGlobales.getFramePrincipal();
		
		JPanel panelPrincipal = framePrincipal.getPanelPrincipal();
		CardLayout cardLayout = framePrincipal.getCardLayout();
		
		List<Criptomoneda> listaCriptos;
		
		Boolean[] tieneActivo;
		
		try {
			listaCriptos = GestorDeDatosGlobales.getListaCriptos();
			tieneActivo = new Boolean[listaCriptos.size()];
			for(int i=0;i<listaCriptos.size();i++) {
				if(FactoryDAO.getActivoCriptoDAO().tieneActivoCripto(GestorDeDatosGlobales.getIdUsuario(), listaCriptos.get(i).getSigla())) {
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
		
		framePrincipal.getMenuCotizaciones().actualizarTabla(tieneActivo);;
		
		GestorDeDatosGlobales.comenzarTimer();
		
		cardLayout.show(panelPrincipal, IdentificadoresDePaneles.MENUCOTIZACIONES.name());
		
	}

}
