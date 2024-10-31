package daos;

import java.sql.SQLException;
import java.sql.Statement;
import modelos.Transaccion;
import singletones.MyStatement;

public class TransaccionDAOjdbc implements TransaccionDAO{

	@Override
	public void insertarTransaccion(Transaccion transaccion) throws SQLException {
		Statement stmt = MyStatement.getStmt();
		String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA) VALUES ('"
				+ transaccion.getResumen()
				+ "',"
				+ transaccion.getFechaHora()
				+ ")";

		stmt.executeUpdate(sql);
		
	}

}
