package com.firstonesoft.scav.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.AdministradorEntornoBL;
import com.firstonesoft.scav.model.AdministradorEntorno;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AdministradorEntornoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(AdministradorEntornoBean.class);
	
	@Inject
	private AdministradorEntornoBL administradorEntornoBL;
	
	private AdministradorEntorno perfil;
	private String ci;
	private boolean edit;
	private String email;
	
	private String passwordActual;
	private String passwordNuevo;
	private String passwordNuevoRep;

	@PostConstruct
	private void init() {
		try {
			
			email = FacesUtil.getSessionAttribute("TEMP$USER_NAME").toString();
			perfil = administradorEntornoBL.obtenerAdminitradorEntornoEmail(email);
			ci = perfil.getCi();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void cargarEdicion() {
		edit = true;
	}
	
	public void cancelarEdicion() {
		edit = false;
	}
	
	public void editarAdministradorEntorno() {
		try {
			perfil.setCi(ci);
			administradorEntornoBL.editar(perfil);
			
			perfil = administradorEntornoBL.obtenerAdminitradorEntornoCI(ci);
			
			edit = false;
			
			log.info("Datos actualizados correctamente: " + perfil.toString());
			FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
			
		} catch (Exception e) {
			log.error("Error al actualizar los datos de: ", e);
			FacesUtil.showFacesMessage("Error al actualizar los datos", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	public void cambiarPassword() {
		try {
			if (!passwordActual.equals(perfil.getPassword())) {
				log.error("La contraseña ingresada no coincide con la actual");
				FacesUtil.showFacesMessage("La contraseña ingresada no coincide con la actual", FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (!passwordNuevo.equals(passwordNuevoRep)) {
				log.error("Las contraseñas no coinciden");
				FacesUtil.showFacesMessage("Las contraseñas no coinciden", FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			perfil.setPassword(passwordNuevo);
			administradorEntornoBL.editar(perfil);
			
			passwordActual = "";
			passwordNuevo = "";
			passwordNuevoRep = "";
			
			log.info("La contraseña se actualizo correctamente");
			FacesUtil.showFacesMessage("La contraseña se actualizo correctamente", FacesUtil.SEVERITY_INFO);
			
		} catch (Exception e) {
			log.error("Error al cambiar la contraseña");
			FacesUtil.showFacesMessage("Error al cambiar la contraseña", FacesUtil.SEVERITY_ERROR);
		}
		
		
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public AdministradorEntorno getPerfil() {
		return perfil;
	}

	public void setPerfil(AdministradorEntorno perfil) {
		this.perfil = perfil;
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

	public String getPasswordActual() {
		return passwordActual;
	}

	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
	}

	public String getPasswordNuevo() {
		return passwordNuevo;
	}

	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}

	public String getPasswordNuevoRep() {
		return passwordNuevoRep;
	}

	public void setPasswordNuevoRep(String passwordNuevoRep) {
		this.passwordNuevoRep = passwordNuevoRep;
	}
	
}
