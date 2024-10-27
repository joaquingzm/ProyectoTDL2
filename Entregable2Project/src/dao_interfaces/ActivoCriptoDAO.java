package dao_interfaces;

import java.sql.SQLException;
import java.util.Comparator;

import java.util.List;

import modelos.ActivoCripto;

public interface ActivoCriptoDAO {
	
	void insertarActivoCripto(ActivoCripto act) throws SQLException;
	
	//Activo buscarActivoCripto(String sigla) ;

	//void actualizarActivoCripto(String sigla);
	
	//Pensar si los eliminar no tendrían que devolver algun parametro que indique si
	//realmente se pudo eliminar
	
	//void eliminarActivoCripto(String sigla);
	
	List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c) throws SQLException;
}
