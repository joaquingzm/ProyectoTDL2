package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modelos.MonedaFiduciaria;
import singletones.MyStatement;

public class MonedaFiduciariaDAOjdbc implements MonedaFiduciariaDAO {

	@Override
	public void insertarMonedaFiduciaria(MonedaFiduciaria mf) throws SQLException{
		Statement stmt = MyStatement.getStmt();
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
	}

	@Override
	public List<MonedaFiduciaria> listarMonedasFiduciarias(Comparator<MonedaFiduciaria> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM MONEDA_FIDUCIARIA";
		LinkedList<MonedaFiduciaria> listaMonedaFiduciarias = new LinkedList<MonedaFiduciaria>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			MonedaFiduciaria mf = new MonedaFiduciaria(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getString("PAIS_EMISOR"));
			listaMonedaFiduciarias.add(mf);
		}
		
		listaMonedaFiduciarias.sort(c);
		return listaMonedaFiduciarias;
	}
/*
	@Override
	public void actualizarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException{
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public MonedaFiduciaria buscarMonedaFiduciaria(String sigla) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT * WHERE SIGLA = '"+sigla+"'";
		MonedaFiduciaria mf = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			mf = new MonedaFiduciaria(resul.getString("NOMBRE"),resul.getString("SIGLA"),resul.getDouble("PRECIO_EN_DOLAR"),resul.getString("PAIS_EMISOR"));
		}
		return mf;
	}

}
