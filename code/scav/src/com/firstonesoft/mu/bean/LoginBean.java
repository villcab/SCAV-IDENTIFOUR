package com.firstonesoft.mu.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import com.firstonesoft.mu.business.MenuBL;
import com.firstonesoft.mu.filter.LoginFilter;
import com.firstonesoft.mu.model.MuMenu;
import com.firstonesoft.scav.business.AdministradorEntornoBL;
import com.firstonesoft.scav.model.AdministradorEntorno;
import com.firstonesoft.scav.model.Entorno;
import com.firstonesoft.util.FacesUtil;
import com.firstonesoft.util.Parameters;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(LoginBean.class);
	
	@Inject
	private MenuBL menuBL;
	
	@Inject
	private AdministradorEntornoBL administradorEntornoBL;
	
	private String email;
	private String password;
	
	private DefaultMenuModel model;
	
	@PostConstruct
	private void init() {
		try {
			
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	public void loguear() {
		
		AdministradorEntorno usuario = administradorEntornoBL.obtenerAdminitradorEntorno(email, password);
		
		if (usuario != null) {	// SE LOGUEO CORRETAMENTE
			
			int idEntorno = 0;
			if (!usuario.getEntornos().isEmpty()) {
				
				Entorno e = usuario.getEntornos().get(0);
				
				if (!e.getLicenciaActiva()) {
					FacesUtil.setParametro("sw", false);
					FacesUtil.showFacesMessage("Su Entorno no esta habilitado", FacesUtil.SEVERITY_ERROR);
					return;
				}
				
				idEntorno = e.getId();
				
				cargarMenusAdmEntorno();
			} else {
				
				idEntorno = 0;
				cargarMenusCompleto();
			}
			
			FacesUtil.setParametro("sw", true);
			FacesUtil.setParametro("sig", LoginFilter.rederingMenu);
			
			FacesUtil.setSessionAttribute("TEMP$USER_NAME", usuario.getEmail());
			FacesUtil.setSessionAttribute("TEMP$ENTORNO_ID", idEntorno);
			
		} else {				// FALLO AL LOGUEARSE
			FacesUtil.setParametro("sw", false);
			FacesUtil.showFacesMessage("Credenciales Invalidas", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void cargarMenusAdmEntorno() {
		model = new DefaultMenuModel();
		List<MuMenu> menus = menuBL.getMenusDisponibles();
		for (MuMenu m : menus) {
			DefaultSubMenu subMenu = new DefaultSubMenu(m.getNombre());
			List listMenus = m.getMuMenus();
			Collections.sort(listMenus, new Comparator<MuMenu>() {
				@Override
				public int compare(MuMenu o1, MuMenu o2) {
					return o1.getNivel().compareTo(o2.getNivel());
				}
			});
			for (Object o : listMenus) {
				MuMenu i = (MuMenu) o;
				DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
				item.setUrl(i.getUrl());
				
				subMenu.addElement(item);
			}
			model.addElement(subMenu);
		}
		
		DefaultSubMenu subMenu = new DefaultSubMenu("Opciones");
		
		DefaultMenuItem item = new DefaultMenuItem("Configuracion de Perfil");
		item.setUrl("scav_administrador_entorno.xhtml");
		item.setIcon("ui-icon-person");
		subMenu.addElement(item);
		
		item = new DefaultMenuItem("Manual de Usuario");
		String pathDoc = "/" + Parameters.system_name + "/resources/mu.pdf";
		String strUrlDoc = "window.open('" + pathDoc + "'); return false;";
		item.setOnclick(strUrlDoc);
		item.setIcon("ui-icon-document");
		subMenu.addElement(item);
		
		item = new DefaultMenuItem("Cerrar Sesion");
		item.setUrl("/Logout");
		item.setIcon("ui-icon-close");
		subMenu.addElement(item);
		
		model.addElement(subMenu);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void cargarMenusCompleto() {
		model = new DefaultMenuModel();
		List<MuMenu> menus = menuBL.getMenusNoDisponibles();
		for (MuMenu m : menus) {
			DefaultSubMenu subMenu = new DefaultSubMenu(m.getNombre());
			List listMenus = m.getMuMenus();
			Collections.sort(listMenus, new Comparator<MuMenu>() {
				@Override
				public int compare(MuMenu o1, MuMenu o2) {
					return o1.getNivel().compareTo(o2.getNivel());
				}
			});
			for (Object o : listMenus) {
				MuMenu i = (MuMenu) o;
				DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
				item.setUrl(i.getUrl());
				
				subMenu.addElement(item);
			}
			
			model.addElement(subMenu);
		}
		
		DefaultSubMenu subMenu = new DefaultSubMenu("Opciones");
		
		DefaultMenuItem item = new DefaultMenuItem("Cerrar Sesion");
		item.setUrl("/Logout");
		item.setIcon("ui-icon-close");
		
		subMenu.addElement(item);
		
		model.addElement(subMenu);
	}

	/**
	 * GETTER AND SETTER
	 */
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public DefaultMenuModel getModel() {
		return model;
	}
	
	public void setModel(DefaultMenuModel model) {
		this.model = model;
	}
	
}
