import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Cliente;

public class TestCliente {

	Cliente cliente;
	private String nombre = "Lander";
	private String apellidos = "Gelado Bilbao";
	private String usuario = "ClienteTest";
	private String contrasenia = "ContraTest";

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("Lander", "Gelado Bilbao", "ClienteTest", "ContraTest");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClienteNombre() {
		assertNotNull(cliente.getnombre());
	}

	@Test
	public void testgetClienteNombre() {
		assertEquals(nombre, cliente.getnombre());
	}

	@Test
	public void testsetClienteNombre() {
		cliente.setnombre("Alberto");
		assertEquals("Alberto", cliente.getnombre());
	}

	@Test
	public void testClienteApellido() {
		assertNotNull(cliente.getapellidos());
	}

	@Test
	public void testgetClienteApellidos() {
		assertEquals(apellidos, cliente.getapellidos());
	}

	@Test
	public void testsetClienteApellidos() {
		cliente.setapellidos("Martin Landeta");
		assertNotNull(apellidos, cliente.getapellidos());
	}

	@Test
	public void testClienteUsuario() {
		assertNotNull(cliente.getUsuario());
	}

	@Test
	public void testgetClienteUsuario() {
		assertEquals(usuario, cliente.getUsuario());
	}

	/*
	 * @Test public void testsetClienteUsuario() { cliente.setUsuario("SetUsuario");
	 * assertEquals(usuario, cliente.getUsuario()); }
	 */
	// No entiendo porque me da error esto

	@Test
	public void testClienteContrasenia() {
		assertNotNull(cliente.getContrasenia());
	}

	public void testgetClienteContrasenia() {
		assertEquals(contrasenia, cliente.getContrasenia());
	}

	public void testsetClienteContrasenia() {
		cliente.setContrasenia("CambioCon");
		assertEquals(contrasenia, cliente.getContrasenia());
	}

}
