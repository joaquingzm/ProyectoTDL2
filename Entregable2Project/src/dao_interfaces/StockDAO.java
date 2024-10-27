package dao_interfaces;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import modelos.Stock;

public interface StockDAO {
	
	public void insertarStock() throws SQLException;
	
	List<Stock> listarStock(Comparator<Stock> c) throws SQLException;
	
	//void eliminarStock() throws SQLException;
	
	//void actualizarStock() throws SQLException;

	
}
