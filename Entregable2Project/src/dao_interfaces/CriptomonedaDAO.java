package dao_interfaces;

import java.util.Comparator;
import java.util.List;

import modelos.Criptomoneda;

public interface CriptomonedaDAO {
	
	void insertarCriptomoneda(Criptomoneda m);
	
	//Criptomoneda leerCriptomoneda(String sigla);
	
	List<Criptomoneda> listarCriptomonedas(Comparator<Criptomoneda> c);
	
	void actualizarCriptomoneda(Criptomoneda m);
	
	void eliminarCriptomoneda(Criptomoneda m);
	
	Criptomoneda buscarCriptomoneda(String sigla);
	
}
