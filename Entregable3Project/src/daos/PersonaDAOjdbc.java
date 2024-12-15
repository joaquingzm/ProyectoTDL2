package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import modelos.Persona;
import singletones.MyConnection;


public class PersonaDAOjdbc implements PersonaDAO{

	@Override
	public int insertarPersona(Persona persona) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "INSERT INTO PERSONA (NOMBRE,APELLIDO) VALUES ('"
				+ persona.getNombre()
				+ "','"
				+ persona.getApellido()
				+ "')";

		stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet llavesAutoGeneradas = stmt.getGeneratedKeys();
		int idPersona = -1;
		
		if (llavesAutoGeneradas.next()) {
			idPersona = llavesAutoGeneradas.getInt(1);
		}
		
		llavesAutoGeneradas.close();
		
		stmt.close();
		return idPersona;
	}

	@Override
	public Persona buscarPersona(int idPersona) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT * FROM PERSONA WHERE ID = " + idPersona;
		
		Persona persona = null;
		String nombre,apellido;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			nombre = resul.getString("NOMBRE");
			apellido = resul.getString("APELLIDO");
			persona = new Persona(nombre, apellido);
		}
		resul.close();
		
		stmt.close();
		return persona;
	}
	
	@Override
	public int buscarId(Persona persona) throws SQLException {
		
		Statement stmt = MyConnection.getCon().createStatement();
		String sql = "SELECT ID FROM PERSONA WHERE NOMBRE = '" + persona.getNombre() + "' AND APELLIDO = '" + persona.getApellido() + "'";
		//Se supone que no pueden existir dos o mas personas con el mismo nombre y apellido
		
		int idUsuario = -1;
		
		ResultSet resul = stmt.executeQuery(sql);
		
		if (resul.next()) {
			idUsuario = resul.getInt("ID");
		}
		
		resul.close();
		
		stmt.close();
		return idUsuario;
	}

}
