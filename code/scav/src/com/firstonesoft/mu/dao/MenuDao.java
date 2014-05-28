package com.firstonesoft.mu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.firstonesoft.mu.model.MuMenu;

public class MenuDao {

	@PersistenceContext
	private transient EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<MuMenu> getMenus() {
		Query q = manager.createQuery("from MuMenu c where c.muMenu = null");
		return q.getResultList();
	}
	
}
