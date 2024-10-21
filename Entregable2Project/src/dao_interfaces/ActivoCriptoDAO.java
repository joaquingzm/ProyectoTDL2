package dao_interfaces;

import java.util.Comparator;

import java.util.List;

import modelos.ActivoCripto;

public interface ActivoCriptoDAO {
	
	void insertarActivoCripto(ActivoCripto act);
	
	//Activo leerActivoCripto(String nomenclatura);

	//void actualizarActivoCripto(String nomenclatura);
	
	//Pensar si los eliminar no tendrían que devolver algun parametro que indique si
	//realmente se pudo eliminar
	
	//void eliminarActivoCripto(String nomenclatura);
	
	List<ActivoCripto> listarActivosCripto(Comparator<ActivoCripto> c);
}
