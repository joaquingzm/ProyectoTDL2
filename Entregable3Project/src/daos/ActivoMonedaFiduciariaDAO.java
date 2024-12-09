package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.ActivoMonedaFiduciaria;

public interface ActivoMonedaFiduciariaDAO {
	
	void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act) throws SQLException;
	
	public void sumarCantidadActivoFiduciaria(String sigla, Double cantidad) throws SQLException;

	List<ActivoMonedaFiduciaria> listarActivosFiduciarios() throws SQLException;
	
	public ActivoMonedaFiduciaria buscarActivoMonedaFiduciaria(String sigla) throws SQLException;
	
	public List<ActivoMonedaFiduciaria> listarActivosFiduciarios(int idUsuario) throws SQLException;
}
