package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.Tranca;

public class TrancaDAO {
	
	private final Logger log = Logger.getLogger(TrancaDAO.class);

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean guardar(Tranca data) {
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
	
	public boolean actualizar(Tranca data) {
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
	
	public boolean eliminar(Integer idTranca) {
		try {
			tx.begin();
			String sql = "DELETE FROM tranca WHERE id = :id";
			Query q = manager.createNativeQuery(sql);
			q.setParameter("id", idTranca);
			q.executeUpdate();
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
	public Tranca obtenerTrancaId(Integer idTranca) {
		Query q = manager.createQuery("FROM Tranca t WHERE t.id = :id");
		q.setParameter("id", idTranca);
		List<Tranca> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tranca> obtenerTrancas() {
		Query q = manager.createQuery("FROM Tranca");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tranca> obtenerTrancasEntorno(Integer idEntorno) {
		String sql = "FROM Tranca t WHERE t.entorno.id = :id";
		Query q = manager.createQuery(sql);
		q.setParameter("id", idEntorno);
		return q.getResultList();
	}
	
}
