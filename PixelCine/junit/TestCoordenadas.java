import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.BD;
import Datos.Coordenadas;

public class TestCoordenadas {
	Coordenadas coor;

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
			BD.initBD(null);
		
		} catch (Exception ex) {
	        ex.printStackTrace();
	        fail("Error setting up test: " + ex.getMessage());
	    }
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCoordenadaX() {
		assertNotNull(coor.getX());
	}
	@Test
	public void testCoordenadaY() {
		assertNotNull(coor.getY());
	}

}
