package com.firstonesoft.mu.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import com.firstonesoft.mu.business.BitacoraBL;
import com.firstonesoft.mu.ed.NodoCliente;
import com.firstonesoft.mu.ed.NodoIngresoUsername;
import com.firstonesoft.util.BitacoraUtil;
import com.firstonesoft.util.Parameters;

public class TimeOutController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BitacoraBL bitacoraBL;

	private static Map<String, NodoCliente> mapClientes = new HashMap<String, NodoCliente>();
	private static Map<String, NodoIngresoUsername> mapIngresoClientes = new HashMap<String, NodoIngresoUsername>();

	private static int timeOut = Parameters.system_tiempo_fuera;
	private int indexPath;

	public TimeOutController() {
		this.indexPath = 0;
	}

	public void addRutaBase(String path) {
		int k = path.lastIndexOf("/");
		indexPath = k + 1;
	}

	public void addNodoCliente(NodoCliente nd) {
		mapClientes.put(nd.getUsername(), nd);
	}

	public NodoCliente getNodoCliente(String username) {
		return mapClientes.get(username);
	}

	public void crearNuevoCliente(String username, String direccionIp) {
		NodoCliente nd = new NodoCliente();
		nd.setUsername(username);
		nd.setDireccionIp(direccionIp);
		nd.setTiempo(new Date().getTime());
		mapClientes.put(username, nd);
	}

	public void setDatos(String username, long tiempo) {
		NodoCliente nd = mapClientes.get(username);
		nd.setTiempo(tiempo);
	}

	public void remove(String username) {
		mapClientes.remove(username);
	}

	public String getDireccionIp(String username) {
		return mapClientes.get(username).getDireccionIp();
	}

	@SuppressWarnings("rawtypes")
	public void registerOutTime(long timeMax) {
		Iterator em = mapClientes.keySet().iterator();
		Date d = new Date();
		long tp = d.getTime();
		try {
			while (em.hasNext()) {
				String key = (String) em.next();
				long value = mapClientes.get(key).getTiempo();
				
				if ((tp - value) > timeMax) {
					bitacoraBL.accionExpulsar(key, mapClientes.get(key).getDireccionIp(), BitacoraUtil.MU_EXPULSION_SISTEMA);
					mapClientes.remove(key);
				}
			}
		} catch (Exception e) {
			
		}
	}

	@SuppressWarnings("rawtypes")
	public void clearUserCliente() {
		int nroTime = timeOut;
		long timeMax = ((long) nroTime * 60000l);
		Iterator em = mapIngresoClientes.keySet().iterator();
		Date d = new Date();
		long tp = d.getTime();
		try {
			while (em.hasNext()) {
				String key = (String) em.next();
				long value = mapIngresoClientes.get(key).getTiempoDate();
				
				if ((tp - value) > timeMax) {
					mapIngresoClientes.remove(key);
				}
			}
		} catch (Exception e) {
		}

	}

	public boolean existUsernameIngreso(String username) {
		return mapIngresoClientes.containsKey(username);
	}

	public void insertUsernameIngreso(String username) {
		NodoIngresoUsername nd = new NodoIngresoUsername();
		nd.setDate(new Date());
		nd.setContador(1);
		nd.setUsername(username);
		mapIngresoClientes.put(username, nd);
	}

	public void deleteUsernameIngreso(String username) {
		mapIngresoClientes.remove(username);
	}

	public int contadorIntentoUsernameIngreso(String username) {
		NodoIngresoUsername nd = mapIngresoClientes.get(username);
		return nd.getContador();
	}

	public void incrementarIntentoUsername(String username) {
		NodoIngresoUsername nd = mapIngresoClientes.get(username);
		nd.setDate(new Date());
		nd.incrementar();
	}

	public NodoIngresoUsername getNodoIngresoUsername(String username) {
		return mapIngresoClientes.get(username);
	}

	public boolean isPaginaUsuario(String username, String path) {
		NodoCliente nd = getNodoCliente(username);
		String strPg = path.substring(indexPath);
		return nd.existeUrl(strPg);
	}
	
}
