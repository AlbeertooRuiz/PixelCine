import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Datos.BD;
import Datos.Cliente;

public class TestCliente {
	Cliente cliente;
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
	public void testClienteNombre() {
		assertNotNull(cliente.getnombre());
	}
	@Test
	public void testClienteApellido() {
		assertNotNull(cliente.getapellidos());
	}
	@Test
	public void testClienteUsuario() {
		assertNotNull(cliente.getUsuario());
	}
	@Test
	public void testClienteContrasenia() {
		assertNotNull(cliente.getContrasenia());
	}
	@Test
	public void testClienteUsuarioExiste() {
		assertEquals("a", cliente.getUsuario());
	}@Test
	public void testClienteContraExiste() {
		assertEquals("a", cliente.getContrasenia());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
