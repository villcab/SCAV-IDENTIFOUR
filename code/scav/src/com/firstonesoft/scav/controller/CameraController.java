package com.firstonesoft.scav.controller;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.primefaces.event.CaptureEvent;

import com.firstonesoft.util.ArchivoUtil;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CameraController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(CameraController.class);
	
	private String archivoFoto;
	private String foto;
	private ServletContext servletContext;
	
	@PostConstruct
	private void init() {
		
		try {
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		} catch (Exception e) {
			log.error("Error al cargar el controller: ", e);
		}
		
	}
	
	public void oncapture(CaptureEvent captureEvent) {
        ArchivoUtil.verificarEliminar(archivoFoto);
        foto = "foto" + getNumeroRandomico() + ".png";
        archivoFoto = servletContext.getRealPath(File.separator + "resources"+
        										 File.separator + "images" +
        										 File.separator + "tmp" +
        										 File.separator + foto);
        ArchivoUtil.crearArchivo(archivoFoto, captureEvent.getData());
        FacesUtil.showFacesMessage("Foto capturada correctamente.", FacesUtil.SEVERITY_INFO);
        FacesUtil.setParametro("fotoId", archivoFoto);
    }
	
	private String getNumeroRandomico() {
        int i = (int) (Math.random() * 10000);
        return String.valueOf(i);
    }

	/**
	 * GETTER AND SETTER
	 */
	public String getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(String archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
