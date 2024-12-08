package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import singletones.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO {

	@Override
	public int insertarUsuario(int IdPersona, String email, String contraseña, boolean terminosYCondiciones) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES ('"
				+ IdPersona
				+ "','"
				+ email
				+ "','"
				+ contraseña
				+ "',"
				+ terminosYCondiciones
				+ ")";

		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet llavesAutoGeneradas = stmt.getGeneratedKeys();
		int idUsuario = -1;
		
		if (llavesAutoGeneradas.next()) {
			idUsuario = llavesAutoGeneradas.getInt("ID");
		}
		
		stmt.close();
		return idUsuario;
	}

	@Override
	public int buscarId(String email, String contraseña) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM USUARIO WHERE EMAIL = '" + email + "' AND CONTRASEÑA = '" + contraseña + "'";
		
		ResultSet resul = stmt.executeQuery(sql);
		int idUsuario = -1;
		
		if (resul.next()) {
			idUsuario = resul.getInt("ID");
		}
		
		stmt.close();
		return idUsuario;
	}

}
