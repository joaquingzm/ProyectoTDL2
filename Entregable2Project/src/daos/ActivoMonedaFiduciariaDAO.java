package daos;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import modelos.ActivoMonedaFiduciaria;

public interface ActivoMonedaFiduciariaDAO {
	
	void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act) throws SQLException;
	
	public void sumarCantidadActivoFiduciaria(String sigla, Double cantidad) throws SQLException;
	
	//Activo leerActivoMonedaFiduciaria(String nomenclatura);

	//void actualizarActivoMonedaFiduciaria(String nomenclatura);
	
	//Pensar si los eliminar no tendrían que devolver algun parametro que indique si
	//realmente se pudo eliminar
	
	//void eliminarActivoMonedaFiduciaria(String nomenclatura);
	
	List<ActivoMonedaFiduciaria> listarActivosFiduciarios(Comparator<ActivoMonedaFiduciaria> c) throws SQLException;
}
