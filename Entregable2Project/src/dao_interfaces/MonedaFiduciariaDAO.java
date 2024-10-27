package dao_interfaces;
import java.util.Comparator;
import java.util.List;

import modelos.MonedaFiduciaria;

public interface MonedaFiduciariaDAO {
	
	void insertarMonedaFiduciaria(MonedaFiduciaria m);
	
	//MonedaFiduciaria leerMonedaFiduciaria(String sigla);
	
	List<MonedaFiduciaria> listarMonedasFiduciarias(Comparator<MonedaFiduciaria> c);
	
	//void actualizarMonedaFiduciaria(MonedaFiduciaria m);
	
	//void eliminarMonedaFiduciaria(MonedaFiduciaria m);
	
	MonedaFiduciaria buscarMonedaFiduciaria(String sigla);
	
}
