package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modelos.Criptomoneda;
import singletones.MyStatement;

public class CriptomonedaDAOjdbc implements CriptomonedaDAO{

	@Override
	public void insertarCriptomoneda(Criptomoneda cm) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO CRIPTOMONEDA (NOMBRE,SIGLA,PRECIO_EN_DOLAR,VOLATILIDAD) VALUES ('"
				+ cm.getNombre()
				+ "','"
				+ cm.getSigla()
				+ "',"
				+ cm.getPrecioEnDolar()
				+ ","
				+ cm.getVolatilidad()
				+ ")";

		stmt.executeUpdate(sql);
	}

	@Override
	public List<Criptomoneda> listarCriptomonedas(Comparator<Criptomoneda> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM CRIPTOMONEDA";
		LinkedList<Criptomoneda> listaCriptomonedas = new LinkedList<Criptomoneda>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			Criptomoneda cm = new Criptomoneda(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getDouble("VOLATILIDAD"));
			listaCriptomonedas.add(cm);
		}
		
		listaCriptomonedas.sort(c);
		return listaCriptomonedas;
	}

	/*
	@Override
	public void actualizarCriptomoneda(Criptomoneda m) throws SQLException{
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarCriptomoneda(Criptomoneda m) throws SQLException{
		// TODO Auto-generated method stub

	}
	 */

	@Override
	public Criptomoneda buscarCriptomoneda(String sigla) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT * FROM CRIPTOMONEDA WHERE SIGLA = '"+sigla+"'";
		Criptomoneda cm = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			cm = new Criptomoneda(resul.getString("NOMBRE"),resul.getString("SIGLA"),resul.getDouble("PRECIO_EN_DOLAR"),resul.getDouble("VOLATILIDAD"));
		}
		
		return cm;
	}


}
