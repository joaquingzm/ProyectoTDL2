package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Persona;
import modelos.Usuario;
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
		
		llavesAutoGeneradas.close();
		stmt.close();
		return idUsuario;
	}

	@Override
	public int buscarId(String email, String contraseña) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM USUARIO WHERE EMAIL = '" + email + "' AND PASSWORD = '" + contraseña + "'";
		
		ResultSet resul = stmt.executeQuery(sql);
		int idUsuario = -1;
		
		if (resul.next()) {
			idUsuario = resul.getInt("ID");
		}
		
		resul.close();
		stmt.close();
		return idUsuario;
	}

	@Override
	public int getIdPersona(int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID_PERSONA FROM USUARIO WHERE ID ="+idUsuario;
		
		ResultSet resul = stmt.executeQuery(sql);
		int idPersona = -1;
		
		if (resul.next()) {
			idPersona = resul.getInt("ID");
		}
		
		resul.close();
		stmt.close();
		return idPersona;
	}

	@Override
	public boolean existeEmail(String email) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT EMAIL FROM USUARIO WHERE EMAIL='"+email+"'";
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if(resul.next()) {
			return true;
		}
		
		resul.close();
		stmt.close();
		return false;
	}

	@Override
	public Usuario buscarUsuario(int id) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM USUARIO WHERE ID="+id;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if(!resul.next()) {
			return null;
		}
		
		String email = resul.getString("EMAIL");
		String password = resul.getString("PASSWORD");
		boolean aceptaTerminos = resul.getBoolean("ACEPTA_TERMINOS");
		
		int idPersona = resul.getInt("ID_PERSONA");
		
		Persona persona = FactoryDAO.getPersonaDAO().buscarPersona(idPersona);
				
		Usuario usuario = new Usuario(persona, email, password, aceptaTerminos);
		
		return usuario;
	}

}
