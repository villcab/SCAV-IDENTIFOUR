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

	public static void init() {
		try {
			rb = ResourceBundle.getBundle(configFile);
		} catch (Exception e) {
			log.error("Fallo al cargar el Archivo de Properties [" + configFile + "]: ", e);
		}
	}

	public static String system_name = rb.getString("system.name");
	public static String system_active_directory = rb.getString("system.active_directory");
	public static String system_username = rb.getString("system.username");
	public static String system_password = rb.getString("system.password");

}
