package daos;

import java.sql.SQLException;
import java.sql.Statement;
import modelos.Transaccion;
import singletones.MyConnection;

public class TransaccionDAOjdbc implements TransaccionDAO{

	@Override
	public void insertarTransaccion(Transaccion transaccion) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA) VALUES ('"
				+ transaccion.getResumen()
				+ "',"
				+ transaccion.getfechaYHora()
				+ ")";

		stmt.executeUpdate(sql);
		
		stmt.close();
		
	}

}
