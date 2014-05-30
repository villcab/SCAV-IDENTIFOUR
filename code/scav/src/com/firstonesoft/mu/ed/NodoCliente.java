package com.firstonesoft.mu.ed;

import java.util.HashMap;
import java.util.Map;

public class NodoCliente {

	private String username;
	private String direccionIp;
	private long tiempo;
	private Map<String, Boolean> mapListaUrl;
	
	public NodoCliente() {
		this.username = "";
		this.direccionIp = "";
		this.tiempo = 0;
		mapListaUrl = new HashMap<String, Boolean>();
	}
	
	public void agregarUrl(String url, boolean sw) {
		mapListaUrl.put(url, sw);
	}

	public boolean existeUrl(String url) {
		return mapListaUrl.get(url);
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

	public String getDireccionIp() {
		return direccionIp;
	}
	
	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}
	
	public long getTiempo() {
		return tiempo;
	}
	
	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}
	
}
