import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import BaseDeDatos.BD;
import Datos.Pelicula;
import Datos.Persona;

public class TestPersona {

	private Persona persona;

	private String nombre = "Lander";
	private String apellidos = "Gelado";

	@Before
	public void setUp() throws Exception {
		persona = new Persona(nombre, apellidos);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersona() {
		assertNotNull(persona);
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getnombre());
	}

	@Test
	public void testSetNombre() {
		persona.setnombre("Jane");
		assertEquals("Jane", persona.getnombre());
	}

	@Test
	public void testGetApellidos() {
		assertEquals(apellidos, persona.getapellidos());
	}

	@Test
	public void testSetApellidos() {
		persona.setapellidos("Smith");
		assertEquals("Smith", persona.getapellidos());
	}
}
