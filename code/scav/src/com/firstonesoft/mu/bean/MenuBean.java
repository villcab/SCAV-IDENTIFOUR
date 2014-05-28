package com.firstonesoft.mu.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.firstonesoft.mu.dao.MenuDao;
import com.firstonesoft.mu.model.MuMenu;

@ManagedBean
@ViewScoped
public class MenuBean {
	
	private final Logger log = Logger.getLogger(MenuBean.class);

	@Inject
	private MenuDao menuDao;
	
	private List<MuMenu> menus;
	private DefaultMenuModel model;
	
	@PostConstruct
	private void init() {
		try {
			menus = menuDao.getMenus();
			load();
			log.info("cargardo menus");
		} catch (Exception e) {
			log.error("Error al cargar la vista: ", e);
		}
	}
	
	private void load() {
		model = new DefaultMenuModel();
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
	
	public MenuModel getModel() {
		return model;
	}
	
	public List<MuMenu> getMenus() {
		return menus;
	}

}
