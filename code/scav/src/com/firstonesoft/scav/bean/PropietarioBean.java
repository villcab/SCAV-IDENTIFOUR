package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.PropietarioBL;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PropietarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(PropietarioBean.class);

	@Inject
	private PropietarioBL propitarioBusiness;

	private List<Propietario> propietarios;
	private Propietario propietario;
	private int ci;
	private boolean edit;

	@PostConstruct
	private void init() {
		try {
			propietarios = propitarioBusiness.obtenerPropietarios();
			nuevoPropitario();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void guardarPropietario() {
		if (!edit) {	// GUARDAR
			
			String error = propitarioBusiness.validarNuevo(propietario);
			if (!error.equals("")) {
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			propietario.setEstado(true);
			propietario.setFoto(null);
			
			propitarioBusiness.guardar(propietario);
			log.info("Se guardo correctamente el: " + propietario.toString());
			
			nuevoPropitario();
			propietarios = propitarioBusiness.obtenerPropietarios();
			
		} else {		// ACTUALIZAR
			
			String error = propitarioBusiness.validarActualizar(propietario);
			if (!error.equals("")) {
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			propitarioBusiness.actualizar(propietario);
			log.info("Se actualizo correctamente el: " + propietario.toString());
			
			nuevoPropitario();
			propietarios = propitarioBusiness.obtenerPropietarios();
		}
	}
	
	public void nuevoPropitario() {
		propietario = new Propietario();
		edit = false;
	}
	
	/**
	 * GETTER AND SETTER
	 */

	public List<Propietario> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

}
