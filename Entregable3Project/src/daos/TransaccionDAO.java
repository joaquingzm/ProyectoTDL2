package daos;

import java.sql.SQLException;
import modelos.Transaccion;

public interface TransaccionDAO {
	
	public void insertarTransaccion (Transaccion transaccion) throws SQLException;
}
