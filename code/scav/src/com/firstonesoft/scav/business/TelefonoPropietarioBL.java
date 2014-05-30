package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.TelefonoPropietarioDAO;
import com.firstonesoft.scav.model.TelefonoPropietario;
import com.firstonesoft.util.ValidacionUtil;

public class TelefonoPropietarioBL {

	@Inject
	private TelefonoPropietarioDAO telefonoPropietarioDAO;

	public boolean guardar(TelefonoPropietario data) {
		return telefonoPropietarioDAO.guardar(data);
	}
	
	public boolean eliminar(Integer data) {
		return telefonoPropietarioDAO.eliminar(data);
	}
	
	public TelefonoPropietario obtenerTelefonoPropietario(Integer telefono) {
		return telefonoPropietarioDAO.obtenerTelefonoPropietario(telefono);
	}
	
	public List<TelefonoPropietario> obtenerTelefonosPropietarios(String ci) {
		return telefonoPropietarioDAO.obtenerTelefonosPropietarios(ci);
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(TelefonoPropietario data) {
		
		String error = "";
		
		if (data.getTelefono() == 0) {
			return "Error debe completar el campo Nro. de Telefono";
		} else {
			if (!ValidacionUtil.sonNumeros(String.valueOf(data.getTelefono()))) {
				return "Error el campo Nro. de Telefono no es valido";
			} else {
				TelefonoPropietario aux = telefonoPropietarioDAO.obtenerTelefonoPropietario(data.getTelefono());
				if (aux != null) {
					return "Ya existe un Nro. de Telefono asignado al Propietario: " + aux.getPropietario().getNombres() + " " + aux.getPropietario().getApellidos();
				}
			}
		}
		
		return error;
	}
	
}
