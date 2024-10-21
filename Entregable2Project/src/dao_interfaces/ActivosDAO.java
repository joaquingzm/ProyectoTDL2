package dao_interfaces;

import java.util.Comparator;
import java.util.List;

import modelos.Activo;

public interface ActivosDAO<T> {
	
	void insertarActivo(Activo act);
	
	//Activo leerActivo(String nomenclatura);

	//void actualizarActivo(String nomenclatura);
	
	//Pensar si los eliminar no tendrían que devolver algun parametro que indique si
	//realmente se pudo eliminar
	
	//void eliminarActivo(String nomenclatura);
	
	List<Activo> listarActivos(Comparator<T> c);
}
