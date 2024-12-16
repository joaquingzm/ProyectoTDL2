package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import controlador.GestorDeDatosDelControlador;
import modelos.ActivoMonedaFiduciaria;
import modelos.MonedaFiduciaria;
import singletones.MyConnection;

public class ActivoMonedaFiduciariaDAOjdbc implements ActivoMonedaFiduciariaDAO{

	@Override
	public void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act, int idUsuario) throws SQLException{

		Statement stmt = MyConnection.getCon().createStatement();
		
		int idFIAT = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciariaId(act.getMonedaFIAT());
		
		String sql = "INSERT INTO ACTIVO_MONEDA_FIDUCIARIA (ID_FIAT, ID_USUARIO, CANTIDAD) VALUES ("
				+ idFIAT
				+ ","
				+ idUsuario
				+ ","
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
			int idFIAT = resul.getInt("ID_FIAT");
			double cantidad = resul.getDouble("CANTIDAD");
			MonedaFiduciaria monedaFiduciaria = mfDAO.buscarMonedaFiduciaria(idFIAT);
			ActivoMonedaFiduciaria a = new ActivoMonedaFiduciaria(cantidad, monedaFiduciaria);
			listaActivosMonedaFiduciaria.add(a);

		}
		resul.close();
		
		stmt.close();
		
		return listaActivosMonedaFiduciaria;
	}
	
	@Override
	public void sumarCantidadActivoFiduciaria(int idFIAT, int idUsuario, Double cantidad) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT CANTIDAD FROM ACTIVO_MONEDA_FIDUCIARIA WHERE ID_FIAT = '"+idFIAT+"' AND ID_USUARIO = " + idUsuario;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		sql = "UPDATE ACTIVO_MONEDA_FIDUCIARIA SET CANTIDAD = "+(resul.getDouble("CANTIDAD")+cantidad)+" WHERE ID_FIAT = '"+idFIAT+"' AND ID_USUARIO = " + idUsuario;
	
		stmt.executeUpdate(sql);
		
		resul.close();
		stmt.close();
	}
	
	@Override
	public void cambiarCantidadActivoMonedaFiduciaria(int idFIAT, int idUsuario, double cantidad) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "UPDATE ACTIVO_MONEDA_FIDUCIARIA SET CANTIDAD = "+cantidad+" WHERE ID_FIAT = '"+idFIAT+"' AND ID_USUARIO = " + idUsuario;

		stmt.executeUpdate(sql);
		
		stmt.close();
	}
	
	@Override
	public ActivoMonedaFiduciaria buscarActivoMonedaFiduciaria(int idFIAT, int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA WHERE ID_FIAT = '"+idFIAT+"' AND ID_USUARIO = " + idUsuario;
		
		ActivoMonedaFiduciaria amf = null;
		double cant;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			cant = resul.getDouble("CANTIDAD");
			MonedaFiduciaria mf = FactoryDAO.getMonedaFiduciariaDAO().buscarMonedaFiduciaria(idFIAT);
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

	public boolean tieneActivoMonedaFiduciaria(int idUsuario, int idFIAT) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		
		String sql = "SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA WHERE ID_USUARIO = "+idUsuario+" AND ID_FIAT = '"+idFIAT+"'";
	    		
		ResultSet resul = stmt.executeQuery(sql);
		
		if(resul.next()) {
			return true;
		}
		
		return false;
	}

}
