package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.Criptomoneda;

public interface CriptomonedaDAO {

	void insertarCriptomoneda(Criptomoneda cm) throws SQLException;
	
	List<Criptomoneda> listarCriptomonedas() throws SQLException;
	
	Criptomoneda buscarCriptomoneda(int idCripto) throws SQLException;
	
	int buscarCriptomonedaId(Criptomoneda cm) throws SQLException;
	
	int buscarCriptomonedaId(String sigla) throws SQLException;
	
	void actualizarPrecioEnDolar(int idCripto, double precioEnDolar) throws SQLException;
	
	boolean estaVacia() throws SQLException; 
	
}
