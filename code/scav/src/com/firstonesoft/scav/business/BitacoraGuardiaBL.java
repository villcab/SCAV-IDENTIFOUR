package com.firstonesoft.scav.business;

import java.util.List;

import javax.inject.Inject;

import com.firstonesoft.scav.dao.BitacoraGuardiaDAO;
import com.firstonesoft.scav.model.BitacoraGuardia;

public class BitacoraGuardiaBL {

	@Inject
	private BitacoraGuardiaDAO bitacoraGuardiaDAO;

	public List<BitacoraGuardia> obtenerBitacorasGuardias(String ci) {
		return bitacoraGuardiaDAO.obtenerBitacorasGuardias(ci);
	}
	
}
