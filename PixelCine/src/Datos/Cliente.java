package Datos;

public class Cliente extends Persona {

	private String nombre;
	private String apellidos;
	private String usuario;
	private String contrasenia;

	public Cliente(String nombre, String apellidos, String nombre2, String apellidos2, String usuario,
			String contrasenia) {
		super(nombre, apellidos);
		nombre = nombre2;
		apellidos = apellidos2;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Cliente(String nombre, String apellidos, String usuario, String contrasenia) {
		super(nombre, apellidos);
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Cliente() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Cliente [usuario=" + usuario + ", contrasenia=" + contrasenia + "]";
	}

}
