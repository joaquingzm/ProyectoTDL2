package dao_implementado_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import dao_interfaces.ActivoMonedaFiduciariaDAO;
import dao_interfaces.MonedaFiduciariaDAO;
import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;
import modelos.Criptomoneda;
import modelos.FactoryDAO;
import modelos.MonedaFiduciaria;
import singletones.MyStatement;

public class ActivoMonedaFiduciariaDAOjdbc implements ActivoMonedaFiduciariaDAO{

	@Override
	public void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act) {
		try {
			Statement stmt = MyStatement.getStmt();
			String sql = "INSERT INTO ACTIVO_MONEDA_FIDUCIARIA (sigla,cantidad) VALUES ("
						+ act.getMonedaFIAT().getSigla()
						+ ","
						+ act.getCantidad() 
						+ ")";
		
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
	}

	@Override
	public List<ActivoMonedaFiduciaria> listarActivosCripto(Comparator<ActivoMonedaFiduciaria> c) {
		
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_MONEDA_FIDUCIARIA";
		LinkedList<ActivoMonedaFiduciaria> listaActivosMonedaFiduciaria = new LinkedList<ActivoMonedaFiduciaria>();
		MonedaFiduciariaDAO mf = FactoryDAO.getMonedaFiduciariaDAO();
		
		try {
		
			ResultSet resul = stmt.executeQuery(sql);
			
			while(resul.next()) {		
				/* CAMBIAR, TENGO QUE VER DE HACER ALGUN METODO EN
				 * MonedaDAOjdbc, que sea algo asi como seleccionar
				 * moneda segun la nomenclatura, o debería utilizar
				 * la FOREIGN KEY de criptomoneda que tiene el ActivoCripto? 
				 */
				
				String sigla = resul.getString("sigla");
				MonedaFiduciaria monedaFiduciaria = mf.buscarMonedaFiduciaria(sigla);
				
				double cantidad = resul.getDouble("cantidad");
				
				ActivoMonedaFiduciaria a = new ActivoMonedaFiduciaria(cantidad, monedaFiduciaria);
				
				listaActivosMonedaFiduciaria.add(a);
				
			}
		
		}
		catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		
		listaActivosMonedaFiduciaria.sort(c);
		
		return listaActivosMonedaFiduciaria;
	}

}
