package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.VehiculoBL;
import com.firstonesoft.scav.model.Vehiculo;
import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.ServiceProvider;

@ManagedBean
@RequestScoped
public class Reporte3Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(Reporte3Bean.class);

	@Inject
	private VehiculoBL vehiculoBL;
	
	//reporte 2
	private Date fechaInicio;
	private Date fechaFin;
	
	private List<SelectItem> itemPlacas;
	private String selectPlaca;
	
	private int idEntorno;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {

			fechaInicio = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(fechaInicio);
			gc.add(GregorianCalendar.MONTH, 1);
			fechaFin = gc.getTime();
			selectPlaca = "";
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
			cargarPlacas();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	private void cargarPlacas() {
		itemPlacas = new ArrayList<SelectItem>();
		itemPlacas.add(new SelectItem("-1", "Seleccione un Vehiculo"));
		List<Vehiculo> list;
		if (idEntorno == 0) {
			list = vehiculoBL.obtenerVehiculos();
		} else {
			list = vehiculoBL.obtenerVehiculosEntorno(idEntorno);
		}
		for (Vehiculo v : list) {
			SelectItem s = new SelectItem(v.getPlaca(), v.getPlaca() + " | " + v.getMarca());
			itemPlacas.add(s);
		}
	}
	
	/**
	 * REPORTE 3
	 * Lista todas las transaccion dado un vehiculo
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param placa
	 */
	public void generarReporte(ActionEvent event) {

		if (validate()) {
			try {
	        	Connection conexion = ServiceProvider.openConnection();
	        	
	        	String ruta = servletContext.getRealPath(File.separator + "resources" +
														 File.separator + "reports" +
														 File.separator + "scav_report3.jasper");
	        	
	        	Vehiculo v = vehiculoBL.obtenerVehiculosPlaca(selectPlaca);
	        	
	        	Map<String, Object> parameters = new HashMap<String, Object>();
	        	parameters.put("fechaInicio", fechaInicio);
	        	parameters.put("fechaFin", fechaFin);
	        	parameters.put("placa", selectPlaca);
	        	parameters.put("marca", v.getMarca());
	        	
	        	
	    		byte[] bytes = JasperRunManager.runReportToPdf(ruta, parameters, conexion);
	    		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    		response.setContentType("application/pdf");
	    		response.setContentLength(bytes.length);
	    		ServletOutputStream outStream = response.getOutputStream();
	    		outStream.write(bytes, 0 , bytes.length);
	    		outStream.flush();
	    		outStream.close();
	    			
	    		FacesContext.getCurrentInstance().responseComplete();
	    		
	    		ServiceProvider.closeConnection(conexion);
	    		
	        } catch(Exception e) {
	            
	        	log.error("Error al generar el reporte: ", e);
	        	FacesUtil.showFacesMessage("Error al generar el reporte", FacesUtil.SEVERITY_ERROR);
	        }
		}
		
	}
	
	private boolean validate() {
		if (selectPlaca.equals("-1")) {
			FacesUtil.showFacesMessage("Seleccione un Vehiculo para Generar el Reporte de Transacciones", FacesUtil.SEVERITY_ERROR);
			return false;
		}
		
		return true;
	}
	
	/**
	 * GETTER AND SETTER
	 */	

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public List<SelectItem> getItemPlacas() {
		return itemPlacas;
	}
	
	public void setItemPlacas(List<SelectItem> itemPlacas) {
		this.itemPlacas = itemPlacas;
	}
	
	public String getSelectPlaca() {
		return selectPlaca;
	}
	
	public void setSelectPlaca(String selectPlaca) {
		this.selectPlaca = selectPlaca;
	}
	
}
