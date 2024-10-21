package dao_interfaces;

import java.util.Comparator;
import java.util.List;

public interface StockDAO<T> {

	void insertarCantidad
	
	List<Stock> listarStock(Comparator<T> c);
	
	void eliminar();
	
	void actualizarStock();
	
	
}
