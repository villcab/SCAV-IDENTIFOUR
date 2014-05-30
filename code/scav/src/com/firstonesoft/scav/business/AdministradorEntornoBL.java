package com.firstonesoft.scav.business;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.AdministradorEntornoDAO;
import com.firstonesoft.scav.model.AdministradorEntorno;

public class AdministradorEntornoBL {

	@Inject
	private AdministradorEntornoDAO administradorEntornoDAO;

	public AdministradorEntorno obtenerAdminitradorEntorno(String username, String password) {
		return administradorEntornoDAO.obtenerAdminitradorEntorno(username, password);
	}
	
}
