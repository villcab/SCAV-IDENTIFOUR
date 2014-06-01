package com.firstonesoft.scav.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import com.firstonesoft.scav.model.Sincronizador;

public class SincronizadorDAO {
	
	private final Logger log = Logger.getLogger(SincronizadorDAO.class);

	@PersistenceContext
	private transient EntityManager manager;
	
	@Resource
	private transient UserTransaction tx;
	
	public void guardar(Sincronizador data) {
		try {
			tx.begin();
			manager.persist(data);
			tx.commit();
			log.info("Se agrego: " + data.toString());
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
