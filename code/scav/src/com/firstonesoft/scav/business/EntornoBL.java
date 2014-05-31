package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.EntornoDAO;
import com.firstonesoft.scav.model.Entorno;

public class EntornoBL {

	@Inject
	private EntornoDAO entornoDAO;
	
	public boolean actualizar(Entorno data) {
		return entornoDAO.actualizar(data);
	}
	
	public Entorno obtenerEntornoId(Integer idEntorno) {
		return entornoDAO.obtenerEntornoId(idEntorno);
	}
	
	public List<Entorno> obtenerEntornos() {
		return entornoDAO.obtenerEntornos();
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Entorno data) {
		
		String error = "";
		
		if (data.getNombre().equals("")) {
			return "Error debe completar el campo Nombres";
		}
		
		if (data.getUbicacion().equals("")) {
			return "Error debe completar el campo Ubicacion";
		}
		
		return error;
	}
	
}
