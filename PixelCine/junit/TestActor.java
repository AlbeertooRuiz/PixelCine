import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.Actor;
import Datos.BD;

public class TestActor {
	
	Actor actor;
	
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
		
		} catch (Exception ex) {
	        ex.printStackTrace();
	        fail("Error setting up test: " + ex.getMessage());
	    }
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActorNombre() {
		assertNotNull(actor.getnombre());
	}
	@Test
	public void testActorApellidos() {
		assertNotNull(actor.getapellidos());
	}
	@Test
	public void testActorPeliculas() {
		assertNotNull(actor.getPeliculas());
	}
	

}
