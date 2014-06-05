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
	
	public static String db_driver;
	public static String db_url;
	public static String db_username;
	public static String db_password;
	
	public static String er_ci;
	public static String er_nombres;
	public static String er_apellido_paterno;
	public static String er_placa;
	public static String er_marca;
	public static String er_telefonos;

	public static void init() {
		try {
			rb = ResourceBundle.getBundle(configFile);
			
			system_name = rb.getString("system.name");
			
			db_driver = rb.getString("db.driver");
			db_url = rb.getString("db.url");
			db_username = rb.getString("db.username");
			db_password = rb.getString("db.password");
			
			er_ci = rb.getString("er.ci");
			er_nombres = rb.getString("er.nombres");
			er_apellido_paterno = rb.getString("er.apellido.paterno");
			er_placa = rb.getString("er.placa");
			er_marca = rb.getString("er.marca");
			er_telefonos = rb.getString("er.telefonos");
			
		} catch (Exception e) {
			log.error("Fallo al cargar el Archivo de Properties [" + configFile + "]: ", e);
		}
	}

	

}
