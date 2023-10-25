package ar.edu.unlam.pb1.vivero;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

public class test {

	@Test(expected = PlantaDuplicadaExeption.class)
	public void NoPuedeHaberDosPlantasCOnELMIsmoID() throws PlantaDuplicadaExeption {
		Planta arbol = new Arbol(1, "sakura", 2324, 3, null);
		Planta arbusto = new Arbusto(1, "Sakura", 23124, 2, null);

		Vivero viveros = new Vivero("MILI-VIVERO");
		viveros.agregarPlanta(arbol);
		viveros.agregarPlanta(arbusto);

	}

	@Test
	public void SeREalizaUnaVentaYSeDescuentaStock()
			throws PlantaDuplicadaExeption, PlantaInexistente, ProductoSinStock {
		Planta arbol = new Arbol(1, "sakura", 2324, 3, null);
		Planta arbusto = new Arbusto(2, "Sakura", 23124, 2, null);

		Vivero viveros = new Vivero("MILI-VIVERO");
		viveros.agregarPlanta(arbol);
		viveros.agregarPlanta(arbusto);

		viveros.venderPlanta(2, 2);

		assertEquals(0, arbusto.getStock());

	}

	@Test
	public void SeDevuelveUnaListaOrdenda() throws PlantaDuplicadaExeption, PlantaInexistente, ProductoSinStock {

		Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
		Planta arbusto = new Arbusto(2, "Sakura", 2, 2, TipoPlanta.ARBUSTO);
		Planta arbusto2 = new Arbusto(3, "Sakura", 2, 8, TipoPlanta.ARBUSTO);
		Planta arbusto3 = new Arbusto(4, "Sakura", 2, 7, TipoPlanta.ARBUSTO);

		Vivero viveros = new Vivero("MILI-VIVERO");
		viveros.agregarPlanta(arbol);
		viveros.agregarPlanta(arbusto);
		viveros.agregarPlanta(arbusto2);
		viveros.agregarPlanta(arbusto3);

		viveros.venderPlanta(1, 1);
		viveros.venderPlanta(2, 2);
		viveros.venderPlanta(3, 8);
		viveros.venderPlanta(4, 5);

		TreeSet<Venta> ventasOrdenadas = viveros.obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta();

		Integer cod2 = 3;
		Integer cod2VO = ventasOrdenadas.last().getPlanta().getCodigo();

		Integer cod = arbol.getCodigo();
		Integer codVO = ventasOrdenadas.first().getPlanta().getCodigo();

		assertEquals(cod, codVO);
		assertEquals(cod2, cod2VO);
		assertEquals(4, ventasOrdenadas.size());
	}

	@Test
	public void obtenerReporteDePlantasAgrupadasPorTipo() throws PlantaDuplicadaExeption {

		Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
		Planta arbusto = new Arbusto(2, "Sakura", 2, 2, TipoPlanta.ARBUSTO);
		Planta arbusto2 = new Arbusto(3, "Sakura", 2, 8, TipoPlanta.ARBUSTO);
		Planta arbusto3 = new Arbusto(4, "Sakura", 2, 7, TipoPlanta.HIERBA);
		

		Vivero viveros = new Vivero("MILI-VIVERO");
		viveros.agregarPlanta(arbol);
		viveros.agregarPlanta(arbusto);
		viveros.agregarPlanta(arbusto2);
		viveros.agregarPlanta(arbusto3);

		Map<String, List<Planta>> plantasOrdenadasPorTipo = viveros.obtenerReporteDePlantasAgrupadasPorTipo();

		assertTrue(plantasOrdenadasPorTipo.containsKey("ARBOL"));
		assertTrue(plantasOrdenadasPorTipo.containsKey("ARBUSTO"));
		assertTrue(plantasOrdenadasPorTipo.containsKey("HIERBA"));
	
		
		// assertTrue(plantasOrdenadasPorTipo.containsKey("HIERBA"));

	}
	
	@Test
	public void obtenerTodasLasPlantasFlorales() throws PlantaDuplicadaExeption {

		Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
		Planta arbusto = new Arbol(2, "Sakura", 2, 2, TipoPlanta.ARBOL);
		Planta arbusto2 = new Arbol(3, "Sakura", 2, 8, TipoPlanta.ARBOL);
		Planta arbusto3 = new Hierba(4, "Sakura", 2, 7, TipoPlanta.HIERBA);
		

		Vivero viveros = new Vivero("MILI-VIVERO");
		viveros.agregarPlanta(arbol);
		viveros.agregarPlanta(arbusto);
		viveros.agregarPlanta(arbusto2);
		viveros.agregarPlanta(arbusto3);

		 List<Florales> plantasImplementanInterfaz = viveros.obtenerTodasLasPlantasFlorales();

		for (Florales florales : plantasImplementanInterfaz) {
		
			assertTrue(florales instanceof Florales);
			assertEquals(3, plantasImplementanInterfaz.size());
		}

	}
	
	@Test
	public void obtenerPrecioBaseDeLaClasePlanta() throws PlantaDuplicadaExeption {

		Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
		Planta arbusto = new Arbol(2, "Sakura", 2, 2, TipoPlanta.ARBOL);
		Planta arbusto2 = new Arbol(3, "Sakura", 2, 8, TipoPlanta.ARBOL);
		Planta arbusto3 = new Hierba(4, "Sakura", 2, 7, TipoPlanta.HIERBA);
		
        Double precio=arbol.getPrecioBase();
		
		
			assertEquals(2, precio,0.0);
		}

	@Test
	public void obtenerPrecioDeUnaPlantaFloracion() throws PlantaDuplicadaExeption {

		Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
	   
		((Florales)arbol).florar();
		Double precio=arbol.obtenerPrecio();
		
		Double ex=(((arbol.getPrecioBase()*0.05)+arbol.getPrecioBase()))*2.3;
      assertEquals(ex, precio, 0.01);
		
		
					}
	


@Test
public void obtenerPrecioDeUnaPlantaFloracion100PorcientoYFrutos() throws PlantaDuplicadaExeption {

	Planta arbol = new Arbol(1, "sakura", 2, 4, TipoPlanta.ARBOL);
   
	((Florales)arbol).florar();
	((Florales)arbol).florar();
	((Florales)arbol).florar();
	((Florales)arbol).florar();
	((Florales)arbol).florar();
	
	((Florales)arbol).producirFrutos();
	((Florales)arbol).producirFrutos();
	
	Double precio=arbol.obtenerPrecio();
	
	Double ex=((((arbol.getPrecioBase()*0.85)+arbol.getPrecioBase()))*2.3)+(2*0.03);
  assertEquals(ex, precio, 0.01);
	
	
				}
}

	


