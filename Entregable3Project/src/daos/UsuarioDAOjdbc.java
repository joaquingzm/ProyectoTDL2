package daos;

import java.sql.SQLException;
import java.sql.Statement;

import modelos.Usuario;
import singletones.MyStatement;

public class UsuarioDAOjdbc implements UsuarioDAO{

	@Override
	public void insertarUsuario(Usuario usuario) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO USUARIO (NOMBRE,SIGLA,PRECIO_EN_DOLAR,VOLATILIDAD) VALUES ('"
				+ usuario.getNombre()
				+ "','"
				+ cm.getSigla()
				+ "',"
				+ cm.getPrecioEnDolar()
				+ ","
				+ cm.getVolatilidad()
				+ ")";

		stmt.executeUpdate(sql);
		
	}

	@Override
	public void buscarUsuario(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
