package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.Entorno;

public class EntornoDAO {
	
	private final Logger log = Logger.getLogger(EntornoDAO.class);

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean actualizar(Entorno data) {
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
	public Entorno obtenerEntornoId(Integer idEntorno) {
		Query q = manager.createQuery("FROM Entorno t WHERE t.id = :id");
		q.setParameter("id", idEntorno);
		List<Entorno> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Entorno> obtenerEntornos() {
		Query q = manager.createQuery("FROM Entorno");
		return q.getResultList();
	}
	
}
