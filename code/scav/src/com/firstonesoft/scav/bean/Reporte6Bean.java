package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.BitacoraGuardiaBL;
import com.firstonesoft.scav.business.GuardiaBL;
import com.firstonesoft.scav.model.BitacoraGuardia;
import com.firstonesoft.scav.model.Guardia;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class Reporte6Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(Reporte6Bean.class);

	@Inject
	private GuardiaBL guardiaBL;
	
	@Inject
	private BitacoraGuardiaBL bitacoraGuardiaBL;
	
	private List<BitacoraGuardia> bitacoraGuardias;
	
	private List<SelectItem> itemGuardias;
	private String selectGuardia;
	
	private String nombresGuardias;
	
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			cargarGuardias();
			bitacoraGuardias = new ArrayList<BitacoraGuardia>();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void cargarGuardias() {
		selectGuardia = "-1";
		itemGuardias = new ArrayList<SelectItem>();
		itemGuardias.add(new SelectItem("-1", "Seleccione un Guardia"));
		List<Guardia> list;
		if (idEntorno == 0) {
			list = guardiaBL.obtenerGuardias();
		} else {
			list = guardiaBL.obtenerGuardiaEntorno(idEntorno);
		}
		for (Guardia g : list) {
			SelectItem s = new SelectItem(g.getCi(), g.getCi() + " | " +g.getNombre() + " " + g.getApellido());
			itemGuardias.add(s);
		}
	}
	
	public void generarReporte() {
		if (selectGuardia.equals("-1")) {
			log.info("Debe seleccionar un guardia para generar el reporte");
			FacesUtil.showFacesMessage("Debe seleccionar un guardia para generar el reporte", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		try {
			bitacoraGuardias = bitacoraGuardiaBL.obtenerBitacorasGuardias(selectGuardia);
			Guardia g = guardiaBL.obtenerGuardiaCi(selectGuardia);
			nombresGuardias = g.getNombre() + " " + g.getApellido();
			
		} catch (Exception e) {
			log.error("Error al generar el reporte");
			FacesUtil.showFacesMessage("Error al generar el reporte", FacesUtil.SEVERITY_ERROR);
		}
		
	}

	/**
	 * GETTER AND SETTER
	 */
	public String getSelectGuardia() {
		return selectGuardia;
	}
	
	public void setSelectGuardia(String selectGuardia) {
		this.selectGuardia = selectGuardia;
	}
	
	public List<SelectItem> getItemGuardias() {
		return itemGuardias;
	}
	
	public void setItemGuardias(List<SelectItem> itemGuardias) {
		this.itemGuardias = itemGuardias;
	}
	
	public List<BitacoraGuardia> getBitacoraGuardias() {
		return bitacoraGuardias;
	}
	
	public void setBitacoraGuardias(List<BitacoraGuardia> bitacoraGuardias) {
		this.bitacoraGuardias = bitacoraGuardias;
	}
	
	public String getNombresGuardias() {
		return nombresGuardias;
	}

}
