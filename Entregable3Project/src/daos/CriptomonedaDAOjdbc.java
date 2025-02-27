package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import modelos.Criptomoneda;
import singletones.MyConnection;


public class CriptomonedaDAOjdbc implements CriptomonedaDAO{

	@Override
	public void insertarCriptomoneda(Criptomoneda cm) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
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
		
		stmt.close();
	}

	@Override
	public List<Criptomoneda> listarCriptomonedas() throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM CRIPTOMONEDA";
		
		LinkedList<Criptomoneda> listaCriptomonedas = new LinkedList<Criptomoneda>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			Criptomoneda cm = new Criptomoneda(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getDouble("VOLATILIDAD"));
			listaCriptomonedas.add(cm);
		}
		
		resul.close();
		
		stmt.close();
		
		return listaCriptomonedas;
	}

	
	@Override
	public Criptomoneda buscarCriptomoneda(int idCripto) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM CRIPTOMONEDA WHERE ID = "+idCripto;
		
		Criptomoneda cm = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			cm = new Criptomoneda(resul.getString("NOMBRE"),resul.getString("SIGLA"),resul.getDouble("PRECIO_EN_DOLAR"),resul.getDouble("VOLATILIDAD"));
		}
		resul.close();
		
		stmt.close();
		
		return cm;
	}
	
	@Override
	public int buscarCriptomonedaId(Criptomoneda cm) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM CRIPTOMONEDA WHERE SIGLA = '"+cm.getSigla()+"'";
		
		int id = -1;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			id = resul.getInt("ID");
		}
		resul.close();
		
		stmt.close();
		
		return id;
	}
	
	@Override
	public int buscarCriptomonedaId(String sigla) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM CRIPTOMONEDA WHERE SIGLA = '" + sigla + "'";
		
		int id = -1;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			id = resul.getInt("ID");
		}
		resul.close();
		
		stmt.close();
		
		return id;
	}

	@Override
	public void actualizarPrecioEnDolar(int idCripto, double precioEnDolar) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "UPDATE CRIPTOMONEDA SET PRECIO_EN_DOLAR = "+ precioEnDolar +" WHERE ID = "+idCripto;
	
		stmt.executeUpdate(sql);
		
		stmt.close();
		
	}
	
	@Override
	public boolean estaVacia() throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
	
		String sql = "SELECT * FROM CRIPTOMONEDA";
       
		ResultSet resul = stmt.executeQuery(sql);
        
        if (resul.next()) {
        	
        	resul.close();
        	stmt.close();
        	return false;
        }
        
    	resul.close();
    	stmt.close();
        return true;
        
	}




}
