package dao_implementado_jdbc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import dao_interfaces.ActivoCriptoDAO;
import modelos.ActivoCripto;
import modelos.Criptomoneda;
import singletones.MyStatement;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {

	/* deberia devolver algo para indicar que algo salió bien o mal?
	 * o una SQLException ya supone "tirar todo a la basura" y la excepcion
	 * corta el programa?
	 */
	@Override
	public void insertarActivoCripto(ActivoCripto act) throws SQLException{

		try {
			Statement stmt = MyStatement.getStmt();
			String sql = "INSERT INTO ACTIVO_CRIPTO (sigla,cantidad,direccion) VALUES ("
						+ act.getCriptomoneda().getSigla()
						+ ","
						+ act.getCantidad() 
						+ ","
						+ act.getDireccion()
						+ ")";
		
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
	}

	@Override
	public List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c) throws SQLException {
		
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_CRIPTO";
		LinkedList<ActivoCripto> listaActivosCripto = new LinkedList<ActivoCripto>();
		
		try {
		
			ResultSet resul = stmt.executeQuery(sql);
			
			while(resul.next()) {		
				/* CAMBIAR, TENGO QUE VER DE HACER ALGUN METODO EN
				 * MonedaDAOjdbc, que sea algo asi como seleccionar
				 * moneda segun la nomenclatura, o debería utilizar
				 * la FOREIGN KEY de criptomoneda que tiene el ActivoCripto? 
				 */
				Criptomoneda cripto = new Criptomoneda();
				double cantidad = resul.getDouble("cantidad");
				String direccion = resul.getString("direccion");
				
				ActivoCripto a = new ActivoCripto(cantidad,direccion,cripto);
				
				listaActivosCripto.add(a);
				
			}
		
		}
		catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		
		listaActivosCripto.sort(c);
		
		return listaActivosCripto;
	}

}
