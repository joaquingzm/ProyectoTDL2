package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Criptomoneda;

public interface CriptomonedaDAO {

	void insertarCriptomoneda(Criptomoneda m) throws SQLException;
	
	List<Criptomoneda> listarCriptomonedas() throws SQLException;
	
	Criptomoneda buscarCriptomoneda(String sigla) throws SQLException;
	
	void actualizarPrecioEnDolar(String sigla, double precioEnDolar) throws SQLException;
	
	boolean estaVacia() throws SQLException; 
	
}
