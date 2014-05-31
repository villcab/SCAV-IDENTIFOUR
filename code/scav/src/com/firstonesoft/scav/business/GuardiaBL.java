package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.GuardiaDAO;
import com.firstonesoft.scav.model.Guardia;
import com.firstonesoft.util.ValidacionUtil;

public class GuardiaBL {

	@Inject
	private GuardiaDAO guardiaDAO;

	public boolean guardar(Guardia data) {
		return guardiaDAO.guardar(data);
	}
	
	public boolean actualizar(Guardia data) {
		return guardiaDAO.actualizar(data);
	}
	
	public boolean eliminar(Guardia data) {
		return guardiaDAO.eliminar(data);
	}
	
	public Guardia obtenerGuardiaCi(String ci) {
		return guardiaDAO.obtenerGuardiaCi(ci);
	}
	
	public List<Guardia> obtenerGuardias() {
		return guardiaDAO.obtenerGuardias();
	}
	
	public List<Guardia> obtenerGuardiaEntorno(Integer idEntorno) {
		return guardiaDAO.obtenerGuardiaEntorno(idEntorno);
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(Guardia data) {
		
		String error = "";
		
		data.setCi(data.getCi().trim());
		if (data.getCi().equals("")) {
			return "Error debe completar el campo CI";
		} else {
			if (!ValidacionUtil.sonLetrasNumeros(data.getCi())) {
				return "Error el campo CI solo acepta Letras y Numeros";
			} else {
				if (guardiaDAO.obtenerGuardiaCi(data.getCi()) != null) {
					return "Ya existe un Guardia con el CI: " + data.getCi();
				}
			}
		}
		
		if (data.getNombre().equals("")) {
			return "Error debe completar el campo Nombres";
		} else {
			if (!ValidacionUtil.sonLetrasPalabras(data.getNombre())) {
				return "Error el campo Nombres no es valido";
			}
		}
		
		if (data.getApellido().equals("")) {
			return "Error debe completar el campo Apellidos";
		} else {
			if (!ValidacionUtil.sonLetrasPalabras(data.getApellido())) {
				return "Error el campo Apellidos no es valido";
			}
		}
		
		if (data.getPassword().equals("")) {
			return "Error debe completar el campo Contrase&ntilde;a";
		}
		
		return error;
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Guardia data) {
		
		String error = "";
		
		if (data.getNombre().equals("")) {
			return "Error debe completar el campo Nombres";
		} else {
			if (!ValidacionUtil.sonLetrasPalabras(data.getNombre())) {
				return "Error el campo Nombres no es valido";
			}
		}
		
		if (data.getApellido().equals("")) {
			return "Error debe completar el campo Apellidos";
		} else {
			if (!ValidacionUtil.sonLetrasPalabras(data.getApellido())) {
				return "Error el campo Apellidos no es valido";
			}
		}
		
		if (data.getPassword().equals("")) {
			return "Error debe completar el campo Contrase&ntilde;a";
		}
		
		return error;
	}
	
	
}
