package dao_interfaces;

import java.util.Comparator;
import java.util.List;

import modelos.Stock;

public interface StockDAO<T> {

	void generarStock();
	
	List<Stock> listarStock(Comparator<T> c);
	
	//void eliminar();
	
	//void actualizarStock();

	
}
