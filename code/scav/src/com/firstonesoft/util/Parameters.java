package com.firstonesoft.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Parameters {

	private static final Logger log = Logger.getLogger(Parameters.class);

	private static String configFile = "scav_web-config";
	private static ResourceBundle rb;

	static {
		init();
	}
	
	public static String system_name;
	public static String system_active_directory;
	public static String system_username;
	public static String system_password;
	
	public static Integer system_tiempo_fuera;
	public static Integer system_numero_intentos;

	public static void init() {
		try {
			rb = ResourceBundle.getBundle(configFile);
			
			system_name = rb.getString("system.name");
			system_active_directory = rb.getString("system.active_directory");
			system_username = rb.getString("system.username");
			system_password = rb.getString("system.password");
			
			system_tiempo_fuera = Integer.parseInt(rb.getString("system.tiempo.fuera"));
			system_numero_intentos = Integer.parseInt(rb.getString("system.numero.intentos"));
			
		} catch (Exception e) {
			log.error("Fallo al cargar el Archivo de Properties [" + configFile + "]: ", e);
		}
	}

	

}
