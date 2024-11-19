package daos;

import java.sql.SQLException;

import modelos.Usuario;

public interface UsuarioDAO {
	
	void insertarUsuario(Usuario usuario) throws SQLException;
	
	void buscarUsuario(int id) throws SQLException;
}
