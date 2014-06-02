package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
public class Reporte5Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(Reporte5Bean.class);
	
	private int idEntorno;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {

			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	/**
	 * REPORTE 5
	 * Lista todas las trancas y sus respectivas transacciones
	 * 
	 * @param ci
	 */
	public void generarReporte(ActionEvent event) {

		if (validate()) {
			try {
				
				Connection cnx = ServiceProvider.openConnection();
				
	        	String ruta = servletContext.getRealPath(File.separator + "resources" +
														 File.separator + "reports" +
														 File.separator + "scav_report5.jasper");
	        		        	
	        	Map<String, Object> parameters = new HashMap<String, Object>();
	        	
	        	parameters.put("idEntorno", idEntorno);
	        	
	    		byte[] bytes = JasperRunManager.runReportToPdf(ruta, parameters, cnx);
	    		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	    		response.setContentType("application/pdf");
	    		response.setContentLength(bytes.length);
	    		ServletOutputStream outStream = response.getOutputStream();
	    		outStream.write(bytes, 0 , bytes.length);
	    		outStream.flush();
	    		outStream.close();
	    			
	    		FacesContext.getCurrentInstance().responseComplete();
	    		
	    		ServiceProvider.closeConnection(cnx);
	    		
	        } catch(Exception e) {
	            
	        	log.error("Error al generar el reporte: ", e);
	        	FacesUtil.showFacesMessage("Error al generar el reporte", FacesUtil.SEVERITY_ERROR);
	        }
		}
		
	}
	
	private boolean validate() {
		if (idEntorno == 0) {
			FacesUtil.showFacesMessage("Usted no puede generar el reporte por que no tiene un Entorno asociado o no Habilitado", FacesUtil.SEVERITY_ERROR);
			return false;
		}
		
		return true;
	}
	
}
