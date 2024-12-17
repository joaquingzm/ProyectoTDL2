package controlador;

import java.sql.SQLException;
import java.util.List;

import daos.ActivoCriptoDAO;
import daos.FactoryDAO;
import daos.MonedaFiduciariaDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.MonedaFiduciaria;
import vista.menuMisActivos.MenuMisActivos;

public class GestorDeActualizaciones {

	public static void actualizarMenuMisActivos(int idUsuario) throws SQLException {
		
		List<ActivoCripto> listaActivosCripto = null;
		List<ActivoMonedaFiduciaria> listaActivosFIAT = null;

		listaActivosCripto = FactoryDAO.getActivoCriptoDAO().listarActivosCripto(idUsuario);
		listaActivosFIAT = FactoryDAO.getActivoMonedaFiduciariaDAO().listarActivosFiduciarios(idUsuario);
		
		GestorDeDatosDelControlador.getFramePrincipal().getMenuMisActivos().actualizarActivos(listaActivosCripto, listaActivosFIAT);
	}
	
	public static void actualizarMenuCotizaciones() throws SQLException {
		
		List<Criptomoneda> listaCriptos = FactoryDAO.getCriptomonedaDAO().listarCriptomonedas();
		Boolean[] tieneActivo = new Boolean[listaCriptos.size()];
		ActivoCriptoDAO acDAO = FactoryDAO.getActivoCriptoDAO();
			
		for(int i=0;i<listaCriptos.size();i++) {
			if(acDAO.tieneActivoCripto(GestorDeDatosDelControlador.getIdUsuario(), FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(listaCriptos.get(i).getSigla()))) {
				tieneActivo[i] = true;
			}
			else {
				tieneActivo[i] = false;
			}
		}
		
		GestorDeDatosDelControlador.getFramePrincipal().getMenuCotizaciones().actualizarTabla(tieneActivo, listaCriptos);
		

	}
	
	public static void actualizarMenuMisActivosFIAT() throws SQLException{

		MenuMisActivos menuMisActivos = GestorDeDatosDelControlador.getFramePrincipal().getMenuMisActivos();
		
		String siglaElegida = menuMisActivos.extraerSiglaSelectorFIAT();
		
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		int idMF;
		MonedaFiduciaria mf = null;
		idMF = mfDAO.buscarMonedaFiduciariaId(siglaElegida);
		mf = mfDAO.buscarMonedaFiduciaria(idMF);

		menuMisActivos.actualizarMonedaFIAT(mf);
	}
}
