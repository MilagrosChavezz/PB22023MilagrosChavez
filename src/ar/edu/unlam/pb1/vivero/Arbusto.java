package ar.edu.unlam.pb1.vivero;

public class Arbusto extends Planta {
	
	private final double GANANCIA_ARBUSTO = 1.6;
	 
	public Arbusto(int codigo, String nombre, double precio, int stock,TipoPlanta tipo) {
		super(codigo,nombre,precio,stock, tipo);
	}

	@Override
	public Double obtenerPrecio() {
		// TODO Auto-generated method stub
		return getPrecioBase()*GANANCIA_ARBUSTO;
	}

}
