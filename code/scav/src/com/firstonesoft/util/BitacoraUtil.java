package com.firstonesoft.util;

public enum BitacoraUtil {

	MU_LOGIN("Iniciar Sesion"),
	MU_LOGOUT("Salir Sesion"),
	MU_EXPULSION_SISTEMA("Expulsion del Sistema"),
	SCAV_PROPIETARIOS("Gestionar Propietarios"),
	SCAV_VEHICULOS("Gestionar Vehiculos");
	
	private final String formulario;

	BitacoraUtil(String detalle) {
		this.formulario = detalle;
	}

	@Override
	public String toString() {
		return formulario;
	}
	
}
