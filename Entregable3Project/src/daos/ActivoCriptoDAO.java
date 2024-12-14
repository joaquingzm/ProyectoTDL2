package daos;

import java.sql.SQLException;

import java.util.List;

import modelos.ActivoCripto;

public interface ActivoCriptoDAO {
	
	void insertarActivoCripto(ActivoCripto act, int idUsuario) throws SQLException;
	
	public void sumarCantidadActivoCripto(int idCripto, int idUsuario, Double cantidad) throws SQLException;
	
	List<ActivoCripto> listarActivosCripto() throws SQLException;
	
	public ActivoCripto buscarActivoCripto(String sigla, int idUsuario) throws SQLException;
	
	public List<ActivoCripto> listarActivosCripto(int idUsuario) throws SQLException;

	public boolean tieneActivoCripto(int idUsuario, String sigla) throws SQLException;
}
