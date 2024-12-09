package daos;

import java.sql.SQLException;

public interface UsuarioDAO {
	
	int insertarUsuario(int IdPersona, String email, String contraseña, boolean terminosYCondiciones) throws SQLException;
	
	int buscarId(String email, String contraseña) throws SQLException;

	int getIdPersona(int idUsuario) throws SQLException;

	boolean existeEmail(String email) throws SQLException;
}
