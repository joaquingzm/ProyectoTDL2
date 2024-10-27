package dao_implementado_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dao_interfaces.CriptomonedaDAO;
import modelos.Criptomoneda;
import singletones.MyStatement;

public class CriptomonedaDAOjdbc implements CriptomonedaDAO{

	@Override
	public void insertarCriptomoneda(Criptomoneda cm) throws SQLException{
		try {
			Statement stmt = MyStatement.getStmt();
			String sql = "INSERT CRIPTOMONEDA (nombre,sigla,precioEnDolar,volatilidad) VALUES ("
						+ cm.getNombre()
						+ ","
						+ cm.getSigla()
						+ ","
						+ cm.getPrecioEnDolar()
						+ ","
						+ cm.getVolatilidad()
						+ ")";
		
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	@Override
	public List<Criptomoneda> listarCriptomonedas(Comparator<Criptomoneda> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM CRIPTOMONEDA";
		LinkedList<Criptomoneda> listaCriptomonedas = new LinkedList<Criptomoneda>();
		
		try {
		
			ResultSet resul = stmt.executeQuery(sql);
			
			while(resul.next()) {		
				Criptomoneda cm = new Criptomoneda(resul.getString("nombre"), resul.getString("sigla"), resul.getDouble("precioEnDolar"), resul.getDouble("volatilidad"));
				listaCriptomonedas.add(cm);
			}
		
		}
		catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
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
		
		return null;
	}

	
}
