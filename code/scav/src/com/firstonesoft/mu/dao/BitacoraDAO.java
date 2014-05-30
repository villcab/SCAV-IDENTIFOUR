package com.firstonesoft.mu.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.mu.model.MuBitacora;

public class BitacoraDAO {

	private final Logger log = Logger.getLogger(BitacoraDAO.class);
	
	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public void guardar(MuBitacora data) {
		try {
			tx.begin();
			manager.persist(data);
			tx.commit();
		} catch (Exception e) {
			try {
				tx.rollback();
				log.error("Error al guardar: ", e);
			} catch (Exception ex) {
				log.error("Error al deshacer la transaccion: ", ex);
			}
			
		}
	}
	
}
