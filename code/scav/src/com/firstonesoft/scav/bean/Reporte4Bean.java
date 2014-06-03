package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.PropietarioBL;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.util.FacesUtil;

import firstone.serializable.Vehiculo;

@ManagedBean
@SessionScoped
public class Reporte4Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(Reporte4Bean.class);
	
	@Inject
	private PropietarioBL propietarioBL;
	
	private List<SelectItem> itemPropietarios;
	private String selectPropietario;
	
	private int idEntorno;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			cargarPropietarios();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	private void cargarPropietarios() {
		selectPropietario = "-1";
		itemPropietarios = new ArrayList<SelectItem>();
		itemPropietarios.add(new SelectItem("-1", "Seleccione un Propietario"));
		List<Propietario> list;
		if (idEntorno == 0) {
			list = propietarioBL.obtenerPropietarios();
		} else {
			list = propietarioBL.obtenerPropietariosEntorno(idEntorno);
		}
		for (Propietario v : list) {
			SelectItem s = new SelectItem(v.getCi(), v.getCi() + " | " + v.getNombres() + " " + v.getApellidos());
			itemPropietarios.add(s);
		}
	}
	
	/**
	 * REPORTE 4
	 * Lista todos los vehiculos que tiene un propietario
	 * 
	 * @param ci
	 */
	public void generarReporte(ActionEvent event) {

		if (validate()) {
			try {
				
				Propietario p = propietarioBL.obtenerPropietarioCi(selectPropietario);
				List<Vehiculo> list = new ArrayList<Vehiculo>();
				for (com.firstonesoft.scav.model.Vehiculo v : p.getVehiculos()) {
					list.add(new Vehiculo(v.getPlaca(), v.getMarca(), v.getModelo(), v.getRfid()+"", new ImageIcon(v.getFoto()).getImage()));
				}
				
	        	String ruta = servletContext.getRealPath(File.separator + "resources" +
														 File.separator + "reports" +
														 File.separator + "scav_report4.jasper");
	        	
	        	JRBeanCollectionDataSource jrcds = new JRBeanCollectionDataSource(list);
	        	
	        	Map<String, Object> parameters = new HashMap<String, Object>();
	        	
	        	parameters.put("ci", p.getCi());
	        	parameters.put("nombre", p.getNombres());
	        	parameters.put("apellidos", p.getApellidos());
	        	parameters.put("fotoPropietario", new ImageIcon(p.getFoto()).getImage());
	        	
	    		byte[] bytes = JasperRunManager.runReportToPdf(ruta, parameters, jrcds);
	    		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    		response.setContentType("application/pdf");
	    		response.setContentLength(bytes.length);
	    		ServletOutputStream outStream = response.getOutputStream();
	    		outStream.write(bytes, 0 , bytes.length);
	    		outStream.flush();
	    		outStream.close();
	    			
	    		FacesContext.getCurrentInstance().responseComplete();
	    		
	        } catch(Exception e) {
	            
	        	log.error("Error al generar el reporte: ", e);
	        	FacesUtil.showFacesMessage("Error al generar el reporte", FacesUtil.SEVERITY_ERROR);
	        }
		}
		
	}
	
	private boolean validate() {
		if (selectPropietario.equals("-1")) {
			FacesUtil.showFacesMessage("Seleccione un Propietario para Generar el Reporte", FacesUtil.SEVERITY_ERROR);
			return false;
		}
		
		return true;
	}
	
	/**
	 * GETTER AND SETTER
	 */	
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
	
}
