package com.firstonesoft.mu.business;

import java.util.Date;

import javax.inject.Inject;

import com.firstonesoft.mu.dao.BitacoraDAO;
import com.firstonesoft.mu.model.MuBitacora;

public class BitacoraBL {

	@Inject
	private BitacoraDAO bitacoraDAO;
	
	public void guardar(MuBitacora data) {
		bitacoraDAO.guardar(data);
	}
	
	@SuppressWarnings("rawtypes")
	public void  accionGuardar(String username, String ip, Enum dato, String descripcion) {
		String formulario = dato.toString();
		String accion = "Se adiciono: " + descripcion;

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
	@SuppressWarnings("rawtypes")
	public void  accionActualizar(String username, String ip, Enum dato, String descripcion) {
		String formulario = dato.toString();
		String accion = "Se modifico: " + descripcion;

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
	@SuppressWarnings("rawtypes")
	public void accionEliminar(String username, String ip, Enum dato, String descripcion) {
		String formulario = dato.toString();
		String accion = "Se elimino: " + descripcion;

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
	@SuppressWarnings("rawtypes")
	public void accionLoguear(String username, String ip, Enum dato) {
		String formulario = dato.toString();
		String accion = "Ingreso al Sistema";

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
	@SuppressWarnings("rawtypes")
	public void accionLogout(String username, String ip, Enum dato) {
		String formulario = dato.toString();
		String accion = "Salio del Sistema";

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
	@SuppressWarnings("rawtypes")
	public void accionExpulsar(String username, String ip, Enum dato) {
		String formulario = dato.toString();
		String accion = "Salio del Sistema por expiracion de Tiempo";

		MuBitacora bitacora = new MuBitacora();
		bitacora.setUsuario(username);
		bitacora.setFormulario(formulario);
		bitacora.setDireccionIp(ip);
		bitacora.setAccion(accion);
		bitacora.setFecha(new Date());
		guardar(bitacora);
	}
	
}
