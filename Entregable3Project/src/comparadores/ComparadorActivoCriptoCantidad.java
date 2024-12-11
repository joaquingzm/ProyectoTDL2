package comparadores;

import java.util.Comparator;

import modelos.ActivoCripto;

public class ComparadorActivoCriptoCantidad implements Comparator<ActivoCripto> {

	@Override
	public int compare(ActivoCripto a1, ActivoCripto a2) {
		
		double resul = a1.getCantidad() - a2.getCantidad();
		int resultadoComparacion = 0;
		
		if (resul > 0) resultadoComparacion = 1;
		else if (resul < 0) resultadoComparacion = -1;
		
		return resultadoComparacion;
	}
	
}
