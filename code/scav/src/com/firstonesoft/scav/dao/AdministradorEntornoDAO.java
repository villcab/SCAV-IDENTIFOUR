package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.AdministradorEntorno;

public class AdministradorEntornoDAO {
	
	private final Logger log = Logger.getLogger(AdministradorEntornoDAO.class);
	
	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean editar(AdministradorEntorno data) {
		try {
			tx.begin();
			manager.merge(data);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
				log.error("Error al actualizar: ", e);
				return false;
			} catch (Exception ex) {
				log.error("Error al deshacer la transaccion: ", ex);
				return false;
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public AdministradorEntorno obtenerAdminitradorEntorno(String email, String password) {
		Query q = manager.createQuery("FROM AdministradorEntorno p WHERE p.email = :email AND p.password = :pass");
		q.setParameter("email", email);
		q.setParameter("pass", password);
		List<AdministradorEntorno> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public AdministradorEntorno obtenerAdminitradorEntornoCI(String ci) {
		Query q = manager.createQuery("FROM AdministradorEntorno p WHERE p.ci = :ci");
		q.setParameter("ci", ci);
		List<AdministradorEntorno> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public AdministradorEntorno obtenerAdminitradorEntornoEmail(String email) {
		Query q = manager.createQuery("FROM AdministradorEntorno p WHERE p.email = :email");
		q.setParameter("email", email);
		List<AdministradorEntorno> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
}
