package com.firstonesoft.mu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.firstonesoft.mu.model.MuMenu;

public class MenuDAO {

	@PersistenceContext
	private transient EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<MuMenu> getMenusDisponibles() {
		Query q = manager.createQuery("from MuMenu c where c.muMenu = null and c.estado = true");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MuMenu> getMenusNoDisponibles() {
		Query q = manager.createQuery("from MuMenu c where c.muMenu = null and c.estado = false");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<MuMenu> getAllMenus() {
		Query q = manager.createQuery("from MuMenu c where c.muMenu = null");
		return q.getResultList();
	}
	
}
