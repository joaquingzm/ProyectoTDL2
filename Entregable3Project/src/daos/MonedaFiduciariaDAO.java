package daos;
import java.sql.SQLException;
import java.util.List;

import modelos.MonedaFiduciaria;

public interface MonedaFiduciariaDAO {
	
	void insertarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException;
	
	List<MonedaFiduciaria> listarMonedasFiduciarias() throws SQLException;
	
	MonedaFiduciaria buscarMonedaFiduciaria(int idFIAT) throws SQLException;
	
	boolean estaVacia() throws SQLException; 
	
	int buscarMonedaFiduciariaId(MonedaFiduciaria m) throws SQLException;
	
	int buscarMonedaFiduciariaId(String sigla) throws SQLException;
}
