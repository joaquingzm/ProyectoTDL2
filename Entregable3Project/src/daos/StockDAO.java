package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Stock;

public interface StockDAO {
	
	public void insertarStock(Stock stock) throws SQLException;
	
	public Stock buscarStock(String sigla) throws SQLException;
	
	List<Stock> listarStock() throws SQLException;
	
	public void sumarCantidadStock(String sigla,double cantidad) throws SQLException;
	 
	public void cambiarCantidadStock(String sigla, double cantidad) throws SQLException;
}
