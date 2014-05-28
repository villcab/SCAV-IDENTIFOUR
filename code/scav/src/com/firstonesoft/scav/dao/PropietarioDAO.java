package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.firstonesoft.scav.model.Propietario;

public class PropietarioDAO {

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public void guardar(Propietario data) throws Exception {
		try {
			tx.begin();
			manager.persist(data);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}
	
	public void actualizar(Propietario data) throws Exception {
		try {
			tx.begin();
			manager.merge(data);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}
	
	public void eliminar(Propietario data) throws Exception {
		try {
			tx.begin();
			data.setEstado(false);
			manager.merge(data);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Propietario obtenerPropietarioCi(int ci) {
		Query q = manager.createQuery("FROM Propietario p WHERE p.ci = :ci AND p.estado = true");
		q.setParameter("ci", ci);
		List<Propietario> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Propietario> obtenerPropietarios() {
		Query q = manager.createQuery("FROM Propietario p WHERE p.estado = true");
		return q.getResultList();
	}
	
}
