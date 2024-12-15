package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Persona;
import modelos.Usuario;
import singletones.MyConnection;

public class UsuarioDAOjdbc implements UsuarioDAO {

	@Override
	public int insertarUsuario(Usuario usuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES ('"
				+ FactoryDAO.getPersonaDAO().buscarId(usuario.getPersona())
				+ "','"
				+ usuario.getEmail()
				+ "','"
				+ usuario.getPassword()
				+ "',"
				+ usuario.getAcepta_terminos()
				+ ")";

		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet llavesAutoGeneradas = stmt.getGeneratedKeys();
		int idUsuario = -1;
		
		if (llavesAutoGeneradas.next()) {
			idUsuario = llavesAutoGeneradas.getInt(1);
			System.out.println(idUsuario);
		}
		
		llavesAutoGeneradas.close();
		stmt.close();
		return idUsuario;
	}

	@Override
	public int buscarId(Usuario usuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM USUARIO WHERE EMAIL = '" + usuario.getEmail() + "' AND PASSWORD = '" + usuario.getPassword() + "'";
		
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
	public boolean existeEmail(String email) throws SQLException{
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT EMAIL FROM USUARIO WHERE EMAIL = '" + email + "'";
		
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
		String sql = "SELECT * FROM USUARIO WHERE ID = " + id;
		
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
		
		resul.close();
		stmt.close();
		
		return usuario;
	}

}
