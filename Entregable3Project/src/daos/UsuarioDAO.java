package daos;

import java.sql.SQLException;

import modelos.Usuario;


public interface UsuarioDAO {
	
	int insertarUsuario(Usuario usuario) throws SQLException;
	
	int buscarId(Usuario usuario) throws SQLException;

	boolean existeEmail(String email) throws SQLException;
	
	Usuario buscarUsuario(int id) throws SQLException;
}
