package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.PropietarioBL;
import com.firstonesoft.scav.business.SincronizadorBL;
import com.firstonesoft.scav.business.TelefonoPropietarioBL;
import com.firstonesoft.scav.controller.PhotoController;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.scav.model.TelefonoPropietario;
import com.firstonesoft.util.ArchivoUtil;
import com.firstonesoft.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PropietarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(PropietarioBean.class);

	@Inject
	private PropietarioBL propietarioBL;
	
	@Inject
	private TelefonoPropietarioBL telefonoPropietarioBL;
	
	@Inject
	private SincronizadorBL sincronizadorBL;
	
	@ManagedProperty("#{photoController}")
    private PhotoController photoController;

	private List<Propietario> propietarios;
	private List<TelefonoPropietario> telefonoPropietarios;
	
	private Propietario propietario;
	private String ci;
	private String telefonoNuevo;
	private boolean edit;
	
	private String telefono;
	
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
			cargarPropietarios();

			nuevoPropietario();
			nuevoTelefonoPropietario();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void cargarPropietarios() {
		if (idEntorno == 0) {
			propietarios = propietarioBL.obtenerPropietarios();
		} else {
			propietarios = propietarioBL.obtenerPropietariosEntorno(idEntorno);
		}
	}
	
	public void guardarPropietario() {

		if (!edit) {	// GUARDAR
			
			if (photoController.isFotoTomada()) {
				propietario.setFoto(ArchivoUtil.obtenerArrayBytes(photoController.getArchivoFoto()));
			}
			
			propietario.setCi(ci);
			String error = propietarioBL.validarNuevo(propietario);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			TelefonoPropietario aux = new TelefonoPropietario();
			aux.setTelefono(Integer.parseInt(telefonoNuevo));
			error = telefonoPropietarioBL.validarNuevo(aux);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				telefonoNuevo = null;
				return;
			}
			
			Entorno eaux = new Entorno();
			eaux.setId(idEntorno);
			propietario.setEntorno(eaux);
			
			propietario.setEstado(true);
			if (propietarioBL.guardar(propietario)) {
				log.info("Se guardo correctamente el: " + propietario.toString());
				
				sincronizadorBL.guardar('I', ci, "propietario", idEntorno);
				
				aux.setPropietario(propietario);
				if (telefonoPropietarioBL.guardar(aux)) {
					log.info("Se guardo correctamente el: TelefonoPropietario[telefono=" + aux.getTelefono() + ", CI=" + aux.getPropietario().getCi() + "]");
					
					sincronizadorBL.guardar('I', telefonoNuevo, "telefono_propietario", idEntorno);
				}
				FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
				
				nuevoPropietario();
				cargarPropietarios();
				
			} else {
				FacesUtil.showFacesMessage("Error al guardar el Propietario", FacesUtil.SEVERITY_ERROR);
			}
			
		} else {		// ACTUALIZAR
			
			if (photoController.isFotoTomada()) {
				propietario.setFoto(ArchivoUtil.obtenerArrayBytes(photoController.getArchivoFoto()));
			}
			
			String error = propietarioBL.validarActualizar(propietario);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (propietarioBL.actualizar(propietario)) {
				log.info("Se actualizo correctamente el: " + propietario.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				sincronizadorBL.guardar('M', ci, "propietario", idEntorno);
				
				nuevoPropietario();
				cargarPropietarios();
				
			} else {
				FacesUtil.showFacesMessage("Error al actualizar el Propietario", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarPropietario() {
		String idStr = FacesUtil.getParametro("ci").toString();
		propietario = propietarioBL.obtenerPropietarioCi(idStr);
		ci = idStr;
		
		photoController.colocarArchivoBytes(propietario.getFoto());
		edit = true;
	}
	
	public void eliminarPropietario() {
			
		String idStr = FacesUtil.getParametro("ci").toString();
		propietario = propietarioBL.obtenerPropietarioCi(idStr);
		ci = idStr;
		
		if (propietarioBL.eliminar(propietario)) {
			log.info("Se ha eliminado correctamente el: " + propietario.toString());
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			sincronizadorBL.guardar('E', ci, "propietario", idEntorno);
			
			nuevoPropietario();
			cargarPropietarios();
			
		} else {
			FacesUtil.showFacesMessage("Error al eliminar el Propietario", FacesUtil.SEVERITY_ERROR);
		}
			
	}
	
	public void nuevoPropietario() {
		propietario = new Propietario();
		ci = "";
		telefonoNuevo = "";
		edit = false;
		
		photoController.setFoto("");
		photoController.setFotoTomada(false);
	}
	
	/**
	 * ABM para TelefonoPropietario
	 */
	public void guardarTelefonoPropietario() {
		
		TelefonoPropietario aux = new TelefonoPropietario();
		aux.setTelefono(Integer.parseInt(telefono));
		String error = telefonoPropietarioBL.validarNuevo(aux);
		if (!error.equals("")) {
			log.error(error);
			FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		Propietario auxP = new Propietario();
		auxP.setCi(ci);
		aux.setPropietario(auxP);
		if (telefonoPropietarioBL.guardar(aux)) {
			log.info("Se guardo correctamente el: TelefonoPropietario[telefono=" + aux.getTelefono() + ", CI=" + aux.getPropietario().getCi() + "]");
			FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
			
			sincronizadorBL.guardar('I', telefono, "telefono_propietario", idEntorno);
			
			nuevoTelefonoPropietario();
			telefonoPropietarios = telefonoPropietarioBL.obtenerTelefonosPropietarios(ci);
			
		} else {
			FacesUtil.showFacesMessage("Error al guardar el Telefono de Propietario", FacesUtil.SEVERITY_ERROR);
		}
		
	}
	
	public void editarTelefonoPropietario() {
		String idStr = FacesUtil.getParametro("telefono").toString();
		telefono = idStr;
	}
	
	public void eliminarTelefonoPropietario() {
		
		if (telefonoPropietarios.size() == 1) {
			log.error("No puede eliminar el telefono por que es su unico numero");
			FacesUtil.showFacesMessage("No se puede eliminar por que es el unico telefono", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		String idStr = FacesUtil.getParametro("telefono").toString();
		telefono = idStr;
		
		if (telefonoPropietarioBL.eliminar(Integer.parseInt(idStr))) {
			log.info("Se ha eliminado correctamente el: TelefonoPropietario[telefono=" + telefono + ", CI=" + ci + "]");
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			sincronizadorBL.guardar('E', telefono, "telefono_propietario", idEntorno);
			
			nuevoTelefonoPropietario();
			telefonoPropietarios = telefonoPropietarioBL.obtenerTelefonosPropietarios(ci);
			
		} else {
			FacesUtil.showFacesMessage("Error al eliminar el Propietario", FacesUtil.SEVERITY_ERROR);
		}
		
	}
	
	public void nuevoTelefonoPropietario() {
		telefono = "";
	}
	
	/**
	 * METODOS ADICIONALES
	 */
	public void cargarTelefonosPropietarios() {
		String idStr = FacesUtil.getParametro("ci").toString();
		ci = idStr;
		telefonoPropietarios = telefonoPropietarioBL.obtenerTelefonosPropietarios(idStr);
	}
	
	public String mostrarTelefonos(String ci) {
		List<TelefonoPropietario> aux = telefonoPropietarioBL.obtenerTelefonosPropietarios(ci);
		String t = aux.toString();
		return t.substring(1, t.length() - 1);
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
	
	public List<TelefonoPropietario> getTelefonosPropietarios() {
		return telefonoPropietarios;
	}
	
	public void setTelefonosPropietarios(List<TelefonoPropietario> telefonosPropietarios) {
		this.telefonoPropietarios = telefonosPropietarios;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
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
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getTelefonoNuevo() {
		return telefonoNuevo;
	}

	public void setTelefonoNuevo(String telefonoNuevo) {
		this.telefonoNuevo = telefonoNuevo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
