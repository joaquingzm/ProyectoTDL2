package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Stock;

public interface StockDAO {
	
	public void insertarStock(Stock stock) throws SQLException;
	
	public Stock buscarStock(int idStock ) throws SQLException;
	
	List<Stock> listarStock() throws SQLException;
	
	public void sumarCantidadStock(int idStock,double cantidad) throws SQLException;
	 
	public void cambiarCantidadStock(int idStock, double cantidad) throws SQLException;
}
