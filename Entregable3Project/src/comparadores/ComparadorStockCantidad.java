package comparadores;

import java.util.Comparator;

import modelos.Stock;

public class ComparadorStockCantidad implements Comparator<Stock>{

	@Override
	public int compare(Stock s1, Stock s2) {
		double resul = s1.getCantidad() - s2.getCantidad();
		int resultadoComparacion = 0;
		
		if (resul > 0) resultadoComparacion = 1;
		else if (resul < 0) resultadoComparacion = -1;
		
		return resultadoComparacion;
	}

}
