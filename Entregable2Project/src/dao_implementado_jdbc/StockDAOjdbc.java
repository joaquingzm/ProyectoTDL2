package dao_implementado_jdbc;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import dao_interfaces.StockDAO;
import modelos.Stock;

public class StockDAOjdbc implements StockDAO{

	@Override
	public List<Stock> listarStock(Comparator<Stock> c) throws SQLException{
		
		return null;
	}

}
