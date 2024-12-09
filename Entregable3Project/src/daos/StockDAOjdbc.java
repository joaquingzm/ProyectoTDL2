package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import modelos.Criptomoneda;
import modelos.Stock;
import singletones.MyConnection;


public class StockDAOjdbc implements StockDAO{
	
	@Override
	public void insertarStock(Stock stock) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO STOCK (CANTIDAD,SIGLA) VALUES ("
				+ stock.getCantidad()
				+ ","
				+ "'" + stock.getCriptomoneda().getSigla()
				+ "')";

		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	@Override
	public Stock buscarStock(String sigla) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		System.out.println(sigla);
		String sql = "SELECT * FROM STOCK WHERE SIGLA = '"+sigla+"'";
		Stock stock = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			double cantidad = resul.getDouble("cantidad");
			Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
			stock = new Stock(cantidad,cm);
		}
		resul.close();
		
		stmt.close();
		return stock;
	}

	@Override
	public List<Stock> listarStock() throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM STOCK";
		LinkedList<Stock> listaStocks = new LinkedList<Stock>();
		Stock stock = null;
		Criptomoneda cm = null;
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {
			String sigla = resul.getString("SIGLA");
			double cantidad = resul.getDouble("cantidad");
			String rutaIcono = resul.getString("RUTA_ICONO");
			cm = new Criptomoneda(FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getNombre(), sigla, FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getPrecioEnDolar(), FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla).getVolatilidad(), rutaIcono);
			stock = new Stock(cantidad, cm);
			listaStocks.add(stock);
		}
		resul.close();
		
		stmt.close();
		
		return listaStocks;
	}

	@Override
	public void sumarCantidadStock(String sigla, double cantidad) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT CANTIDAD FROM STOCK WHERE SIGLA = '"+sigla+"'";
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE STOCK SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	
	@Override
	public void cambiarCantidadStock(String sigla, double cantidad) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "UPDATE STOCK SET CANTIDAD = "+cantidad+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
		
		stmt.close();
	}
}
