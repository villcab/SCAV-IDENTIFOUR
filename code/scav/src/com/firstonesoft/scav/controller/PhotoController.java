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
import org.primefaces.event.FileUploadEvent;

import com.firstonesoft.util.ArchivoUtil;

@ManagedBean(name = "photoController")
@ViewScoped
public class PhotoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(PhotoController.class);
	
	private String archivoFoto;
	private String foto;
	private ServletContext servletContext;
	private boolean fotoTomada;
	
	@PostConstruct
	private void init() {
		
		try {
			fotoTomada = false;
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
        fotoTomada = true;
    }
	
	public void onupload(FileUploadEvent uploadEvent) {
		ArchivoUtil.verificarEliminar(archivoFoto);
        foto = "foto" + getNumeroRandomico() + ".png";
        archivoFoto = servletContext.getRealPath(File.separator + "resources"+
        										 File.separator + "images" +
        										 File.separator + "tmp" +
        										 File.separator + foto);
        ArchivoUtil.crearArchivo(archivoFoto, uploadEvent.getFile().getContents());
        fotoTomada = true;
	}
	
	public void colocarArchivoBytes(byte [] array) {
		ArchivoUtil.verificarEliminar(archivoFoto);
        foto = "foto" + getNumeroRandomico() + ".png";
        archivoFoto = servletContext.getRealPath(File.separator + "resources"+
        										 File.separator + "images" +
        										 File.separator + "tmp" +
        										 File.separator + foto);
        ArchivoUtil.crearArchivo(archivoFoto, array);
        fotoTomada = false;
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
	
	public void setFotoTomada(boolean fotoTomada) {
		this.fotoTomada = fotoTomada;
	}
	
	public boolean isFotoTomada() {
		return fotoTomada;
	}
	
}
