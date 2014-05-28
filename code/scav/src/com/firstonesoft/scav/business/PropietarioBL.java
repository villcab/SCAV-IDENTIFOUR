package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.dao.PropietarioDAO;
import com.firstonesoft.scav.model.Propietario;

public class PropietarioBL {

	private final Logger log = Logger.getLogger(PropietarioBL.class);

	@Inject
	private PropietarioDAO propitarioDao;

	public void guardar(Propietario data) {
		try {
			propitarioDao.guardar(data);
		} catch (Exception e) {
			log.error("Error al guardar el propitario: ", e);
		}
	}
	
	public void actualizar(Propietario data) {
		try {
			propitarioDao.actualizar(data);
		} catch (Exception e) {
			log.error("Error al actualizar el propitario: ", e);
		}
	}
	
	public void eliminar(Propietario data) {
		try {
			propitarioDao.eliminar(data);
		} catch (Exception e) {
			log.error("Error al eliminar el propitario: ", e);
		}
	}
	
	public Propietario obtenerPropietarioCi(int ci) {
		return propitarioDao.obtenerPropietarioCi(ci);
	}
	
	public List<Propietario> obtenerPropietarios() {
		return propitarioDao.obtenerPropietarios();
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(Propietario data) {
		String error = "";
		if (data.getCi().equals("")) {
			return "Error debe completar el campo CI";
		}
		if (data.getNombres().equals("Error debe completar el campo Nombre"));
		
		return error;
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Propietario data) {
		return "";
	}
	
	
}
