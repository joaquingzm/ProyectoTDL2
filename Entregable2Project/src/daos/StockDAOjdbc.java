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
		String sql = "INSERT INTO STOCK (CANTIDAD,SIGLA) VALUES ("
				+ stock.getCantidad()
				+ ","
				+ "'" + stock.getCriptomoneda().getSigla()
				+ "')";

		stmt.executeUpdate(sql);
	}
	
	@Override
	public Stock buscarStock(String sigla) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT * FROM STOCK WHERE SIGLA = '"+sigla+"'";
		Stock stock = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
			stock = new Stock(resul.getDouble("cantidad"),cm);
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
			cm = new Criptomoneda(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getDouble("VOLATILIDAD"));
			stock = new Stock(resul.getDouble("CANTIDAD"), cm);
			listaStocks.add(stock);
		}
		
		listaStocks.sort(c);
		return listaStocks;
	}

}
