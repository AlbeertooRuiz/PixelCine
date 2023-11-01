package Datos;

import java.util.ArrayList;

public class Pelicula {
	private String nombre;
	private int duracion;
	private Categoria categoria;
	private int asientosDisponibles;
	private String actorPrincipal;
	private String dia;
	private String hora;
	
	public Pelicula(String nombre, int duracion, Categoria categoria, int asientosDisponibles,
			String actorPrincipal) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.categoria = categoria;
		this.asientosDisponibles = asientosDisponibles;
		this.actorPrincipal = actorPrincipal;
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

	public String getActorPrincipal() {
		return actorPrincipal;
	}

	public void setActores(String actorPrincipal) {
		this.actorPrincipal = actorPrincipal;
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", duracion=" + duracion + ", categoria=" + categoria
				+ ", asientosDisponibles=" + asientosDisponibles + ", actores=" + actorPrincipal + "]";
	}
	
}
