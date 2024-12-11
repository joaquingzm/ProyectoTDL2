package comparadores;

import java.util.Comparator;

import modelos.Criptomoneda;

public class ComparadorCriptomonedaPrecioEnDolar implements Comparator<Criptomoneda>{

	@Override
	public int compare(Criptomoneda o1, Criptomoneda o2) {
		
		double resul = o1.getPrecioEnDolar() - o2.getPrecioEnDolar();
		int resultadoComparacion = 0;
		
		if (resul > 0) resultadoComparacion = 1;
		else if (resul < 0) resultadoComparacion = -1;
		
		return resultadoComparacion;
	}

}
