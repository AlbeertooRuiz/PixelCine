import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Datos.Actor;
import Datos.Pelicula;

public class TestActor {

	private Actor actor;

	private String Nombre = "Juanjo";
	private String Apellido = "Perez Sainz";
	private ArrayList<Pelicula> peliculas = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		actor = new Actor(Nombre, Apellido, peliculas);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestActor() {
		assertNotNull(actor);
	}

	@Test
	public void testGetNombre() {
		assertEquals(Nombre, actor.getnombre());
	}

	@Test
	public void testSetNombre() {
		actor.setnombre("Juanjo");
		assertEquals("Juanjo", actor.getnombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals(Apellido, actor.getapellidos());
	}

	@Test
	public void testSetApellido() {
		actor.setapellidos("Perez");
		assertEquals("Perez", actor.getapellidos());
	}

	@Test
	public void testGetaMesa() {
		assertEquals(peliculas, actor.getPeliculas());
	}

	@Test
	public void testSetaMesa() {
		actor.setPeliculas(peliculas);
		assertEquals(peliculas, actor.getPeliculas());
	}

}
