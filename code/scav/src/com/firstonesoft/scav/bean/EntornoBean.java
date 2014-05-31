package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.EntornoBL;
import com.firstonesoft.scav.controller.PhotoController;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.util.ArchivoUtil;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EntornoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(EntornoBean.class);

	@Inject
	private EntornoBL entornoBL;
	
	@ManagedProperty("#{photoController}")
    private PhotoController photoController;

	private List<Entorno> entornos;
	
	private Entorno entorno;
	private String id;
	private boolean edit;

	@PostConstruct
	private void init() {
		try {
			
			cargarEntornos();

			nuevoEntorno();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void cargarEntornos() {
		entornos = entornoBL.obtenerEntornos();
	}
	
	public void guardarEntorno() {

		if (!edit) {	// GUARDAR
			
			FacesUtil.showFacesMessage("Debe seleccionar un Entorno y Editarlo", FacesUtil.SEVERITY_INFO);
			
		} else {		// ACTUALIZAR
			
			if (photoController.isFotoTomada()) {
				entorno.setFoto(ArchivoUtil.obtenerArrayBytes(photoController.getArchivoFoto()));
			}
			
			String error = entornoBL.validarActualizar(entorno);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (entornoBL.actualizar(entorno)) {
				log.info("Se actualizo correctamente el: " + entorno.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				nuevoEntorno();
				cargarEntornos();
			} else {
				FacesUtil.showFacesMessage("Error al actualizar el Entorno", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarEntorno() {
		String idStr = FacesUtil.getParametro("id").toString();
		entorno = entornoBL.obtenerEntornoId(Integer.parseInt(idStr));
		id = idStr;
		
		if (entorno.getFoto() != null) {
			photoController.colocarArchivoBytes(entorno.getFoto());
		}
		edit = true;
	}
	
	public void nuevoEntorno() {
		entorno = new Entorno();
		id = "";
		edit = false;
		
		photoController.setFoto("");
		photoController.setFotoTomada(false);
	}
	
	public String transformarEstado(Boolean sw) {
		return sw ? "green_button.png" : "red_button.png";
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public PhotoController getPhotoController() {
		return photoController;
	}

	public void setPhotoController(PhotoController cameraController) {
		this.photoController = cameraController;
	}
	
	public boolean isEdit() {
		return edit;
	}
	
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getCi() {
		return id;
	}

	public void setCi(String ci) {
		this.id = ci;
	}

	public List<Entorno> getEntornos() {
		return entornos;
	}

	public void setEntornos(List<Entorno> entornos) {
		this.entornos = entornos;
	}

	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
