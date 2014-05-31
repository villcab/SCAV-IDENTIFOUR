package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.GuardiaBL;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.scav.model.Guardia;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class GuardiaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(GuardiaBean.class);

	@Inject
	private GuardiaBL guardiaBL;

	private List<Guardia> guardias;
	
	private Guardia guardia;
	private String ci;
	private boolean edit;
	
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
			cargarGuardias();
			nuevoGuardia();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void cargarGuardias() {
		if (idEntorno == 0) {
			guardias = guardiaBL.obtenerGuardias();
		} else {
			guardias = guardiaBL.obtenerGuardiaEntorno(idEntorno);
		}
	}
	
	public void guardarGuardia() {

		if (!edit) {	// GUARDAR
			
			guardia.setCi(ci);
			String error = guardiaBL.validarNuevo(guardia);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			Entorno eaux = new Entorno();
			eaux.setId(idEntorno);
			guardia.setEntorno(eaux);
			
			guardia.setEstado(true);
			if (guardiaBL.guardar(guardia)) {
				log.info("Se guardo correctamente el: " + guardia.toString());
				FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
				nuevoGuardia();
				cargarGuardias();
			} else {
				FacesUtil.showFacesMessage("Error al guardar el Guardia", FacesUtil.SEVERITY_ERROR);
			}
			
		} else {		// ACTUALIZAR
			
			String error = guardiaBL.validarActualizar(guardia);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (guardiaBL.actualizar(guardia)) {
				log.info("Se actualizo correctamente el: " + guardia.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				nuevoGuardia();
				cargarGuardias();
			} else {
				FacesUtil.showFacesMessage("Error al actualizar el Guardia", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarGuardia() {
		String idStr = FacesUtil.getParametro("ci").toString();
		guardia = guardiaBL.obtenerGuardiaCi(idStr);
		ci = idStr;
		edit = true;
	}
	
	public void eliminarGuardia() {
			
		String idStr = FacesUtil.getParametro("ci").toString();
		guardia = guardiaBL.obtenerGuardiaCi(idStr);
		ci = idStr;
		
		if (guardiaBL.eliminar(guardia)) {
			log.info("Se ha eliminado correctamente el: " + guardia.toString());
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			nuevoGuardia();
			cargarGuardias();
		} else {
			FacesUtil.showFacesMessage("Error al eliminar el Guardia", FacesUtil.SEVERITY_ERROR);
		}
			
	}
	
	public void nuevoGuardia() {
		guardia = new Guardia();
		ci = "";
		edit = false;
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public List<Guardia> getGuardias() {
		return guardias;
	}

	public void setGuardias(List<Guardia> guardias) {
		this.guardias = guardias;
	}

	public Guardia getGuardia() {
		return guardia;
	}

	public void setGuardia(Guardia guardia) {
		this.guardia = guardia;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

}
