package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.VisitaDAO;
import com.firstonesoft.scav.model.Visita;

public class VisitaBL {

	@Inject
	private VisitaDAO visitaDAO;

	public Visita obtenerVisitaCI(String ci) {
		return visitaDAO.obtenerVisitaCI(ci);
	}
	
	public List<Visita> obtenerVisitasEntorno(Integer idEntorno) {
		return visitaDAO.obtenerVisitasEntorno(idEntorno);
	}
	
	public List<Visita> obtenerVisitas() {
		return visitaDAO.obtenerVisitas();
	}
	
}
