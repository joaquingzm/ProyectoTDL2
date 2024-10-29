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
	public List<ActivoMonedaFiduciaria> listarActivosCripto(Comparator<ActivoMonedaFiduciaria> c) throws SQLException{
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA";
		LinkedList<ActivoMonedaFiduciaria> listaActivosMonedaFiduciaria = new LinkedList<ActivoMonedaFiduciaria>();
		MonedaFiduciariaDAO mfDAO = FactoryDAO.getMonedaFiduciariaDAO();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			String sigla = resul.getString("SIGLA");
			MonedaFiduciaria monedaFiduciaria = mfDAO.buscarMonedaFiduciaria(sigla);
			double cantidad = resul.getDouble("CANTIDAD");
			ActivoMonedaFiduciaria a = new ActivoMonedaFiduciaria(cantidad, monedaFiduciaria);
			listaActivosMonedaFiduciaria.add(a);

		}

		listaActivosMonedaFiduciaria.sort(c);
		return listaActivosMonedaFiduciaria;
	}

}
