package comparadores;

import java.util.Comparator;

import modelos.MonedaFiduciaria;

public class ComparadorMonedaFiduciariaPrecioEnDolar implements Comparator<MonedaFiduciaria>{

	@Override
	public int compare(MonedaFiduciaria o1, MonedaFiduciaria o2) {
	
		double resul = o1.getPrecioEnDolar() - o2.getPrecioEnDolar();
		int resultadoComparacion = 0;
		
		if (resul > 0) resultadoComparacion = 1;
		else if (resul < 0) resultadoComparacion = -1;
		
		return resultadoComparacion;
	}

}
