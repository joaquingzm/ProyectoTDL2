package comparadores;

import java.util.Comparator;

import modelos.MonedaFiduciaria;

public class ComparadorMonedaFiduciariaSigla implements Comparator<MonedaFiduciaria>{

	@Override
	public int compare(MonedaFiduciaria o1, MonedaFiduciaria o2) {
		
		return (o1.getSigla().compareTo(o2.getSigla()));
	}

}
