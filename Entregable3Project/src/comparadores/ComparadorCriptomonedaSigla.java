package comparadores;

import java.util.Comparator;

import modelos.Criptomoneda;

public class ComparadorCriptomonedaSigla implements Comparator<Criptomoneda>{

	@Override
	public int compare(Criptomoneda o1, Criptomoneda o2) {
		
		return (o1.getSigla().compareTo(o2.getSigla()));
	}

}
