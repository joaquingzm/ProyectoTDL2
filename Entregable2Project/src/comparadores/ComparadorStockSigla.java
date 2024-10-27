package comparadores;

import java.util.Comparator;

import modelos.Stock;

public class ComparadorStockSigla implements Comparator<Stock> {

	@Override
	public int compare(Stock s1, Stock s2) {
		return (s1.getCriptomoneda().getSigla().compareTo(s2.getCriptomoneda().getSigla()));
	}

}
