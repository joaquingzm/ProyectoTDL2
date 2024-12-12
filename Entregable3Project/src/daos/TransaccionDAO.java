package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Transaccion;

public interface TransaccionDAO {
	
	public void insertarTransaccion (Transaccion transaccion, int idUsuario) throws SQLException;
	
	public List<Transaccion> listarTransacciones (int idUsuario) throws SQLException;
}
