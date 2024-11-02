package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modelos.ActivoMonedaFiduciaria;
import modelos.MonedaFiduciaria;
import singletones.MyStatement;

public class ActivoMonedaFiduciariaDAOjdbc implements ActivoMonedaFiduciariaDAO{

	@Override
	public void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act) throws SQLException{

		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO ACTIVO_MONEDA_FIDUCIARIA (SIGLA,CANTIDAD) VALUES ('"
				+ act.getMonedaFIAT().getSigla()
				+ "',"
				+ act.getCantidad() 
				+ ")";

		stmt.executeUpdate(sql);


	}

	@Override
	public List<ActivoMonedaFiduciaria> listarActivosFiduciarios(Comparator<ActivoMonedaFiduciaria> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA";
		LinkedList<ActivoMonedaFiduciaria> listaActivosMonedaFiduciaria = new LinkedList<ActivoMonedaFiduciaria>();
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();
		
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {		
			String sigla = resul.getString("SIGLA");
			double cantidad = resul.getDouble("CANTIDAD");
			MonedaFiduciaria monedaFiduciaria = mfDAO.buscarMonedaFiduciaria(sigla);
			ActivoMonedaFiduciaria a = new ActivoMonedaFiduciaria(cantidad, monedaFiduciaria);
			listaActivosMonedaFiduciaria.add(a);

		}
		resul.close();
		listaActivosMonedaFiduciaria.sort(c);
		return listaActivosMonedaFiduciaria;
	}

	@Override
	public void sumarCantidadActivoFiduciaria(String sigla, Double cantidad) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT CANTIDAD FROM ACTIVO_MONEDA_FIDUCIARIA WHERE SIGLA = '"+sigla+"'";
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE ACTIVO_MONEDA_FIDUCIARIA SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
	}
	@Override
	public ActivoMonedaFiduciaria buscarActivoMonedaFiduciaria(String sigla) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA WHERE SIGLA = '"+sigla+"'";
		ActivoMonedaFiduciaria amf = null;
		double cant;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			cant = resul.getDouble("CANTIDAD");
			MonedaFiduciaria mf = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla);
			amf = new ActivoMonedaFiduciaria(cant, mf);
		}
		
		return amf;
	}


}
