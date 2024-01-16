package Datos;

import java.util.ArrayList;

public class Actor extends Persona {

	ArrayList<Pelicula> peliculas;

	public Actor(String nombre, String apellidos, ArrayList<Pelicula> peliculas) {
		super(nombre, apellidos);
		this.peliculas = peliculas;
	}

	public ArrayList<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	@Override
	public String toString() {
		return "Actor [peliculas=" + peliculas + ", getPeliculas()=" + getPeliculas() + ", getnombre()=" + getnombre()
				+ ", getapellidos()=" + getapellidos() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
