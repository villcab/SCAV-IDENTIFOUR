package com.firstonesoft.mu.bean;

import java.io.Serializable;
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
import com.firstonesoft.util.FacesUtil;

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
			cargarMenus();
			FacesUtil.setParametro("sw", true);
			FacesUtil.setParametro("sig", LoginFilter.rederingMenu);
			FacesUtil.setSessionAttribute("TEMP$USER_NAME", usuario.getEmail());
			
		} else {				// FALLO AL LOGUEARSE
			FacesUtil.setParametro("sw", false);
			FacesUtil.showFacesMessage("Credenciales Invalidas", FacesUtil.SEVERITY_ERROR);
		}
	}
	
	private void cargarMenus() {
		model = new DefaultMenuModel();
		List<MuMenu> menus = menuBL.obtenerMenus();
		for (MuMenu m : menus) {
			DefaultSubMenu subMenu = new DefaultSubMenu(m.getNombre());
			for (MuMenu i : m.getMuMenus()) {
				DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
				item.setUrl(i.getUrl());
				subMenu.addElement(item);
			}
			model.addElement(subMenu);
		}
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
