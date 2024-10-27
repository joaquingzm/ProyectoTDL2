package modelos;

import dao_implementado_jdbc.ActivoCriptoDAOjdbc;
import dao_implementado_jdbc.ActivoMonedaFiduciariaDAOjdbc;
import dao_implementado_jdbc.CriptomonedaDAOjdbc;
import dao_implementado_jdbc.MonedaFiduciariaDAOjdbc;
import dao_interfaces.ActivoCriptoDAO;
import dao_interfaces.ActivoMonedaFiduciariaDAO;
import dao_interfaces.CriptomonedaDAO;
import dao_interfaces.MonedaFiduciariaDAO;

public class FactoryDAO {
	
	public static MonedaFiduciariaDAO getMonedaFiduciariaDAO() {
		return new MonedaFiduciariaDAOjdbc();
	}
	
	public static CriptomonedaDAO getCriptomonedaDAO() {
		return new CriptomonedaDAOjdbc();
	}
	
	public static ActivoMonedaFiduciariaDAO getActivoMonedaFiduciariaDAO() {
		return new ActivoMonedaFiduciariaDAOjdbc();
	}
	
	public static ActivoCriptoDAO getActivoCriptoDAO() {
		return new ActivoCriptoDAOjdbc();
	}
	
	/*public static MonedaFiduciariaDAO getMonedaFiduciariaDAO() {
		return new MonedaFiduciariaDAOjdbc();
	}*/
}
