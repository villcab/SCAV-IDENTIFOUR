package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.firstonesoft.scav.model.Visita;

public class VisitaDAO {
	
	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	@SuppressWarnings("unchecked")
	public Visita obtenerVisitaCI(String ci) {
		Query q = manager.createQuery("FROM Visita v WHERE v.ci = :ci");
		q.setParameter("ci", ci);
		List<Visita> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Visita> obtenerVisitasEntorno(Integer idEntorno) {
		Query q = manager.createNativeQuery("SELECT visita.ci, visita.nombres, visita.apellidos FROM tranca INNER JOIN ingreso_salida_visita ON ingreso_salida_visita.id_tranca = tranca.id INNER JOIN vehiculo_visita ON ingreso_salida_visita.placa = vehiculo_visita.placa INNER JOIN visita_vehiculo ON visita_vehiculo.placa = vehiculo_visita.placa INNER JOIN visita ON visita_vehiculo.ci = visita.ci WHERE tranca.id_entorno = :idEntorno GROUP BY visita.ci, visita.nombres, visita.apellidos", Visita.class);
		q.setParameter("idEntorno", idEntorno);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Visita> obtenerVisitas() {
		Query q = manager.createQuery("FROM Visita");
		return q.getResultList();
	}
	
}
