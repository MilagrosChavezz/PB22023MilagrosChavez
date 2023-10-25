package ar.edu.unlam.pb1.vivero;

public class ProductoSinStock extends Exception {

	public ProductoSinStock(String mensaje) {
		super(mensaje);
	}
}
