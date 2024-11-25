package comparadores;

import java.util.Comparator;

import modelos.ActivoMonedaFiduciaria;

public class ComparadorActivoMonedaFiduciariaSigla implements Comparator<ActivoMonedaFiduciaria> {
	public int compare(ActivoMonedaFiduciaria a1, ActivoMonedaFiduciaria a2) {
		
		return (a1.getMonedaFIAT().getSigla().compareTo(a2.getMonedaFIAT().getSigla()));
	}
}
