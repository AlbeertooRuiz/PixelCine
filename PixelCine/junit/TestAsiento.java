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

	private int fila = 2;
	private int columna = 3;
	private boolean vip = true;

	@Before
	public void setUp() throws Exception {
		asiento = new Asiento(2, 3, true);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAsiento() {
		assertNotNull(asiento);
	}

	@Test
	public void testgetAsientoFila() {
		assertEquals(fila, asiento.getFila());
	}

	@Test
	public void testsettAsientoFila() {
		asiento.setFila(4);
		assertEquals(4, asiento.getFila());
	}

	@Test
	public void testgetAsientoColumna() {
		assertEquals(columna, asiento.getColumna());
	}

	@Test
	public void testsetAsientoColumna() {
		asiento.setColumna(6);
		assertEquals(6, asiento.getColumna());
	}

	@Test
	public void testAsientoVip() {
		assertNotNull(asiento.isVip());
	}

	public void testAsientoisVip() {
		assertEquals(true, asiento.isVip());
	}
}
