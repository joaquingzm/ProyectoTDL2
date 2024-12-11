package comparadores;

import java.util.Comparator;

import modelos.ActivoCripto;

public class ComparadorActivoCriptoSigla implements Comparator<ActivoCripto>{

	@Override
	public int compare(ActivoCripto a1, ActivoCripto a2) {
		
		return (a1.getCriptomoneda().getSigla().compareTo(a2.getCriptomoneda().getSigla()));
	}
}
