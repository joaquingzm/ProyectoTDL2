package daos;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import modelos.MonedaFiduciaria;

public interface MonedaFiduciariaDAO {
	
	void insertarMonedaFiduciaria(MonedaFiduciaria m) throws SQLException;
	
	List<MonedaFiduciaria> listarMonedasFiduciarias(Comparator<MonedaFiduciaria> c) throws SQLException;
	
	MonedaFiduciaria buscarMonedaFiduciaria(String sigla) throws SQLException;
	
}
