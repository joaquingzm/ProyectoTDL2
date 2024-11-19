package daos;

import java.util.LinkedList;
import java.util.List;
import java.sql.*;

import modelos.ActivoCripto;
import modelos.Criptomoneda;
import singletones.MyConnection;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {
	@Override
	public void insertarActivoCripto(ActivoCripto act) throws SQLException{

		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "INSERT INTO ACTIVO_CRIPTO (SIGLA,CANTIDAD,DIRECCION) VALUES ('"
				+ act.getCriptomoneda().getSigla()
				+ "',"
				+ act.getCantidad() 
				+ ",'"
				+ act.getDireccion()
				+ "')";

		stmt.executeUpdate(sql);
		
		stmt.close();
	}

	@Override
	public List<ActivoCripto> listarActivosCripto() throws SQLException {

		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM ACTIVO_CRIPTO";
		LinkedList<ActivoCripto> listaActivosCripto = new LinkedList<ActivoCripto>();
		CriptomonedaDAO cm = FactoryDAO.getCriptomonedaDAO();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			String sigla = resul.getString("SIGLA");
			double cantidad = resul.getDouble("CANTIDAD");
			String direccion = resul.getString("DIRECCION");
			Criptomoneda cripto = cm.buscarCriptomoneda(sigla);
			ActivoCripto a = new ActivoCripto(cantidad,direccion,cripto);
			listaActivosCripto.add(a);

		}
		resul.close();
		
		stmt.close();
		
		return listaActivosCripto;
	}

	@Override
	public void sumarCantidadActivoCripto(String sigla, Double cantidad) throws SQLException {

		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE SIGLA = '"+sigla+"'";
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	
	@Override
	public ActivoCripto buscarActivoCripto(String sigla) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE SIGLA = '"+sigla+"'";
		ActivoCripto ac = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			String direc = resul.getString("DIRECCION");
			double cant = resul.getDouble("CANTIDAD");
			Criptomoneda cm = FactoryDAO.getCriptomonedaDAO().buscarCriptomoneda(sigla);
			ac = new ActivoCripto(cant, direc, cm);
		}
		resul.close();
		
		stmt.close();
		return ac;
	}

}
