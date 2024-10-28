package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modelos.Criptomoneda;
import modelos.Stock;
import singletones.MyStatement;

public class StockDAOjdbc implements StockDAO{
	
	@Override
	public void insertarStock(Stock stock) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO STOCK (cantidad,sigla_cripto) VALUES ("
				+ stock.getCantidad()
				+ ","
				+ stock.getCriptomoneda().getSigla()
				+ ")";

		stmt.executeUpdate(sql);
	}
	
	@Override
	public Stock buscarStock(String sigla) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT * FROM STOCK WHERE sigla = '"+sigla+"'";
		Stock stock = null;
		Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			stock = new Stock(resul.getDouble("nombre"),cm);
		}
		
		return stock;
	}

	@Override
	public List<Stock> listarStock(Comparator<Stock> c) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM STOCK";
		LinkedList<Stock> listaStocks = new LinkedList<Stock>();
		Stock stock = null;
		Criptomoneda cm = null;
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {
			cm = new Criptomoneda(resul.getString("nombre"), resul.getString("sigla"), resul.getDouble("precioEnDolar"), resul.getDouble("volatilidad"));
			stock = new Stock(resul.getDouble("cantidad"), cm);
			listaStocks.add(stock);
		}
		
		listaStocks.sort(c);
		return listaStocks;
	}

}
