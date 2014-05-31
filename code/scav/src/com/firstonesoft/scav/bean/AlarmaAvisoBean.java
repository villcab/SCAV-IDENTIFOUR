package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.component.selectoneradio.SelectOneRadio;

import com.firstonesoft.util.FacesUtil;

import firstone.serializable.Aviso;

@ManagedBean
@ViewScoped
public class AlarmaAvisoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(AlarmaAvisoBean.class);

	private List<SelectOneRadio> itemsTipoAvisos;
	private String selectTipoAviso;
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			cargarTiposAvisos();
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void cargarTiposAvisos() {
		itemsTipoAvisos = new ArrayList<SelectOneRadio>();
		
		SelectOneRadio s = new SelectOneRadio();
		s.setLabel("Dirigido a Todos");
		s.setValue(Aviso.DIRIGIDO_TODOS);
		itemsTipoAvisos.add(s);
		
		s = new SelectOneRadio();
		s.setLabel("Dirigido a las Trancas");
		s.setValue(Aviso.DIRIGIDO_TRANCAS);
		itemsTipoAvisos.add(s);
		
		s.setLabel("Dirigido a los Propietarios");
		s.setValue(Aviso.DIRIGIDO_PROPIETARIOS);
		itemsTipoAvisos.add(s);
		
	}

	
	/**
	 * GETTER AND SETTER
	 */
	public List<SelectOneRadio> getItemsTipoAvisos() {
		return itemsTipoAvisos;
	}

	public void setItemsTipoAvisos(List<SelectOneRadio> itemsTipoAvisos) {
		this.itemsTipoAvisos = itemsTipoAvisos;
	}

	public String getSelectTipoAviso() {
		return selectTipoAviso;
	}

	public void setSelectTipoAviso(String selectTipoAviso) {
		this.selectTipoAviso = selectTipoAviso;
	}

	public Integer getIdEntorno() {
		return idEntorno;
	}

	public void setIdEntorno(Integer idEntorno) {
		this.idEntorno = idEntorno;
	}

}
