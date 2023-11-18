import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.BD;
import Datos.Pelicula;
import Datos.Persona;

public class TestPersona {

	Persona persona;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try {
			BD basedatos = new BD();
			BD.connect();
			
			persona = new Persona();
		} catch (Exception ex) {
	        ex.printStackTrace();
	        fail("Error setting up test: " + ex.getMessage());
	    }
	}

	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersonaNombre() {
		assertNotNull(persona.getnombre());
	}
	@Test
	public void testPersonaApellido() {
		assertNotNull(persona.getapellidos());
	}

}
