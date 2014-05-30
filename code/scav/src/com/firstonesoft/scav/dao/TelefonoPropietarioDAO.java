package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.TelefonoPropietario;

public class TelefonoPropietarioDAO {

	private final Logger log = Logger.getLogger(TelefonoPropietarioDAO.class);
	
	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean guardar(TelefonoPropietario data) {
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
	
	public boolean eliminar(Integer telefono) {
		try {
			tx.begin();
			String sql = "DELETE FROM telefono_propietario WHERE telefono = :tel";
			Query q = manager.createNativeQuery(sql);
			q.setParameter("tel", telefono);
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
	public TelefonoPropietario obtenerTelefonoPropietario(Integer telefono) {
		Query q = manager.createQuery("FROM TelefonoPropietario p WHERE p.telefono = :tel");
		q.setParameter("tel", telefono);
		List<TelefonoPropietario> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<TelefonoPropietario> obtenerTelefonosPropietarios(String ci) {
		Query q = manager.createQuery("FROM TelefonoPropietario p WHERE p.propietario.ci = :ci ORDER BY p.telefono");
		q.setParameter("ci", ci);
		return q.getResultList();
	}
	
}
