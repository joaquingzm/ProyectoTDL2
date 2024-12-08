package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import modelos.ActivoMonedaFiduciaria;
import modelos.MonedaFiduciaria;
import singletones.MyConnection;

public class ActivoMonedaFiduciariaDAOjdbc implements ActivoMonedaFiduciariaDAO{

	@Override
	public void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act) throws SQLException{

		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO ACTIVO_MONEDA_FIDUCIARIA (SIGLA,CANTIDAD) VALUES ('"
				+ act.getMonedaFIAT().getSigla()
				+ "',"
				+ act.getCantidad() 
				+ ")";

		stmt.executeUpdate(sql);

		stmt.close();
	}

	@Override
	public List<ActivoMonedaFiduciaria> listarActivosFiduciarios() throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
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
		
		stmt.close();
		return listaActivosMonedaFiduciaria;
	}
	
	@Override
	public void sumarCantidadActivoFiduciaria(String sigla, Double cantidad) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "SELECT CANTIDAD FROM ACTIVO_MONEDA_FIDUCIARIA WHERE SIGLA = '"+sigla+"'";
		ResultSet resul = stmt.executeQuery(sql);
		sql = "UPDATE ACTIVO_MONEDA_FIDUCIARIA SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE SIGLA = '"+sigla+"'";
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	@Override
	public ActivoMonedaFiduciaria buscarActivoMonedaFiduciaria(String sigla) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA WHERE SIGLA = '"+sigla+"'";
		ActivoMonedaFiduciaria amf = null;
		double cant;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			cant = resul.getDouble("CANTIDAD");
			MonedaFiduciaria mf = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(sigla);
			amf = new ActivoMonedaFiduciaria(cant, mf);
		}
		resul.close();
		
		stmt.close();
		
		return amf;
	}

	@Override
	public List<ActivoMonedaFiduciaria> listarActivosFiduciarios(int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA amf JOIN MONEDA_FIDUCIARIA mf ON amf.ID_FIAT = mf.ID WHERE ID_USUARIO = " + idUsuario;
		LinkedList<ActivoMonedaFiduciaria> listaActivosMonedaFiduciaria = new LinkedList<ActivoMonedaFiduciaria>();
		
		ResultSet resul = stmt.executeQuery(sql);
		
		while(resul.next()) {		
			double cantidad = resul.getDouble("CANTIDAD");
			MonedaFiduciaria monedaFiduciaria = new MonedaFiduciaria(resul.getString("NOMBRE"), resul.getString("SIGLA"), resul.getDouble("PRECIO_EN_DOLAR"), resul.getString("PAIS_EMISOR"));
			ActivoMonedaFiduciaria a = new ActivoMonedaFiduciaria(cantidad, monedaFiduciaria);
			listaActivosMonedaFiduciaria.add(a);

		}
		resul.close();
		
		stmt.close();
		return listaActivosMonedaFiduciaria;
	}


}
