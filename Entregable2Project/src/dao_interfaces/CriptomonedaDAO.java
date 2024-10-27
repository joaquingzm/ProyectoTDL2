package dao_interfaces;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import modelos.Criptomoneda;

public interface CriptomonedaDAO {

	void insertarCriptomoneda(Criptomoneda m) throws SQLException;
	
	//Criptomoneda buscarCriptomoneda(String sigla) throws SQLException;
	
	List<Criptomoneda> listarCriptomonedas(Comparator<Criptomoneda> c) throws SQLException;
	
	//void actualizarCriptomoneda(Criptomoneda m) throws SQLException;
	
	//void eliminarCriptomoneda(Criptomoneda m) throws SQLException;
	
	Criptomoneda buscarCriptomoneda(String sigla) throws SQLException;
	
}
