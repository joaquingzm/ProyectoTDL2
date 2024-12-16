package daos;

import java.sql.SQLException;
import java.util.List;

import modelos.ActivoMonedaFiduciaria;

public interface ActivoMonedaFiduciariaDAO {
	
	void insertarActivoMonedaFiduciaria(ActivoMonedaFiduciaria act, int idUsuario) throws SQLException;
	
	public void sumarCantidadActivoFiduciaria(int idFIAT, int idUsuario, Double cantidad) throws SQLException;

	public void cambiarCantidadActivoMonedaFiduciaria(int idFIAT, int idUsuario, double cantidad) throws SQLException;
	
	List<ActivoMonedaFiduciaria> listarActivosFiduciarios() throws SQLException;
	
	public ActivoMonedaFiduciaria buscarActivoMonedaFiduciaria(int idFIAT, int idUsuario) throws SQLException;
	
	public List<ActivoMonedaFiduciaria> listarActivosFiduciarios(int idUsuario) throws SQLException;

	public boolean tieneActivoMonedaFiduciaria(int idUsuario, int idFIAT) throws SQLException;
}
