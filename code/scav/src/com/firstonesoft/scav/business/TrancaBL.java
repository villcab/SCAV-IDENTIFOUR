package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.TrancaDAO;
import com.firstonesoft.scav.model.Tranca;

public class TrancaBL {

	@Inject
	private TrancaDAO trancaDAO;

	public boolean guardar(Tranca data) {
		return trancaDAO.guardar(data);
	}
	
	public boolean actualizar(Tranca data) {
		return trancaDAO.actualizar(data);
	}
	
	public boolean eliminar(Integer idTranca) {
		return trancaDAO.eliminar(idTranca);
	}
	
	public Tranca obtenerTrancaId(Integer idTranca) {
		return trancaDAO.obtenerTrancaId(idTranca);
	}
	
	public List<Tranca> obtenerTrancas() {
		return trancaDAO.obtenerTrancas();
	}
	
	public List<Tranca> obtenerTrancasEntorno(Integer idEntorno) {
		return trancaDAO.obtenerTrancasEntorno(idEntorno);
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(Tranca data) {
		
		String error = "";
		
		if (data.getDescripcion().equals("")) {
			return "Error debe completar el campo Descripcion";
		}
		
		return error;
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Tranca data) {
		
		String error = "";
		
		if (data.getDescripcion().equals("")) {
			return "Error debe completar el campo Descripcion";
		}
		
		return error;
	}
	
	
}
