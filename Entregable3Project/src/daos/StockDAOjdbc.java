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
		
		int idCripto = FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(stock.getCriptomoneda());
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO STOCK (CANTIDAD,ID_CRIPTO) VALUES ("
				+ stock.getCantidad()
				+ ","
				+ idCripto
				+ ")";

		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	@Override
	public Stock buscarStock(int idCripto) throws SQLException {

		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM STOCK WHERE ID_CRIPTO = '"+idCripto+"'";	
		
		ResultSet resul = stmt.executeQuery(sql);
		
		Stock stock = null;
		
		if (resul.next()) {
			double cantidad = resul.getDouble("cantidad");
			Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(idCripto);
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
		CriptomonedaDAO cmDAO = FactoryDAO.getCriptomonedaDAO();
		
		
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {
			int idCripto = resul.getInt("ID_CRIPTO");
			double cantidad = resul.getDouble("CANTIDAD");
			cm = cmDAO.buscarCriptomoneda(idCripto);
			cm = new Criptomoneda(cm.getNombre(), cm.getSigla(), FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(idCripto).getPrecioEnDolar(), FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(idCripto).getVolatilidad());
			stock = new Stock(cantidad, cm);
			listaStocks.add(stock);
		}
		resul.close();
		
		stmt.close();
		
		return listaStocks;
	}

	@Override
	public void sumarCantidadStock(int idCripto, double cantidad) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT CANTIDAD FROM STOCK WHERE ID_CRIPTO = '"+idCripto+"'";
		
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE STOCK SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE ID_CRIPTO = "+idCripto;
		
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	
	@Override
	public void cambiarCantidadStock(int idCripto, double cantidad) throws SQLException {
	
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "UPDATE STOCK SET CANTIDAD = "+cantidad+" WHERE ID_CRIPTO = "+idCripto;
		
		stmt.executeUpdate(sql);
		
		stmt.close();
	}
}
