package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.VehiculoBL;
import com.firstonesoft.scav.controller.CameraController;
import com.firstonesoft.scav.model.Vehiculo;
import com.firstonesoft.util.ArchivoUtil;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VehiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(VehiculoBean.class);

	@Inject
	private VehiculoBL vehiculoBL;
	
	@ManagedProperty("#{cameraController}")
    private CameraController cameraController;

	private List<Vehiculo> vehiculos;
	
	private Vehiculo vehiculo;
	private String placa;
	private boolean edit;

	@PostConstruct
	private void init() {
		try {
			vehiculos = vehiculoBL.obtenerVehiculos();
			nuevoVehiculo();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void guardarVehiculo() {

		if (!edit) {	// GUARDAR
			
			if (cameraController.isFotoTomada()) {
				vehiculo.setFoto(ArchivoUtil.obtenerArrayBytes(cameraController.getArchivoFoto()));
			}
			
			vehiculo.setPlaca(placa);
			String error = vehiculoBL.validarNuevo(vehiculo);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			vehiculo.setEstado(true);
			if (vehiculoBL.guardar(vehiculo)) {
				log.info("Se guardo correctamente el: " + vehiculo.toString());
				FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
				nuevoVehiculo();
				vehiculos = vehiculoBL.obtenerVehiculos();
			} else {
				FacesUtil.showFacesMessage("Error al guardar el Vehiculo", FacesUtil.SEVERITY_ERROR);
			}
			
		} else {		// ACTUALIZAR
			
			if (cameraController.isFotoTomada()) {
				vehiculo.setFoto(ArchivoUtil.obtenerArrayBytes(cameraController.getArchivoFoto()));
			}
			
			String error = vehiculoBL.validarActualizar(vehiculo);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (vehiculoBL.actualizar(vehiculo)) {
				log.info("Se actualizo correctamente el: " + vehiculo.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				nuevoVehiculo();
				vehiculos = vehiculoBL.obtenerVehiculos();
			} else {
				FacesUtil.showFacesMessage("Error al actualizar el Vehiculo", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarVehiculo() {
		String idStr = FacesUtil.getParametro("placa").toString();
		vehiculo =vehiculoBL.obtenerVehiculosPlaca(idStr);
		placa = idStr;
		
		cameraController.colocarArchivoBytes(vehiculo.getFoto());
		edit = true;
	}
	
	public void eliminarVehiculo() {
			
		String idStr = FacesUtil.getParametro("placa").toString();
		vehiculo = vehiculoBL.obtenerVehiculosPlaca(idStr);
		placa = idStr;
		
		if (vehiculoBL.eliminar(vehiculo)) {
			log.info("Se ha eliminado correctamente el: " + vehiculo.toString());
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			nuevoVehiculo();
			vehiculos = vehiculoBL.obtenerVehiculos();
		} else {
			FacesUtil.showFacesMessage("Error al eliminar el Vehiculo", FacesUtil.SEVERITY_ERROR);
		}
			
	}
	
	public void nuevoVehiculo() {
		vehiculo = new Vehiculo();
		placa = "";
		edit = false;
		
		cameraController.setFoto("");
		cameraController.setFotoTomada(false);
	}
	
	/**
	 * GETTER AND SETTER
	 */

	public CameraController getCameraController() {
		return cameraController;
	}

	public void setCameraController(CameraController cameraController) {
		this.cameraController = cameraController;
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
