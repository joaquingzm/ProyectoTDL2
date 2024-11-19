package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import modelos.Persona;
import singletones.MyConnection;


public class PersonaDAOjdbc implements PersonaDAO{

	@Override
	public void insertarPersona(Persona persona) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO PERSONA (NOMBRES,APELLIDOS) VALUES ('"
				+ persona.getNombres()
				+ "','"
				+ persona.getApellidos()
				+ ")";

		stmt.executeUpdate(sql);
	}

	@Override
	public Persona buscarPersona(int id) throws SQLException {
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM PERSONA WHERE ID = '"+id+"'";
		Persona persona = null;
		String nombres,apellidos = null;
		
		ResultSet resul = stmt.executeQuery(sql);
		if (resul.next()) {
			nombres = resul.getString("NOMBRES");
			apellidos = resul.getString("APELLIDOS");
			persona = new Persona(nombres, apellidos);
		}
		
		return persona;
	}

}
