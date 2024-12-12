package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import modelos.Transaccion;
import singletones.MyConnection;

public class TransaccionDAOjdbc implements TransaccionDAO{

	@Override
	public void insertarTransaccion(Transaccion transaccion, int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO TRANSACCION (RESUMEN,FECHA_HORA,ID_USUARIO) VALUES ('"
				+ transaccion.getResumen()
				+ "','"
				+ transaccion.getfechaYHora().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
				+ "',"
				+ idUsuario
				+ ")";

		stmt.executeUpdate(sql);
		
		stmt.close();
		
	}

	@Override
	public List<Transaccion> listarTransacciones(int idUsuario) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = " SELECT * FROM TRANSACCION WHERE ID_USUARIO = " + idUsuario;
		
		LinkedList<Transaccion> listaTransacciones = new LinkedList<Transaccion>();

		ResultSet resul = stmt.executeQuery(sql);

		while(resul.next()) {	
			LocalDateTime ldt = LocalDateTime.parse(resul.getString("FECHA_HORA"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			Transaccion t = new Transaccion(resul.getString("RESUMEN"), ldt);
			listaTransacciones.add(t);
		}
		resul.close();
		
		stmt.close();
		return listaTransacciones;
	}

}
