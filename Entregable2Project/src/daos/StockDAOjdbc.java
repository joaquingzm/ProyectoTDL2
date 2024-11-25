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
		System.out.println(sigla);
		String sql = "SELECT * FROM STOCK WHERE SIGLA = '"+sigla+"'";
		Stock stock = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			double cantidad = resul.getDouble("cantidad");
			Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
			stock = new Stock(cantidad,cm);
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
			String sigla = resul.getString("SIGLA");
			double cantidad = resul.getDouble("cantidad");
			cm = new Criptomoneda(FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getNombre(), sigla, FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getPrecioEnDolar(), FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getVolatilidad());
			stock = new Stock(cantidad, cm);
			listaStocks.add(stock);
		}
		
		listaStocks.sort(c);
		return listaStocks;
	}

	@Override
	public void sumarCantidadStock(String sigla, double cantidad) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT CANTIDAD FROM STOCK WHERE SIGLA = '"+sigla+"'";
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE STOCK SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
	}
	
	@Override
	public void cambiarCantidadStock(String sigla, double cantidad) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "UPDATE STOCK SET CANTIDAD = "+cantidad+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
	}
}
