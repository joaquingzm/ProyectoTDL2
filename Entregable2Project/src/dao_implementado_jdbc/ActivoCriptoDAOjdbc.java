package dao_implementado_jdbc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.sql.*;
import dao_interfaces.ActivoCriptoDAO;
import dao_interfaces.CriptomonedaDAO;
import modelos.ActivoCripto;
import modelos.Criptomoneda;
import modelos.FactoryDAO;
import singletones.MyStatement;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO {
	@Override
	public void insertarActivoCripto(ActivoCripto act) throws SQLException{

		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO ACTIVO_CRIPTO (sigla,cantidad,direccion) VALUES ("
				+ act.getCriptomoneda().getSigla()
				+ ","
				+ act.getCantidad() 
				+ ","
				+ act.getDireccion()
				+ ")";

		stmt.executeUpdate(sql);



	}

	@Override
	public List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c) throws SQLException {

		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_CRIPTO";
		LinkedList<ActivoCripto> listaActivosCripto = new LinkedList<ActivoCripto>();
		CriptomonedaDAO cm = FactoryDAO.getCriptomonedaDAO();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {		
			String sigla = resul.getString("sigla");
			Criptomoneda cripto = cm.buscarCriptomoneda(sigla);

			double cantidad = resul.getDouble("cantidad");
			String direccion = resul.getString("direccion");

			ActivoCripto a = new ActivoCripto(cantidad,direccion,cripto);
			listaActivosCripto.add(a);

		}
		listaActivosCripto.sort(c);
		return listaActivosCripto;
	}

}
