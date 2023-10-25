package ar.edu.unlam.pb1.vivero;

public class Hierba extends Planta{

	private final double gananciaHierbaMata = 1.2;
	

	public Hierba(int codigo, String nombre, double precio, int stock,TipoPlanta tipo) {
		super(codigo,nombre,precio,stock, tipo);
	}

	@Override
	public Double obtenerPrecio() {
		// TODO Auto-generated method stub
		return gananciaHierbaMata*getPrecioBase();
	}

	
}
