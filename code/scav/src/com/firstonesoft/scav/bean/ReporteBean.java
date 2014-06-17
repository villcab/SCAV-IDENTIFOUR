package com.firstonesoft.scav.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.business.GuardiaBL;
import com.firstonesoft.scav.business.PropietarioBL;
import com.firstonesoft.scav.business.VehiculoBL;
import com.firstonesoft.scav.business.VisitaBL;
import com.firstonesoft.scav.model.Guardia;
import com.firstonesoft.scav.model.Propietario;
import com.firstonesoft.scav.model.Vehiculo;
import com.firstonesoft.scav.model.Visita;
import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.ServiceProvider;

@ManagedBean
@SessionScoped
public class ReporteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(ReporteBean.class);
	
	@Inject
	private VehiculoBL vehiculoBL;
	
	@Inject
	private PropietarioBL propietarioBL;
	
	@Inject
	private GuardiaBL guardiaBL;
	
	@Inject
	private VisitaBL visitaBL;
	
	private int idEntorno;
	
	//	REPORTE 1
	private Date fechaInicioR1;
	private Date fechaFinR1;
	
	//	REPORTE 2
	private Date fechaInicioR2;
	private Date fechaFinR2;
	private List<SelectItem> itemPlacasR2;
	private String selectPlacaR2;
	
	//	REPORTE 3
	private List<SelectItem> itemPropietariosR3;
	private String selectPropietarioR3;
	
	//	REPORTE 4
	
	//	REPORTE 5
	private List<SelectItem> itemGuardiasR5;
	private String selectGuardiaR5;
	private int cantidadR5;
	
	//	REPORTE 6
	private List<SelectItem> itemVisitasR6;
	private String selectVisitaR6;
	
	private ServletContext servletContext;

	@PostConstruct
	private void init() {
		try {
			
			idEntorno = Integer.parseInt(FacesUtil.getSessionAttribute("TEMP$ENTORNO_ID").toString());
			
			//INICIAR PARAMETROS DE R1
			fechaInicioR1 = new Date();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(fechaInicioR1);
			gc.add(GregorianCalendar.MONTH, 1);
			fechaFinR1 = gc.getTime();
			
			//INICIAR PARAMETROS DE R2
			cargarPlacas();
			fechaInicioR2 = new Date();
			gc = new GregorianCalendar();
			gc.setTime(fechaInicioR2);
			gc.add(GregorianCalendar.MONTH, 1);
			fechaFinR2 = gc.getTime();
			
			//INICIAR PARAMETROS DE R3
			cargarPropietarios();
			
			//INICIAR PARAMETROS DE R5
			cargarGuardias();
			cantidadR5 = 10;
			
			//INICIAR PARAMETROS DE R5
			cargarVisitas();
			
			servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			
		} catch (Exception e) {
			log.error("Error al cargar la vista");
		}
	}
	
	/**
	 * REPORTE 1
	 * Genera la cantidad de ingresos y salidas dada una fecha
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte1(ActionEvent event) throws SQLException, JRException, IOException {
    	Connection conexion = ServiceProvider.openConnection();
    	
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
    											 File.separator + "reports" +
    											 File.separator + "scav_report1.jasper");
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("fechaInicio", fechaInicioR1);
    	parameters.put("fechaFin", fechaFinR1);
    	
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
	}
	
	/**
	 * REPORTE 2
	 * Lista todas las transaccion dado un vehiculo
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @param placa
	 * @param marca
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte2(ActionEvent event) throws SQLException, JRException, IOException {
		
		if (selectPlacaR2.equals("-1")) {
			log.error("Debe seleccionar una vehiculo para Generar el Reporte");
			FacesUtil.showFacesMessage("Debe seleccionar una vehiculo para Generar el Reporte", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
    	Connection conexion = ServiceProvider.openConnection();
    	
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
												 File.separator + "reports" +
												 File.separator + "scav_report2.jasper");
    	
    	Vehiculo v = vehiculoBL.obtenerVehiculosPlaca(selectPlacaR2);
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("fechaInicio", fechaInicioR2);
    	parameters.put("fechaFin", fechaFinR2);
    	parameters.put("placa", selectPlacaR2);
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
	}
	
	/**
	 * REPORTE 3
	 * Lista todos los vehiculos que tiene un propietario
	 * 
	 * @param ci del propietario
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte3(ActionEvent event) throws SQLException, JRException, IOException {
		
		if (selectPropietarioR3.equals("-1")) {
			log.error("Seleccione un Propietario para Generar el Reporte");
			FacesUtil.showFacesMessage("Seleccione un Propietario para Generar el Reporte", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		Propietario p = propietarioBL.obtenerPropietarioCi(selectPropietarioR3);
		List<firstone.serializable.Vehiculo> list = new ArrayList<firstone.serializable.Vehiculo>();
		for (Vehiculo v : p.getVehiculos()) {
			list.add(new firstone.serializable.Vehiculo(v.getPlaca(), v.getMarca(), v.getModelo(), v.getRfid() + "", new ImageIcon(v.getFoto()).getImage()));
		}
		
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
												 File.separator + "reports" +
												 File.separator + "scav_report3.jasper");
    	
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
	}
	
	/**
	 * REPORTE 4
	 * Lista todas las trancas y sus respectivas transacciones
	 * 
	 * @param id del entorno
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte4(ActionEvent event) throws SQLException, JRException, IOException {

		if (idEntorno == 0) {
			log.error("Usted no puede generar el reporte por que no tiene un Entorno asociado o no Habilitado");
			FacesUtil.showFacesMessage("Usted no puede generar el reporte por que no tiene un Entorno asociado o no Habilitado", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		Connection cnx = ServiceProvider.openConnection();
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
												 File.separator + "reports" +
												 File.separator + "scav_report4.jasper");
    		        	
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
	}
	
	/**
	 * REPORTE 5
	 * Lista de las acciones realizadas por un guardia
	 * 
	 * @param ci del guardia
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte5(ActionEvent event) throws SQLException, JRException, IOException {

		if (selectGuardiaR5.equals("-1")) {
			log.error("Debe seleccionar un guardia para generar el reporte");
			FacesUtil.showFacesMessage("Debe seleccionar un guardia para generar el reporte", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		Connection cnx = ServiceProvider.openConnection();
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
												 File.separator + "reports" +
												 File.separator + "scav_report5.jasper");

    	Guardia g = guardiaBL.obtenerGuardiaCi(selectGuardiaR5);
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("ci_guardia", selectGuardiaR5);
    	parameters.put("nombres", g.getNombre());
    	parameters.put("apellidos", g.getApellido());
    	parameters.put("limite", cantidadR5);
    	
    	
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
	}
	
	/**
	 * REPORTE 6
	 * Lista de los detalles de una visita
	 * 
	 * @param ci de la visita
	 * @throws SQLException
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generarReporte6(ActionEvent event) throws SQLException, JRException, IOException {
		if (selectVisitaR6.equals("-1")) {
			log.error("Debe seleccionar una Visita para generar el reporte");
			FacesUtil.showFacesMessage("Debe seleccionar una Visita para generar el reporte", FacesUtil.SEVERITY_ERROR);
			return;
		}
		
		Connection cnx = ServiceProvider.openConnection();
    	String ruta = servletContext.getRealPath(File.separator + "resources" +
												 File.separator + "reports" +
												 File.separator + "scav_report6.jasper");

    	Visita v = visitaBL.obtenerVisitaCI(selectVisitaR6);
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("ci_visita", selectVisitaR6);
    	parameters.put("nombres", v.getNombres());
    	parameters.put("apellidos", v.getApellidos());
    	
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
	}
	
	/**
	 * METODOS DE INICIADO
	 */
	private void cargarPlacas() {
		selectPlacaR2 = "";
		itemPlacasR2 = new ArrayList<SelectItem>();
		itemPlacasR2.add(new SelectItem("-1", "Seleccione un Vehiculo"));
		List<Vehiculo> list;
		if (idEntorno == 0) {
			list = vehiculoBL.obtenerVehiculos();
		} else {
			list = vehiculoBL.obtenerVehiculosEntorno(idEntorno);
		}
		for (Vehiculo v : list) {
			SelectItem s = new SelectItem(v.getPlaca(), v.getPlaca() + " | " + v.getMarca());
			itemPlacasR2.add(s);
		}
	}
	
	private void cargarPropietarios() {
		selectPropietarioR3 = "-1";
		itemPropietariosR3 = new ArrayList<SelectItem>();
		itemPropietariosR3.add(new SelectItem("-1", "Seleccione un Propietario"));
		List<Propietario> list;
		if (idEntorno == 0) {
			list = propietarioBL.obtenerPropietarios();
		} else {
			list = propietarioBL.obtenerPropietariosEntorno(idEntorno);
		}
		for (Propietario v : list) {
			SelectItem s = new SelectItem(v.getCi(), v.getNombres() + " " + v.getApellidos() + " | " + v.getCi());
			itemPropietariosR3.add(s);
		}
	}
	
	private void cargarGuardias() {
		selectGuardiaR5 = "-1";
		itemGuardiasR5 = new ArrayList<SelectItem>();
		itemGuardiasR5.add(new SelectItem("-1", "Seleccione un Guardia"));
		List<Guardia> list;
		if (idEntorno == 0) {
			list = guardiaBL.obtenerGuardias();
		} else {
			list = guardiaBL.obtenerGuardiaEntorno(idEntorno);
		}
		for (Guardia g : list) {
			SelectItem s = new SelectItem(g.getCi(), g.getNombre() + " " + g.getApellido() + " | " + g.getCi());
			itemGuardiasR5.add(s);
		}
	}
	
	private void cargarVisitas() {
		selectVisitaR6 = "-1";
		itemVisitasR6 = new ArrayList<SelectItem>();
		itemVisitasR6.add(new SelectItem("-1", "Seleccione una Visita"));
		List<Visita> list;
		if (idEntorno == 0) {
			list = visitaBL.obtenerVisitas();
		} else {
			list = visitaBL.obtenerVisitasEntorno(idEntorno);
		}
		for (Visita v : list) {
			SelectItem s = new SelectItem(v.getCi(), v.getNombres() + " " + v.getApellidos() + " | " + v.getCi());
			itemVisitasR6.add(s);
		}
	}
	
	/**
	 * GETTER AND SETTER
	 */	
	public Date getFechaInicioR1() {
		return fechaInicioR1;
	}
	
	public Date getFechaFinR1() {
		return fechaFinR1;
	}
	
	public void setFechaFinR1(Date fechaFinR1) {
		this.fechaFinR1 = fechaFinR1;
	}
	
	public void setFechaInicioR1(Date fechaInicioR1) {
		this.fechaInicioR1 = fechaInicioR1;
	}

	public Date getFechaInicioR2() {
		return fechaInicioR2;
	}

	public void setFechaInicioR2(Date fechaInicioR2) {
		this.fechaInicioR2 = fechaInicioR2;
	}

	public Date getFechaFinR2() {
		return fechaFinR2;
	}

	public void setFechaFinR2(Date fechaFinR2) {
		this.fechaFinR2 = fechaFinR2;
	}

	public List<SelectItem> getItemPlacasR2() {
		return itemPlacasR2;
	}

	public void setItemPlacasR2(List<SelectItem> itemPlacasR2) {
		this.itemPlacasR2 = itemPlacasR2;
	}

	public String getSelectPlacaR2() {
		return selectPlacaR2;
	}

	public void setSelectPlacaR2(String selectPlacaR2) {
		this.selectPlacaR2 = selectPlacaR2;
	}

	public List<SelectItem> getItemPropietariosR3() {
		return itemPropietariosR3;
	}

	public void setItemPropietariosR3(List<SelectItem> itemPropietariosR3) {
		this.itemPropietariosR3 = itemPropietariosR3;
	}

	public String getSelectPropietarioR3() {
		return selectPropietarioR3;
	}

	public void setSelectPropietarioR3(String selectPropietarioR3) {
		this.selectPropietarioR3 = selectPropietarioR3;
	}

	public List<SelectItem> getItemGuardiasR5() {
		return itemGuardiasR5;
	}

	public void setItemGuardiasR5(List<SelectItem> itemGuardiasR5) {
		this.itemGuardiasR5 = itemGuardiasR5;
	}

	public String getSelectGuardiaR5() {
		return selectGuardiaR5;
	}

	public void setSelectGuardiaR5(String selectGuardiaR5) {
		this.selectGuardiaR5 = selectGuardiaR5;
	}
	
	public int getCantidadR5() {
		return cantidadR5;
	}
	
	public void setCantidadR5(int cantidadR5) {
		this.cantidadR5 = cantidadR5;
	}
	
	public String getSelectVisitaR6() {
		return selectVisitaR6;
	}
	
	public void setSelectVisitaR6(String selectVisitaR6) {
		this.selectVisitaR6 = selectVisitaR6;
	}
	
	public List<SelectItem> getItemVisitasR6() {
		return itemVisitasR6;
	}
	
	public void setItemVisitasR6(List<SelectItem> itemVisitasR6) {
		this.itemVisitasR6 = itemVisitasR6;
	}
	
}
