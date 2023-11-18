import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.Asiento;
import Datos.BD;

public class TestAsiento {
	
	Asiento asiento;

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
	public void testAsientoFila() {
		assertNotNull(asiento.getFila());
	}
	@Test
	public void testAsientoColumna() {
		assertNotNull(asiento.getColumna());
	}
	@Test
	public void testAsientoVip() {
		assertNotNull(asiento.isVip());
	}
}
