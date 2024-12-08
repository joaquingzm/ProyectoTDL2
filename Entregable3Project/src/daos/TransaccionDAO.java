package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Transaccion;

public interface TransaccionDAO {
	
	public void insertarTransaccion (Transaccion transaccion) throws SQLException;
	
	public List<Transaccion> listarTransacciones () throws SQLException;
}
