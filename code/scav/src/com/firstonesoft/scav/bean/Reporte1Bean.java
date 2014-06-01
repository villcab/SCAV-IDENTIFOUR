package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.log4j.Logger;

import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.ServiceProvider;

@ManagedBean
@RequestScoped
public class Reporte1Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(Reporte1Bean.class);
	
	//reporte 1
	private Date fecha;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {
			
			fecha = new Date();
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	/**
	 * REPORTE 1
	 * Genera la cantidad de ingresos y salidas dada una fecha
	 * 
	 * @param fecha
	 */
	public void generarReporte(ActionEvent event) {
        try {
        	Connection conexion = ServiceProvider.openConnection();
        	
        	String ruta = servletContext.getRealPath(File.separator + "resources" +
        											 File.separator + "reports" +
        											 File.separator + "scav_report1.jasper");
        	
        	Map<String, Object> parameters = new HashMap<String, Object>();
        	parameters.put("fecha", fecha);
        	
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
	
	/**
	 * GETTER AND SETTER
	 */	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
