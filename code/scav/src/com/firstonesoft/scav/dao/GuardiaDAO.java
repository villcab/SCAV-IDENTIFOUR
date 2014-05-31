package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.Guardia;

public class GuardiaDAO {
	
	private final Logger log = Logger.getLogger(GuardiaDAO.class);

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean guardar(Guardia data) {
		try {
			tx.begin();
			manager.persist(data);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
				log.error("Error al guardar: ", e);
				return false;
			} catch (Exception ex) {
				log.error("Error al deshacer la transaccion: ", ex);
				return false;
			}
			
		}
	}
	
	public boolean actualizar(Guardia data) {
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
	
	public boolean eliminar(Guardia data) {
		try {
			tx.begin();
			data.setEstado(false);
			manager.merge(data);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
				log.error("Error al eliminar: ", e);
				return false;
			} catch (Exception ex) {
				log.error("Error al deshacer la transaccion: ", ex);
				return false;
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public Guardia obtenerGuardiaCi(String ci) {
		Query q = manager.createQuery("FROM Guardia g WHERE g.ci = :ci AND g.estado = true");
		q.setParameter("ci", ci);
		List<Guardia> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Guardia> obtenerGuardias() {
		Query q = manager.createQuery("FROM Guardia p WHERE p.estado = true");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Guardia> obtenerGuardiaEntorno(Integer idEntorno) {
		String sql = "FROM Guardia p WHERE p.entorno.id = :id AND p.estado = true";
		Query q = manager.createQuery(sql);
		q.setParameter("id", idEntorno);
		return q.getResultList();
	}
	
}
