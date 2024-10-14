package dao_interfaces;
import java.util.Comparator;
import java.util.List;

import modelos.Moneda;

public interface MonedaDAO {
	
	void insertarMoneda(Moneda m);
	
	List<Moneda> listar(Comparator c);
	
	void actualizarMoneda(Moneda m);
	
	void eliminarMoneda(Moneda m);
	
}
