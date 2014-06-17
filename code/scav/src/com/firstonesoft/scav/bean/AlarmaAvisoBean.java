package com.firstonesoft.scav.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.component.selectoneradio.SelectOneRadio;

import com.firstonesoft.client.Client;
import com.firstonesoft.client.util.ObjectUtil;
import com.firstonesoft.util.Accion;
import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.Parameters;

import firstone.serializable.Alarma;
import firstone.serializable.Aviso;
import firstone.serializable.Contrato;
import firstone.serializable.Propietario;

@ManagedBean
@ViewScoped
public class AlarmaAvisoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(AlarmaAvisoBean.class);

	private List<SelectOneRadio> itemsTipoAvisos;
	private String selectTipoAviso;
	private Integer idEntorno;
	
	private String mensajeAviso;

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
		
		s = new SelectOneRadio();
		s.setLabel("Dirigido a los Propietarios");
		s.setValue(Aviso.DIRIGIDO_PROPIETARIOS);
		itemsTipoAvisos.add(s);
		
		selectTipoAviso = String.valueOf(Aviso.DIRIGIDO_TODOS);
		
	}
	
	public void enviarAviso() {
		try {
			
			log.info("Enviando Aviso...");
			log.info("Aviso: " + selectTipoAviso + ", Mensaje: " + mensajeAviso);
			
			Aviso aviso = new Aviso();
			aviso.setFecha_hora((new Date()).getTime());
			aviso.setFrom("Web");
			aviso.setMensaje(mensajeAviso);
			if (selectTipoAviso.trim().equalsIgnoreCase("1"))
				aviso.setTo(Aviso.DIRIGIDO_TODOS);
			else if (selectTipoAviso.trim().equals("2"))
				aviso.setTo(Aviso.DIRIGIDO_TRANCAS);
			else if (selectTipoAviso.trim().equals("3"))
				aviso.setTo(Aviso.DIRIGIDO_PROPIETARIOS);
			
			Propietario configurador = new Propietario();
	        configurador.setApellidos("FirstOneSoft");
	        configurador.setCi("000000-1");
	        configurador.setFoto(null);
	        configurador.setNombres("IdentiFour");
	        configurador.setNro_licencia("000000-C");
			
			Client client = new Client(Parameters.CORE_IP, Parameters.CORE_PORT);
			try {
	            client.connectOpened(configurador.getCi(), configurador);
	            
	            
	            Contrato contrato = new Contrato();
	            contrato.setAccion(Accion.AVISO);
	            contrato.setContenido(ObjectUtil.createBytes(aviso));
	            contrato.setId_entorno(idEntorno);
	            try {
	                 client.sendPackage(ObjectUtil.createBytes(contrato));
	            } catch (IOException ex) {
	                log.error("Error al conectarse al Servidor",ex);
	            }
	            
	            client.disconect();
	            
	        } catch (IOException ex) {
	            log.error("Error al procesar por socket",ex);
	        }
			
			FacesUtil.showFacesMessage("Aviso Enviado a: " + selectTipoAviso, FacesUtil.SEVERITY_INFO);
			
			mensajeAviso = "";
		} catch (Exception e) {
			log.error("Error al enviar el Aviso: ", e);
			FacesUtil.showFacesMessage("Error al enviar el Aviso", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	public void enviarAlarma(String prioridad) {
		try {
			log.info("Enviando Alarma...");
			log.info("Alarma: " + prioridad);
			
			Alarma alarma = new Alarma();
			alarma.setEmisor("web");
			if (prioridad.trim().equals("1"))
				alarma.setPrioridad("Rojo");
			if (prioridad.trim().equals("2"))
				alarma.setPrioridad("Amarillo");
			if (prioridad.trim().equals("3"))
				alarma.setPrioridad("Verde");
			
			Propietario configurador = new Propietario();
	        configurador.setApellidos("FirstOneSoft");
	        configurador.setCi("000000-1");
	        configurador.setFoto(null);
	        configurador.setNombres("IdentiFour");
	        configurador.setNro_licencia("000000-C");
			
			Client client = new Client(Parameters.CORE_IP, Parameters.CORE_PORT);
			try {
	            client.connectOpened(configurador.getCi(), configurador);
	            
	            
	            Contrato contrato = new Contrato();
	            contrato.setAccion(Accion.ALARMA);
	            contrato.setContenido(ObjectUtil.createBytes(alarma));
	            contrato.setId_entorno(idEntorno);
	            try {
	                 client.sendPackage(ObjectUtil.createBytes(contrato));
	            } catch (IOException ex) {
	                log.error("Error al conectarse al Servidor",ex);
	            }
	            
	            client.disconect();
	            
	        } catch (IOException ex) {
	            log.error("Error al procesar por socket",ex);
	        }
			
			FacesUtil.showFacesMessage("Alarma Enviado a: " + prioridad, FacesUtil.SEVERITY_INFO);
		} catch (Exception e) {
			log.error("Error al enviar el Aviso: ", e);
			FacesUtil.showFacesMessage("Error al enviar el Alarma", FacesUtil.SEVERITY_ERROR);
		}
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
	
	public String getMensajeAviso() {
		return mensajeAviso;
	}
	
	public void setMensajeAviso(String mensajeAviso) {
		this.mensajeAviso = mensajeAviso;
	}

}
