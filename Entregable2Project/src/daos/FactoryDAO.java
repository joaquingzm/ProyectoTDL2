package daos;

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
	
	public static StockDAO getStockDAO() {
		return new StockDAOjdbc();
	}
	
	public static TransaccionDAO getTransaccionDAO() {
		return new TransaccionDAOjdbc();
	}
}
