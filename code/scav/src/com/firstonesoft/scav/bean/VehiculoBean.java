package com.firstonesoft.scav.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.firstonesoft.scav.business.PropietarioBL;
import com.firstonesoft.scav.business.SincronizadorBL;
import com.firstonesoft.scav.business.VehiculoBL;
import com.firstonesoft.scav.controller.PhotoController;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.scav.model.Vehiculo;
import com.firstonesoft.util.ArchivoUtil;
import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.ValidacionUtil;

@ManagedBean
@ViewScoped
public class VehiculoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(VehiculoBean.class);

	@Inject
	private VehiculoBL vehiculoBL;
	
	@Inject
	private PropietarioBL propietarioBL;
	
	@Inject
	private SincronizadorBL sincronizadorBL;
	
	@ManagedProperty("#{photoController}")
    private PhotoController photoController;

	private List<Vehiculo> vehiculos;
	
	private Vehiculo vehiculo;
	private String placa;
	private boolean edit;
	
	private List<SelectItem> itemPropietarios;
	private String selectPropietario;
	
	private DualListModel<String> propietarioPickList;
	private Vehiculo vehiculoSelect;
	
	private Integer idEntorno;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
			cargarVehiculos();
			nuevoVehiculo();
			cargarPropietariosCombo();
			
			List<String> source = new ArrayList<String>();
			List<String> target = new ArrayList<String>();
			propietarioPickList = new DualListModel<String>(source, target);
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void cargarVehiculos() {
		if (idEntorno == 0) {
			vehiculos = vehiculoBL.obtenerVehiculos();
		} else {
			vehiculos = vehiculoBL.obtenerVehiculosEntorno(idEntorno);
		}
	}
	
	public void guardarVehiculo() {

		if (!edit) {	// GUARDAR
			
			if (photoController.isFotoTomada()) {
				vehiculo.setFoto(ArchivoUtil.obtenerArrayBytes(photoController.getArchivoFoto()));
			}
			
			if (selectPropietario.equals("-1")) {
				log.info("Debe seleccionar un Propietario");
				FacesUtil.showFacesMessage("Debe seleccionar un Propietaro", FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			vehiculo.setPlaca(placa);
			String error = vehiculoBL.validarNuevo(vehiculo);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			Entorno eaux = new Entorno();
			eaux.setId(idEntorno);
			vehiculo.setEntorno(eaux);
			
			vehiculo.setEstado(true);
			if (vehiculoBL.guardar(vehiculo)) {
				
				log.info("Se guardo correctamente el: " + vehiculo.toString());
				FacesUtil.showFacesMessage("Datos guardado correctamente", FacesUtil.SEVERITY_INFO);
				
				sincronizadorBL.guardar('I', placa, "vehiculo", idEntorno);
				
				Propietario paux = propietarioBL.obtenerPropietarioCi(selectPropietario);
				List<Vehiculo> vs = paux.getVehiculos();
				vs.add(vehiculo);
				paux.setVehiculos(vs);
				if (propietarioBL.actualizar(paux)) {
					log.info("Se guardo el Vehiculo: "+ vehiculo.getPlaca()+ ", para el Propietario: " + paux.getCi());
					
					sincronizadorBL.guardar('I', placa + "," + paux.getCi(), "propietario_vehiculo", idEntorno);
				}
				
				nuevoVehiculo();
				cargarVehiculos();
			} else {
				FacesUtil.showFacesMessage("Error al guardar el Vehiculo", FacesUtil.SEVERITY_ERROR);
			}
			
		} else {		// ACTUALIZAR
			
			if (photoController.isFotoTomada()) {
				vehiculo.setFoto(ArchivoUtil.obtenerArrayBytes(photoController.getArchivoFoto()));
			}
			
			String error = vehiculoBL.validarActualizar(vehiculo);
			if (!error.equals("")) {
				log.error(error);
				FacesUtil.showFacesMessage(error, FacesUtil.SEVERITY_ERROR);
				return;
			}
			
			if (vehiculoBL.actualizar(vehiculo)) {
				log.info("Se actualizo correctamente el: " + vehiculo.toString());
				FacesUtil.showFacesMessage("Datos actualizados correctamente", FacesUtil.SEVERITY_INFO);
				
				sincronizadorBL.guardar('M', placa, "vehiculo", idEntorno);
				
				nuevoVehiculo();
				cargarVehiculos();
			} else {
				FacesUtil.showFacesMessage("Error al actualizar el Vehiculo", FacesUtil.SEVERITY_ERROR);
			}

		}
	}
	
	public void editarVehiculo() {
		String idStr = FacesUtil.getParametro("placa").toString();
		vehiculo =vehiculoBL.obtenerVehiculosPlaca(idStr);
		placa = idStr;
		
		if (vehiculo.getFoto() != null) {
			photoController.colocarArchivoBytes(vehiculo.getFoto());
		}
		edit = true;
	}
	
	public void eliminarVehiculo() {
			
		String idStr = FacesUtil.getParametro("placa").toString();
		vehiculo = vehiculoBL.obtenerVehiculosPlaca(idStr);
		placa = idStr;
		
		if (vehiculoBL.eliminar(vehiculo)) {
			log.info("Se ha eliminado correctamente el: " + vehiculo.toString());
			FacesUtil.showFacesMessage("Datos eliminados correctamente", FacesUtil.SEVERITY_INFO);
			
			sincronizadorBL.guardar('E', placa, "vehiculo", idEntorno);
			
			nuevoVehiculo();
			cargarVehiculos();
		} else {
			FacesUtil.showFacesMessage("Error al eliminar el Vehiculo", FacesUtil.SEVERITY_ERROR);
		}
			
	}
	
	public void nuevoVehiculo() {
		vehiculo = new Vehiculo();
		placa = "";
		edit = false;
		
		selectPropietario = "-1";
		
		photoController.setFoto("");
		photoController.setFotoTomada(false);
	}
	
	/**
	 * METODOS ADICIONALES
	 */
	/*
	 * Carga los propietarios e un SelectOneMenu
	 */
	public void cargarPropietariosCombo() {
		itemPropietarios = new ArrayList<SelectItem>();
		itemPropietarios.add(new SelectItem("-1", "Seleccione un Propietario"));
		List<Propietario> list = propietarioBL.obtenerPropietariosEntorno(idEntorno);
		for (Propietario p : list) {
			SelectItem s = new SelectItem(p.getCi(), p.getCi() + " | " + p.getNombres() + " " + p.getApellidos());
			itemPropietarios.add(s);
		}
	}
	
	/**
	 * Cargar los propietarios en un vehiculo 
	 */
	public void cargarPropietariosPickList() {

		try {
			
			String idStr = FacesUtil.getParametro("placa").toString();
			vehiculoSelect = vehiculoBL.obtenerVehiculosPlaca(idStr);
			placa = idStr;
			
			List<String> source = new ArrayList<String>();
			for (Propietario p: propietarioBL.obtenerPropietariosEntorno(idEntorno)) {
				String s = p.getCi() + " | " + p.getNombres() + " " + p.getApellidos();
				source.add(s);
			}
			
			List<String> target = new ArrayList<String>();
			for (Propietario p: vehiculoSelect.getPropietarios()) {
				String s = p.getCi() + " | " + p.getNombres() + " " + p.getApellidos();
				target.add(s);
			}
			
			source.removeAll(target);
			
			propietarioPickList = new DualListModel<String>(source, target);
			
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		
	}
	
	public void onTransfer(TransferEvent event) {
		
		if (event.isAdd()) {
			for (Object o: event.getItems().toArray()) {
				String ciaux = ValidacionUtil.obtenerNumeros(o.toString());
				Propietario paux = propietarioBL.obtenerPropietarioCi(ciaux);
				List<Vehiculo> list = paux.getVehiculos();
				list.add(vehiculoSelect);
				propietarioBL.actualizar(paux);
				String msg = "Se agrego al Vehiculo con Placa: " + vehiculoSelect.getPlaca() + ", el Propietario con CI: " + ciaux;
				log.info(msg);
				FacesUtil.showFacesMessage(msg, FacesUtil.SEVERITY_INFO);
				
				sincronizadorBL.guardar('I', vehiculoSelect.getPlaca() + "," + ciaux, "propietario_vehiculo", idEntorno);
			}
		} else {
			for (Object o: event.getItems().toArray()) {
				String ciaux = ValidacionUtil.obtenerNumeros(o.toString());
				Propietario paux = propietarioBL.obtenerPropietarioCi(ciaux);
				List<Vehiculo> list = paux.getVehiculos();
				list.remove(vehiculoSelect);
				propietarioBL.actualizar(paux);
				String msg = "Se quito al Vehiculo con Placa: " + vehiculoSelect.getPlaca() + ", el Propietario con CI: " + ciaux;
				log.info(msg);
				FacesUtil.showFacesMessage(msg, FacesUtil.SEVERITY_INFO);
				
				sincronizadorBL.guardar('E', vehiculoSelect.getPlaca() + "," + ciaux, "propietario_vehiculo", idEntorno);
			}
		}
		
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

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public List<SelectItem> getItemPropietarios() {
		return itemPropietarios;
	}

	public void setItemPropietarios(List<SelectItem> itemPropietarios) {
		this.itemPropietarios = itemPropietarios;
	}

	public String getSelectPropietario() {
		return selectPropietario;
	}

	public void setSelectPropietario(String selectPropietario) {
		this.selectPropietario = selectPropietario;
	}

	public Vehiculo getVehiculoSelect() {
		return vehiculoSelect;
	}

	public void setVehiculoSelect(Vehiculo vehiculoSelect) {
		this.vehiculoSelect = vehiculoSelect;
	}
	
	public DualListModel<String> getPropietarioPickList() {
		return propietarioPickList;
	}
	
	public void setPropietarioPickList(DualListModel<String> propietarioPickList) {
		this.propietarioPickList = propietarioPickList;
	}

}
