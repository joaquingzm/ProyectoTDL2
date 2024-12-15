package daos;

import java.util.LinkedList;
import java.util.List;


import java.sql.*;

import modelos.ActivoCripto;
import modelos.Criptomoneda;
import singletones.MyConnection;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {
	
	@Override
	public void insertarActivoCripto(ActivoCripto act, int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO,ID_CRIPTO,CANTIDAD,DIRECCION) VALUES ("
				+ idUsuario
				+ ","
				+ FactoryDAO.getCriptomonedaDAO().buscarCriptomonedaId(act.getCriptomoneda())
				+ ","
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
		CriptomonedaDAO cmDAO = FactoryDAO.getCriptomonedaDAO();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			int idCripto = resul.getInt("ID_CRIPTO");
			double cantidad = resul.getDouble("CANTIDAD");
			String direccion = resul.getString("DIRECCION");
			Criptomoneda cm = cmDAO.buscarCriptomoneda(idCripto);
			ActivoCripto a = new ActivoCripto(cantidad,direccion,cm);
			listaActivosCripto.add(a);

		}
		resul.close();
		
		stmt.close();
		
		return listaActivosCripto;
	}

	@Override
	public void sumarCantidadActivoCripto(int idCripto, int idUsuario, Double cantidad) throws SQLException {

		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT CANTIDAD FROM ACTIVO_CRIPTO WHERE ID_CRIPTO = '"+idCripto+"' AND ID_USUARIO = " + idUsuario;
	
		ResultSet resul = stmt.executeQuery(sql);
		
		sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE ID_CRIPTO = '"+idCripto+"' AND ID_USUARIO = " + idUsuario;
		
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	
	@Override
	public ActivoCripto buscarActivoCripto(int idCripto, int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		
		CriptomonedaDAO cmDAO = FactoryDAO.getCriptomonedaDAO();
			
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE ID_CRIPTO = '"+idCripto+"' AND ID_USUARIO = " + idUsuario;
		ActivoCripto ac = null;
		
		ResultSet resul = stmt.executeQuery(sql);
	
		if (resul.next()) {
			String direc = resul.getString("DIRECCION");
			double cant = resul.getDouble("CANTIDAD");
			Criptomoneda cm = cmDAO.buscarCriptomoneda(idCripto);
			ac = new ActivoCripto(cant, direc, cm);
		}
		resul.close();
		
		stmt.close();
		return ac;
	}
	
	@Override
	public List<ActivoCripto> listarActivosCripto(int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM ACTIVO_CRIPTO ac JOIN CRIPTOMONEDA c ON ac.ID_CRIPTO = c.ID WHERE ID_USUARIO = " + idUsuario;
		
		LinkedList<ActivoCripto> listaActivosCripto = new LinkedList<ActivoCripto>();
		
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {		
			double cantidad = resul.getDouble("CANTIDAD");
			String direccion = resul.getString("DIRECCION");
			Criptomoneda criptomoneda = new Criptomoneda(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getDouble("VOLATILIDAD"));
			ActivoCripto a = new ActivoCripto(cantidad, direccion, criptomoneda);
			listaActivosCripto.add(a);

		}
		resul.close();
		
		stmt.close();
		return listaActivosCripto;
	}

	@Override
	public boolean tieneActivoCripto(int idUsuario, int idCripto) throws SQLException {
	
		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "SELECT * FROM ACTIVO_CRIPTO WHERE ID_USUARIO = "+idUsuario+" AND ID_CRIPTO = '"+idCripto
				+"'";
	    		
		ResultSet resul = stmt.executeQuery(sql);
		
		if(resul.next()) {
			return true;
		}
		
		return false;
	}



}
