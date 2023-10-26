package Datos;

<<<<<<< HEAD
public class Pelicula {
	private String nombre;
	private int duracion;
	private Categoria categoria;
	private int asientosDisponibles;
	public Pelicula(String nombre, int duracion, Categoria categoria, int asientosDisponibles) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.categoria = categoria;
		this.asientosDisponibles = asientosDisponibles;
	}
	public Pelicula() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}
	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", duracion=" + duracion + ", categoria=" + categoria
				+ ", asientosDisponibles=" + asientosDisponibles + "]";
	}
	
=======
import java.util.ArrayList;

public class Pelicula {
	
	private String nombre;
	private int duracion;
	private ArrayList<Actor> actores;
	
	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pelicula(String nombre, int duracion, ArrayList<Actor> actores) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.actores = actores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public ArrayList<Actor> getActores() {
		return actores;
	}

	public void setActores(ArrayList<Actor> actores) {
		this.actores = actores;
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", duracion=" + duracion + ", actores=" + actores + "]";
	}
	
	
	

>>>>>>> branch 'master' of https://github.com/AlbeertooRuiz/PixelCine.git
}
