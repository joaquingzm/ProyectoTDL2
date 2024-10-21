package dao_interfaces;

import java.util.Comparator;
import java.util.List;

public interface StockDAO {

	void insertarCantidad
	
	List<Stock> listarStock(Comparator c);
	
	void eliminar();
	
	void actualizarStock();
	
	
}
