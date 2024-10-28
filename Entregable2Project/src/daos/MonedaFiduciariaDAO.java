package daos;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import modelos.MonedaFiduciaria;

public interface MonedaFiduciariaDAO {
	
	void insertarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException;
	
	//MonedaFiduciaria leerMonedaFiduciaria(String sigla) throws SQLException;
	
	List<MonedaFiduciaria> listarMonedasFiduciarias(Comparator<MonedaFiduciaria> c) throws SQLException;
	
	//void actualizarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException;
	
	//void eliminarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException;
	
	MonedaFiduciaria buscarMonedaFiduciaria(String sigla) throws SQLException;
	
}
