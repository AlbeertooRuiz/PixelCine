package Datos;

import java.io.Serializable;
import java.util.ArrayList;

public class Pelicula implements Serializable {
	private String nombre;
	private int duracion;
	private Categoria categoria;
	private int asientosDisponibles;
	private String actorPrincipal;
	private String fechayhora;

	public Pelicula(String nombre, int duracion, Categoria categoria, int asientosDisponibles, String actorPrincipal,
			String fechayhora) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.categoria = categoria;
		this.asientosDisponibles = asientosDisponibles;
		this.actorPrincipal = actorPrincipal;
		this.fechayhora = fechayhora;
	}

	public Pelicula(String nombre, int duracion, Categoria categoria, int asientosDisponibles) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.categoria = categoria;
		this.asientosDisponibles = asientosDisponibles;
	}

	public Pelicula(String nombre, int duracion, Categoria categoria, int asientosDisponibles, String fechayhora) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.categoria = categoria;
		this.asientosDisponibles = asientosDisponibles;
		this.fechayhora = fechayhora;
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

	public void setActorPrincipal(String actorPrincipal) {
		this.actorPrincipal = actorPrincipal;
	}

	public String getFechayhora() {
		return fechayhora;
	}

	public void setFechayhora(String fechayhora) {
		this.fechayhora = fechayhora;
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", duracion=" + duracion + ", categoria=" + categoria
				+ ", asientosDisponibles=" + asientosDisponibles + ", actorPrincipal=" + actorPrincipal
				+ ", fechayhora=" + fechayhora + "]";
	}

	public String toStringResumido() {
		return nombre + " - Duración: " + duracion + "mins" + " - Categoria: " + categoria + " - Asientos Disponibles: "
				+ asientosDisponibles;
	}
}
