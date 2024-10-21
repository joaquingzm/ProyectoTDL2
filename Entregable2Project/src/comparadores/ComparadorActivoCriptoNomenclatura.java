package comparadores;

import java.util.Comparator;

import modelos.ActivoCripto;

public class ComparadorActivoCriptoNomenclatura implements Comparator<ActivoCripto>{
	public int compare(ActivoCripto a1, ActivoCripto a2) {
		return a1.getCriptomoneda().getSigla().compareTo(a2.getCriptomoneda().getSigla());
	}
}
