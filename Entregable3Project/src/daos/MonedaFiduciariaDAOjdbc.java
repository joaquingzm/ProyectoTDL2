package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import modelos.MonedaFiduciaria;
import singletones.MyConnection;

public class MonedaFiduciariaDAOjdbc implements MonedaFiduciariaDAO {

	@Override
	public void insertarMonedaFiduciaria(MonedaFiduciaria mf) throws SQLException{
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO MONEDA_FIDUCIARIA (NOMBRE,SIGLA,PRECIO_EN_DOLAR,PAIS_EMISOR) VALUES ('"
				+ mf.getNombre()
				+ "','"
				+ mf.getSigla()
				+ "',"
				+ mf.getPrecioEnDolar()
				+ ",'"
				+ mf.getPaisEmisor()
				+ "')";

		stmt.executeUpdate(sql);
		
		stmt.close();
	}

	@Override
	public List<MonedaFiduciaria> listarMonedasFiduciarias() throws SQLException{
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM MONEDA_FIDUCIARIA";
		LinkedList<MonedaFiduciaria> listaMonedaFiduciarias = new LinkedList<MonedaFiduciaria>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			MonedaFiduciaria mf = new MonedaFiduciaria(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getString("PAIS_EMISOR"));
			listaMonedaFiduciarias.add(mf);
		}
		resul.close();
		
		stmt.close();
		return listaMonedaFiduciarias;
	}
	
	@Override
	public MonedaFiduciaria buscarMonedaFiduciaria(String sigla) throws SQLException{
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM MONEDA_FIDUCIARIA WHERE SIGLA = '"+sigla+"'";
		MonedaFiduciaria mf = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			mf = new MonedaFiduciaria(resul.getString("NOMBRE"),resul.getString("SIGLA"),resul.getDouble("PRECIO_EN_DOLAR"),resul.getString("PAIS_EMISOR"));
		}
		
		resul.close();
		stmt.close();
		return mf;
	}

	@Override
	public boolean estaVacia() throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM MONEDA_FIDUCIARIA";
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
