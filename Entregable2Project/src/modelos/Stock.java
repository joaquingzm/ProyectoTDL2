package modelos;

public class Stock {
	
	private double cantidad;
	private Criptomoneda criptomoneda;
	
	public Stock(double cantidad, Criptomoneda criptomoneda) {
		//super();
		this.cantidad = cantidad;
		this.criptomoneda = criptomoneda;
	}

	public double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	

	public Criptomoneda getCriptomoneda() {
		return criptomoneda;
	}
	
	public void setCriptomoneda(Criptomoneda criptomoneda) {
		this.criptomoneda = criptomoneda;
	}
	
	@Override
	public String toString() {
		String str = "Stock:{ Cantidad: "+this.getCantidad()+", Criptomoneda:{ "+this.getCriptomoneda().toString()+"} }";
		return str;
	}
}
