import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.BD;
import Datos.Pelicula;

public class TestPelicula {
	Pelicula peli;

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
			
			peli = new Pelicula();
		} catch (Exception ex) {
	        ex.printStackTrace();
	        fail("Error setting up test: " + ex.getMessage());
	    }
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPeliculaNombre() {
		assertNotNull(peli.getNombre());
	}
	@Test
	public void testPeliculaDuracion() {
		assertNotNull(peli.getDuracion());
	}
	@Test
	public void testPeliculaCategoria() {
		assertNotNull(peli.getCategoria());
	}
	@Test
	public void testPeliculaAsientosLibres() {
		assertNotNull(peli.getAsientosDisponibles());
	}
	@Test
	public void testPeliculaActorPrincipal() {
		assertNotNull(peli.getActorPrincipal());
	}
	@Test
	public void testPeliculaCategoriaDrama() {
		assertEquals("Drama", peli.getCategoria());
	}
	public void testPeliculaActorPrincipalLeonardo(){
		assertEquals("Leonardo Di Caprio", peli.getActorPrincipal());
	}
	@Test
	public void testPeliculaActorPrincipalNoExiste() {
		assertNotEquals("Robert de Niro", peli.getActorPrincipal());
	}
	
	
	

}
