package dao_interfaces;

import java.util.Comparator;
import java.util.List;

import modelos.Stock;

public interface StockDAO {

	void generarStock();
	
	List<Stock> listarStock(Comparator<Stock> c);
	
	//void eliminar();
	
	//void actualizarStock();

	
}
