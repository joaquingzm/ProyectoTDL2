package comparadores;

import java.util.Comparator;

import modelos.ActivoMonedaFiduciaria;

public class ComparadorActivoMonedaFiduciariaCantidad implements Comparator<ActivoMonedaFiduciaria>{

	@Override
	public int compare(ActivoMonedaFiduciaria a1, ActivoMonedaFiduciaria a2) {
		
		double resul = a1.getCantidad() - a2.getCantidad();
		int resultadoComparacion = 0;
		
		if (resul > 0) resultadoComparacion = 1;
		else if (resul < 0) resultadoComparacion = -1;
		
		return resultadoComparacion;
	}

}
