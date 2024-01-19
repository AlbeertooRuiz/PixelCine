import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Categoria;
import domain.Pelicula;

public class TestPelicula {

	private Pelicula pelicula;
	private Categoria categoria;

	private String nombre = "Inception";
	private int duracion = 150;
	private Categoria categori = Categoria.Accion;
	private int asientosDisponibles = 200;
	private String actorPrincipal = "Leonardo DiCaprio";
	private String fechayhora = "17/08/2002  20:00";

	@Before
	public void setUp() throws Exception {
		pelicula = new Pelicula(nombre, duracion, categoria, asientosDisponibles, actorPrincipal, fechayhora);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPelicula() {
		assertNotNull(pelicula);
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, pelicula.getNombre());
	}

	@Test
	public void testSetNombre() {
		pelicula.setNombre("Interstellar");
		assertEquals("Interstellar", pelicula.getNombre());
	}

	@Test
	public void testGetDuracion() {
		assertEquals(duracion, pelicula.getDuracion());
	}

	@Test
	public void testSetDuracion() {
		pelicula.setDuracion(180);
		assertEquals(180, pelicula.getDuracion());
	}

	@Test
	public void testGetCategoria() {
		assertEquals(categoria, pelicula.getCategoria());
	}

	@Test
	public void testSetCategoria() {
		pelicula.setCategoria(Categoria.Drama);
		assertEquals(Categoria.Drama, pelicula.getCategoria());
	}

	@Test
	public void testGetAsientosDisponibles() {
		assertEquals(asientosDisponibles, pelicula.getAsientosDisponibles());
	}

	@Test
	public void testSetAsientosDisponibles() {
		pelicula.setAsientosDisponibles(180);
		assertEquals(180, pelicula.getAsientosDisponibles());
	}

	@Test
	public void testGetActorPrincipal() {
		assertEquals(actorPrincipal, pelicula.getActorPrincipal());
	}

	@Test
	public void testSetActorPrincipal() {
		pelicula.setActorPrincipal("Tom Hardy");
		assertEquals("Tom Hardy", pelicula.getActorPrincipal());
	}

}
