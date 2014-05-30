package com.firstonesoft.mu.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.mu.dao.MenuDAO;
import com.firstonesoft.mu.model.MuMenu;

public class MenuBL {

	@Inject
	private MenuDAO menuDao;
	
	public List<MuMenu> obtenerMenus() {
		return menuDao.getMenus();
	}
}
