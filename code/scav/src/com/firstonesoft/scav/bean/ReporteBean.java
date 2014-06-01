package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Logger;

import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.ServiceProvider;

@ManagedBean
@ViewScoped
public class ReporteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(ReporteBean.class);
	
	private Date fecha;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	/**
	 * REPORTE 1
	 * Generar la cantidad de ingresos y salidas dada una fecha
	 * @param fecha
	 */
	@SuppressWarnings("deprecation")
	public void generarReporte1() {
        try {
        	Connection conexion = ServiceProvider.openConnection();
        	
        	String ruta = servletContext.getRealPath(File.separator + "reports" +
        											 File.separator + "scav_reporte_1.jasper");
        	
        	log.info("ruta: " + ruta);
        	
            JasperReport reporte = (JasperReport) JRLoader.loadObject(ruta);
            
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("fecha", fecha);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setVisible(true);
            
            log.info("Completo el reporte");
            
        } catch(Exception e) {
            
        	log.error("Error al generar el reporte: ", e);
        	FacesUtil.showFacesMessage("Error al generar el reporte", FacesUtil.SEVERITY_ERROR);
        }
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
