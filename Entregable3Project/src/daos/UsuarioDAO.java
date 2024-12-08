package daos;

import java.sql.SQLException;

import modelos.Usuario;

public interface UsuarioDAO {
	
	int insertarUsuario(int IdPersona, String email, String contraseña, boolean terminosYCondiciones) throws SQLException;
	
	int buscarId(String email, String contraseña) throws SQLException;
}
