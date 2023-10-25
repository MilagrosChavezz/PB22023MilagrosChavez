package ar.edu.unlam.pb1.vivero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Vivero {
	
	/**
	 * Se deberan realizar los siguientes tests
	 * 
	 * - 1 test para el metodo agregarPlanta() que arroje la excepcion de validacion
	 * - 1 test para el metodo venderPlanta() que arroje una excepcion a eleccion
	 * - 1 test para el metodo obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta()
	 * - 1 test para el metodo obtenerReporteDePlantasAgrupadasPorTipo()
	 * - 1 test para el metodo obtenerTodasLasPlantasFlorales()
	 * - 1 test para el metodo obtenerPrecio() de la clase Planta 
	 * - 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de floracion
	 * - 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de produccion
	 * */

	private String nombre;

	// No se pueden registrar plantas duplicadas. 2 plantas son iguales cuando tiene
	// el mismo Id
	private Set<Planta> plantas;
	private List<Venta> ventas;

	public Vivero(String nombre) {
		plantas=new HashSet<>();
		ventas=new ArrayList<>();
	}

	// No puede haber 2 plantas con el mismo Id , Si se duplica lanza una Exception
	// Planta Duplicada Exception
	public Boolean agregarPlanta(Planta planta) throws PlantaDuplicadaExeption {
		
		if(!plantas.add(planta)) {
			throw new PlantaDuplicadaExeption("ya existe una planta con el mismo id");
		}
		
		return plantas.add(planta);
	}

	/*
	 * Registra una venta y descuenta del stock de la planta la cantidad deseada. Si no se encuentra la planta lanza
	 * una exception Planta Inexistente. Si no hay Stock Lanza Una Exception
	 * ProdutoSinStockException
	 */
	
	public Planta buscarPlanta(Integer codigoPlanta) throws PlantaInexistente {
		for (Planta planta : plantas) {
			if(((Integer)planta.getCodigo()).equals(codigoPlanta) ) {
				return planta;
			}
		}
		throw new PlantaInexistente("Planta inexistente");
	}
	
	public Boolean hayStock(Integer codigoPlanta,Integer cantidadAVender) throws PlantaInexistente, ProductoSinStock {
		Planta planta=buscarPlanta(codigoPlanta);
		if(planta.getStock()>=cantidadAVender) {
			return true;
		}
		throw new ProductoSinStock("no hay stock");
	}
	
	public Venta BuscarVenta(Integer codigoPlanta) throws PlantaInexistente, ProductoSinStock {
		
		for (Venta planta : ventas) {
			if(planta.getId().equals(codigoPlanta)) {
				return planta;
			}
		}
		return null;
		
	}
	public void venderPlanta(Integer codigoPlanta, Integer cantidadAVender) throws PlantaInexistente, ProductoSinStock {
	
		Planta planta=buscarPlanta(codigoPlanta);
		if(hayStock(codigoPlanta, cantidadAVender) ) {
			ventas.add(new Venta(IdIncrementable(), cantidadAVender, planta));
			plantas.remove(planta);
			planta.setStock(planta.getStock()-cantidadAVender);
		}
		
	}

	private Integer IdIncrementable() {
		// TODO Auto-generated method stub
		Integer numeroMax=0;
		for (Venta venta : ventas) {
			if(venta.getId()>numeroMax) {
				numeroMax=venta.getId();
			}
		}
		return numeroMax+1;
	}

	/*
	 * Obtener un listado de todos los arboles vendidos ordenados por el total de
	 * venta (Cantidad * precioDeLaPlanta)
	 * 
	 */
	public TreeSet<Venta> obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta() {

		TreeSet<Venta> coleccionOrdenada=new TreeSet<>();
		
		coleccionOrdenada.addAll(ventas);
		
		return coleccionOrdenada;
	}

	/*
	 * Obtener Un reporte de las plantas vendidas agrupados por tipo Plantas
	 * 
	 * 
	 * Ejemplo: para una key "arbol", debemos completar las plantas de este tipo
	 * 
	 */
	public Map<String, List<Planta>> obtenerReporteDePlantasAgrupadasPorTipo() {
	    Map<String, List<Planta>> plantasAgrupadas = new HashMap<>();
	    
	    for (Planta planta : plantas) {
	        String tipo = planta.getTipo().toString();
	        
	        // Si el mapa no contiene el tipo, crea una nueva lista
	        plantasAgrupadas.putIfAbsent(tipo, new ArrayList<>());
	        
	        // Obtiene la lista de plantas del tipo y agrega la planta a la lista
	        plantasAgrupadas.get(tipo).add(planta);
	    }
	    
	    return plantasAgrupadas;
	}

	/**
	 * Obtener una lista de plantas que implementen la interfaz correspondiente
	 * */
	public List<Florales> obtenerTodasLasPlantasFlorales() {
		
		List<Florales> listaOrdenada =new  ArrayList<>();
		
		for (Planta planta : plantas) {
			if(planta instanceof Florales) {
				listaOrdenada.add( (Florales) planta);
			}
		}
		return listaOrdenada;
		
	}
}
