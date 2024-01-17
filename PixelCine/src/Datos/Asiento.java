package Datos;

import java.io.Serializable;

public class Asiento implements Serializable{
	private int fila;
	private int columna;
	private boolean vip;

	public Asiento(int fila, int columna, boolean vip) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.vip = vip;
	}

	public Asiento() {
		super();
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
	public String toString() {
		return "Asiento [fila=" + fila + ", columna=" + columna + ", vip=" + vip + "]";
	}

}
