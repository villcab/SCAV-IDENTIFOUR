package com.firstonesoft.scav.business;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.AdministradorEntornoDAO;
import com.firstonesoft.scav.model.AdministradorEntorno;

public class AdministradorEntornoBL {

	@Inject
	private AdministradorEntornoDAO administradorEntornoDAO;
	
	public boolean editar(AdministradorEntorno data) {
		return administradorEntornoDAO.editar(data);
	}

	public AdministradorEntorno obtenerAdminitradorEntorno(String username, String password) {
		return administradorEntornoDAO.obtenerAdminitradorEntorno(username, password);
	}
	
	public AdministradorEntorno obtenerAdminitradorEntornoCI(String ci) {
		return administradorEntornoDAO.obtenerAdminitradorEntornoCI(ci);
	}
	
	public AdministradorEntorno obtenerAdminitradorEntornoEmail(String email) {
		return administradorEntornoDAO.obtenerAdminitradorEntornoEmail(email);
	}
	
}
