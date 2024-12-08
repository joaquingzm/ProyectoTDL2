package daos;

import java.sql.SQLException;

import modelos.Persona;

public interface PersonaDAO {
	
	int insertarPersona(Persona persona) throws SQLException;
	//Ver si es necesario
	//Persona buscarPersona(String nombres) throws SQLException;
	
	Persona buscarPersona(int id) throws SQLException;
	
	int buscarId (String nombre, String apellido) throws SQLException;
	
	//Ver si es necesario
	//boolean existePersona(int id) throws SQLException;
}
