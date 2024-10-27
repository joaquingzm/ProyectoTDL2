package dao_implementado_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dao_interfaces.MonedaFiduciariaDAO;
import modelos.MonedaFiduciaria;
import singletones.MyStatement;

public class MonedaFiduciariaDAOjdbc implements MonedaFiduciariaDAO {

	@Override
	public void insertarMonedaFiduciaria(MonedaFiduciaria mf) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO MONEDA_FIDUCIARIA (nombre,sigla,precioEnDolar,paisEmisor) VALUES ("
				+ mf.getNombre()
				+ ","
				+ mf.getSigla()
				+ ","
				+ mf.getPrecioEnDolar()
				+ ","
				+ mf.getPaisEmisor()
				+ ")";

		stmt.executeUpdate(sql);
	}

	@Override
	public List<MonedaFiduciaria> listarMonedasFiduciarias(Comparator<MonedaFiduciaria> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM MONEDA_FIDUCIARIA";
		LinkedList<MonedaFiduciaria> listaMonedaFiduciarias = new LinkedList<MonedaFiduciaria>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			MonedaFiduciaria mf = new MonedaFiduciaria(resul.getString("nombre"), resul.getString("sigla"), resul.getDouble("precioEnDolar"), resul.getString("paisEmisor"));
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
		String sql = "SELECT * WHERE sigla = '"+sigla+"'";
		MonedaFiduciaria mf = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			mf = new MonedaFiduciaria(resul.getString("nombre"),resul.getString("sigla"),resul.getDouble("precioEnDolar"),resul.getString("paisEmisor"));
		}
		return mf;
	}

}
