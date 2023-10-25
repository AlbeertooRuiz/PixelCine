package Datos;

public class Cliente {
	
	private String usuario;
	private String contrasenia;
	
	public Cliente(String usuario, String contrasenia) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Cliente() {
		super();
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
