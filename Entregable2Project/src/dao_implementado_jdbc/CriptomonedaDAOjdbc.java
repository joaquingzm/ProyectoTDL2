package dao_implementado_jdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;

import dao_interfaces.CriptomonedaDAO;
import modelos.Criptomoneda;
import singletones.MyStatement;

public class CriptomonedaDAOjdbc implements CriptomonedaDAO{

	@Override
	public void insertarCriptomoneda(Criptomoneda m) {
		try {
			Statement stmt = MyStatement.getStmt();
			String sql = "INSERT INTO CRIPTOMONEDA (sigla,cantidad) VALUES ("
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
	public List<Criptomoneda> listarCriptomonedas(Comparator<Criptomoneda> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarCriptomoneda(Criptomoneda m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCriptomoneda(Criptomoneda m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Criptomoneda buscarCriptomoneda(String sigla) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
