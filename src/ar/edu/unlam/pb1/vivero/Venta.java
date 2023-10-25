package ar.edu.unlam.pb1.vivero;

public class Venta implements Comparable<Venta>{

	private Integer id;
	private Integer unidades;
	private Planta planta;
	public Double precioUnitario; // Precio final de la planta al momento de realizar la venta
	public Integer precioTotal;
	
	public Venta(Integer id, Integer unidades, Planta planta) {
		super();
		this.id = id;
		this.unidades = unidades;
		this.planta = planta;
		this.precioUnitario = planta.obtenerPrecio();
		this.precioTotal=getPrecioTotal();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	public Planta getPlanta() {
		return planta;
	}
	public void setPlanta(Planta planta) {
		this.planta = planta;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Integer getPrecioTotal() {
		return (int) (this.precioUnitario*unidades);
	}

	@Override
	public int compareTo(Venta o) {
		// TODO Auto-generated method stub
		return this.getPrecioTotal()-o.getPrecioTotal();
	}
	
	
}
