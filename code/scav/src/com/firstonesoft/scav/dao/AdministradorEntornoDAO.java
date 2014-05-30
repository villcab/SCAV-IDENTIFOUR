package com.firstonesoft.scav.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.firstonesoft.scav.model.AdministradorEntorno;

public class AdministradorEntornoDAO {
	
	@PersistenceContext
	private transient EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public AdministradorEntorno obtenerAdminitradorEntorno(String email, String password) {
		Query q = manager.createQuery("FROM AdministradorEntorno p WHERE p.email = :email AND p.password = :pass");
		q.setParameter("email", email);
		q.setParameter("pass", password);
		List<AdministradorEntorno> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
}
