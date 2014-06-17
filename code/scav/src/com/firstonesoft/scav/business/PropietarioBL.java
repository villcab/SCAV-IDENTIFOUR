package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.PropietarioDAO;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.util.ValidacionUtil;

public class PropietarioBL {

	@Inject
	private PropietarioDAO propietarioDao;

	public boolean guardar(Propietario data) {
		return propietarioDao.guardar(data);
	}
	
	public boolean actualizar(Propietario data) {
		return propietarioDao.actualizar(data);
	}
	
	public boolean eliminar(Propietario data) {
		return propietarioDao.eliminar(data);
	}
	
	public Propietario obtenerPropietarioCi(String ci) {
		return propietarioDao.obtenerPropietarioCi(ci);
	}
	
	public List<Propietario> obtenerPropietarios() {
		return propietarioDao.obtenerPropietarios();
	}
	
	public List<Propietario> obtenerPropietariosEntorno(Integer idEntorno) {
		return propietarioDao.obtenerPropietariosEntorno(idEntorno);
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(Propietario data) {
		
		String error = "";
		
		data.setCi(data.getCi().trim());
		if (data.getCi().equals("")) {
			return "Error debe completar el campo CI";
		} else {
			if (!ValidacionUtil.esCI(data.getCi())) {
				return "Error el campo CI solo acepta Letras y Numeros";
			} else {
				if (propietarioDao.obtenerPropietarioCi(data.getCi()) != null) {
					return "Ya existe un Propietario con el CI: " + data.getCi();
				}
			}
		}
		
		if (data.getNombres().equals("")) {
			return "Error debe completar el campo Nombres";
		} else {
			if (!ValidacionUtil.esNombres(data.getNombres())) {
				return "Error el campo Nombres no es valido";
			}
		}
		
		if (data.getApellidos().equals("")) {
			return "Error debe completar el campo Apellidos";
		} else {
			if (!ValidacionUtil.esApellidos(data.getApellidos())) {
				return "Error el campo Apellidos no es valido";
			}
		}
		
		if (data.getFoto() == null) {
			return "Error debe sacar una foto";
		}
		
		return error;
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Propietario data) {
		
		String error = "";
		
		if (data.getNombres().equals("")) {
			return "Error debe completar el campo Nombres";
		} else {
			if (!ValidacionUtil.esNombres(data.getNombres())) {
				return "Error el campo Nombres no es valido";
			}
		}
		
		if (data.getApellidos().equals("")) {
			return "Error debe completar el campo Apellidos";
		} else {
			if (!ValidacionUtil.esApellidos(data.getApellidos())) {
				return "Error el campo Apellidos no es valido";
			}
		}
		
		return error;
	}
	
	
}
