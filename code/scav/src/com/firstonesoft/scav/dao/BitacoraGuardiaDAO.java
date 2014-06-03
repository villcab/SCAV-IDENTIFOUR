package com.firstonesoft.scav.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.firstonesoft.scav.model.BitacoraGuardia;

public class BitacoraGuardiaDAO {

	@PersistenceContext
	private transient EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<BitacoraGuardia> obtenerBitacorasGuardias(String ci) {
		Query q = manager.createQuery("SELECT b FROM BitacoraGuardia b where b.guardia.ci = :ci order by b.fechaHora desc");
		q.setParameter("ci", ci);
		return q.getResultList();
	}
	
}
