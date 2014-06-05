package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.VehiculoDAO;
import com.firstonesoft.scav.model.Vehiculo;
import com.firstonesoft.util.ValidacionUtil;

public class VehiculoBL {

	@Inject
	private VehiculoDAO vehiculoDAO;

	public boolean guardar(Vehiculo data) {
		return vehiculoDAO.guardar(data);
	}
	
	public boolean actualizar(Vehiculo data) {
		return vehiculoDAO.actualizar(data);
	}
	
	public boolean eliminar(Vehiculo data) {
		return vehiculoDAO.eliminar(data);
	}
	
	public Vehiculo obtenerVehiculosPlaca(String placa) {
		return vehiculoDAO.obtenerVehiculosPlaca(placa);
	}
	
	public List<Vehiculo> obtenerVehiculos() {
		return vehiculoDAO.obtenerVehiculos();
	}
	
	public List<Vehiculo> obtenerVehiculosEntorno(Integer idEntorno) {
		return vehiculoDAO.obtenerVehiculosEntorno(idEntorno);
	}
	
	/**
	 * Validacion cuando se quiere registrar uno nuevo
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarNuevo(Vehiculo data) {
		
		String error = "";
		
		if (data.getPlaca().equals("")) {
			return "Error debe completar el campo Placa";
		} else {
			if (!ValidacionUtil.esPlaca(data.getPlaca())) {
				return "Error debe insertar una Placa Correcta";
			} else {
				if (vehiculoDAO.obtenerVehiculosPlaca(data.getPlaca()) != null) {
					return "Ya existe un Vehiculo con la Placa: " + data.getPlaca();
				}
			}
		}
		
		if (data.getMarca().equals("")) {
			return "Error debe completar el campo Marca";
		} else {
			if (!ValidacionUtil.esMarca(data.getMarca())) {
				return "Error debe ingresar una Marca correcta";
			}
		}
		
		if (data.getRfid() == 0) {
			return "Error debe completar el campo RFID";
		}
		
		return error;
	}
	
	/**
	 * Validacion cuando se quiere actualizar uno ya registrado
	 * @param data
	 * @return error; si no tiene error devuelve vacio
	 */
	public String validarActualizar(Vehiculo data) {
		
		String error = "";
		
		if (data.getMarca().equals("")) {
			return "Error debe completar el campo Marca";
		} else {
			if (!ValidacionUtil.esMarca(data.getMarca())) {
				return "Error debe ingresar una Marca correcta";
			}
		}
		
		if (data.getRfid() == 0) {
			return "Error debe completar el campo RFID";
		}
		
		return error;
	}
	
	
}
