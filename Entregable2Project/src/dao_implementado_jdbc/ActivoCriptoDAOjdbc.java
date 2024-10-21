package dao_implementado_jdbc;

import java.util.Comparator;
import java.util.List;
import java.sql.*;
import dao_interfaces.ActivoCriptoDAO;
import modelos.ActivoCripto;
import singletones.MyConnection;
import singletones.MyStatement;

public class ActivoCriptoDAOjdbc implements ActivoCriptoDAO{

	@Override
	public void insertarActivoCripto(ActivoCripto act) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c) {
		Statement stmt = MyStatement.getStmt();
		String sql = " SELECT * FROM ACTIVO_CRIPTO";
		try {
			ResultSet resul = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
