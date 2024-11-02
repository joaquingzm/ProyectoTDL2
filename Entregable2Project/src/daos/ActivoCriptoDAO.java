package daos;

import java.sql.SQLException;
import java.util.Comparator;

import java.util.List;

import modelos.ActivoCripto;
import modelos.ActivoMonedaFiduciaria;

public interface ActivoCriptoDAO {
	
	void insertarActivoCripto(ActivoCripto act) throws SQLException;
	
	public void sumarCantidadActivoCripto(String sigla, Double cantidad) throws SQLException;
	
	List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c) throws SQLException;
	
	public ActivoCripto buscarActivoCripto(String sigla) throws SQLException;

}
