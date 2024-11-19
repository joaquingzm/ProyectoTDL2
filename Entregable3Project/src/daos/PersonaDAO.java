package daos;

import java.sql.SQLException;

import modelos.Persona;

public interface PersonaDAO {
	
	void insertarPersona(Persona persona) throws SQLException;
	//Ver si es necesario
	//Persona buscarPersona(String nombres) throws SQLException;
	
	Persona buscarPersona(int id) throws SQLException;
	
	//Ver si es necesario
	//boolean existePersona(int id) throws SQLException;
}
