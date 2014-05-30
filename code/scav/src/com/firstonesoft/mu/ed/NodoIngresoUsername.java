package com.firstonesoft.mu.ed;

import java.util.Date;

public class NodoIngresoUsername {

	private String username;
	private Date date;
	private int contador;
	
	public NodoIngresoUsername() {
		this.username = "";
		this.date = new Date();
		this.contador = 0;
	}
	
	public void incrementar() {
		contador++;
	}

	public long getTiempoDate() {
		return date.getTime();
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}

}
