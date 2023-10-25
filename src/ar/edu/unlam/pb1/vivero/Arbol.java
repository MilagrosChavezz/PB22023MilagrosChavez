package ar.edu.unlam.pb1.vivero;

public class Arbol extends Planta implements Florales{

	private final double GANANCIA_ARBOL = 2.3;
	private Double estadoFloracion;
	private int madurezFrutos;

	public Arbol(int codigo, String nombre, double precio, int stock,TipoPlanta tipo) {
		 super(codigo,nombre,precio,stock, tipo);
		 estadoFloracion=0.0;
		 madurezFrutos=0;
	}

	@Override
	public Double obtenerPrecio() {
	    Double precioArbol = getPrecioBase();

	    if (estadoFloracion < 33.0) {
	    	precioArbol = getPrecioBase() * 0.05 + precioArbol; // Aumenta un 5%
	    } else if (estadoFloracion >= 34.0 && estadoFloracion <= 66.0) {
	        precioArbol = getPrecioBase() *0.75+precioArbol; // Aumenta un 7.5%
	    } else if (estadoFloracion >= 66.0 && estadoFloracion < 100.0) {
	        precioArbol = getPrecioBase()*0.85+precioArbol; // Aumenta un 8.5%
	    } else if (estadoFloracion == 100.0 && madurezFrutos > 0) {
	        // Si la floración está al 100% y hay madurez en los frutos
	        double porcentajeMadurez = madurezFrutos * 0.03; // Aumenta 3% por cada unidad de madurez
	        precioArbol = getPrecioBase()*0.85+getPrecioBase()+porcentajeMadurez; // Aumenta un 8.5% + porcentaje de madurez
	    }

	    return precioArbol * GANANCIA_ARBOL;
	}

	@Override
	public void florar() {
		// TODO Auto-generated method stub
		Double avanceFloracion=20.0;
		if(estadoFloracion<100) {
	  this.estadoFloracion+=avanceFloracion;
		}
	}

	@Override
	public void producirFrutos() {
		// TODO Auto-generated method stub
		Integer aumentoDeMadurez=1;
		if(estadoFloracion==100 && madurezFrutos<5) {
			this.madurezFrutos+=aumentoDeMadurez;
		}
	}


	/**
	 * Los arboles pueden dar flores, las que posteriormente se convertiran en frutos. 
	 * Las flores tienen un rango de crecimiento, siendo 0 (el valor inicial) cuando no tiene flores aun y 100 cuando ya estan aptas para dar frutos.
	 * El precio del arbol se incrementa de acuerdo al avance de la floracion:
	 * - Menos de 33% de floracion incrementa un 5% su precio. 
	 * - Entre 34% y 66% incrementa un 7.5% su precio.
	 * - Mas del 66% y menos de 100% incrementa un 8.5% su precio.
	 * - Cuando el estado de floracion llega al 100%, se comienza la produccion de frutos
	 * */

	/**
	 * Para poder producir frutos, el estado de floracion debe ser 100%.
	 * La produccion de frutos se mide entre 1 y 5 siendo 5 el mejor escenario.
	 * Cuando un arbol produce frutos, su precio aumenta 10% inicialmente (por tener el estado de floracion al 100%) y
	 * agrega al precio, el porcentaje de madurez. Ejemplo: precioBase = 100 + 10% (por floracion) + 3% (madurez actual de los frutos)
	 * */
}
