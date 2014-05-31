package com.firstonesoft.scav.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.Vehiculo;;

public class VehiculoDAO {
	
	private final Logger log = Logger.getLogger(VehiculoDAO.class);

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public boolean guardar(Vehiculo data) {
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
	
	public boolean actualizar(Vehiculo data) {
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
	
	public boolean eliminar(Vehiculo data) {
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
	public Vehiculo obtenerVehiculosPlaca(String placa) {
		Query q = manager.createQuery("FROM Vehiculo p WHERE p.placa = :placa AND p.estado = true");
		q.setParameter("placa", placa);
		List<Vehiculo> list = q.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerVehiculos() {
		Query q = manager.createQuery("FROM Vehiculo p WHERE p.estado = true");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerVehiculosEntorno(Integer idEntorno) {
		Query q = manager.createQuery("FROM Vehiculo p WHERE p.entorno.id = :id AND p.estado = true");
		q.setParameter("id", idEntorno);
		return q.getResultList();
	}
	
}
