package com.firstonesoft.scav.business;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.SincronizadorDAO;
import com.firstonesoft.scav.model.Sincronizador;

public class SincronizadorBL {

	@Inject
	private SincronizadorDAO dao;

	public void guardar(char transaccion, String refId, String tabla, Integer idEntorno) {
		Sincronizador data = new Sincronizador();
		data.setTransaccion(String.valueOf(transaccion));
		data.setRefId(refId);
		data.setTabla(tabla);
		data.setIdEntorno(idEntorno);
		
		dao.guardar(data);
	}
	
}
