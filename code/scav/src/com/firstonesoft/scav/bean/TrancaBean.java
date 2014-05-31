package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.TrancaBL;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.scav.model.Tranca;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TrancaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(TrancaBean.class);

	@Inject
	private TrancaBL trancaBL;

	private List<Tranca> trancas;
	
	private Tranca tranca;
	private String id;
	private boolean edit;
	
	private String selectTipo;
	
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
			cargarTrancas();
			nuevoTranca();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void cargarTrancas() {
		if (idEntorno == 0) {
			trancas = trancaBL.obtenerTrancas();
		} else {
			trancas = trancaBL.obtenerTrancasEntorno(idEntorno);
		}
	}
	
	public void guardarTranca() {

		if (!edit) {	// GUARDAR
			
			String error = trancaBL.validarNuevo(tranca);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			Entorno eaux = new Entorno();
			eaux.setId(idEntorno);
			
			log.info("entorno: " + idEntorno);
			log.info("tipo: " + selectTipo);
			
			tranca.setEntorno(eaux);
			tranca.setTipo(selectTipo);
			
			if (trancaBL.guardar(tranca)) {
				log.info("Se guardo correctamente el: " + tranca.toString());
				FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
				nuevoTranca();
				cargarTrancas();
			} else {
				FacesUtil.showFacesMessage("Error al guardar la Tranca", FacesUtil.SEVERITY_ERROR);
			}
			
		} else {		// ACTUALIZAR
			
			String error = trancaBL.validarActualizar(tranca);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			tranca.setTipo(selectTipo);
			if (trancaBL.actualizar(tranca)) {
				log.info("Se actualizo correctamente el: " + tranca.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				nuevoTranca();
				cargarTrancas();
			} else {
				FacesUtil.showFacesMessage("Error al actualizar la Tranca", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarTranca() {
		String idStr = FacesUtil.getParametro("id").toString();
		id = idStr;
		tranca = trancaBL.obtenerTrancaId(Integer.parseInt(id));
		edit = true;
	}
	
	public void eliminarTranca() {
			
		String idStr = FacesUtil.getParametro("id").toString();
		id = idStr;
		tranca = trancaBL.obtenerTrancaId(Integer.parseInt(id));
		
		Tranca tt = trancaBL.obtenerTrancaId(Integer.parseInt(id));
		if (trancaBL.eliminar(Integer.parseInt(id))) {
			log.info("Se ha eliminado correctamente el: " + tt.toString());
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			nuevoTranca();
			cargarTrancas();
		} else {
			FacesUtil.showFacesMessage("Error al eliminar la Tranca", FacesUtil.SEVERITY_ERROR);
		}
			
	}
	
	public void nuevoTranca() {
		tranca = new Tranca();
		id = "";
		edit = false;
	}
	
	/**
	 * GETTER AND SETTER
	 */
	public void setsdcsdcEdit(boolean edit) {
		this.edit = edit;
	}

	public TrancaBL getTrancaBL() {
		return trancaBL;
	}

	public void setTrancaBL(TrancaBL trancaBL) {
		this.trancaBL = trancaBL;
	}

	public List<Tranca> getTrancas() {
		return trancas;
	}

	public void setTrancas(List<Tranca> trancas) {
		this.trancas = trancas;
	}

	public Tranca getTranca() {
		return tranca;
	}

	public void setTranca(Tranca tranca) {
		this.tranca = tranca;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
	public String getSelectTipo() {
		return selectTipo;
	}
	
	public void setSelectTipo(String selectTipo) {
		this.selectTipo = selectTipo;
	}

	
}
