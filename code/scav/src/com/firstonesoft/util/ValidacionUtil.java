package com.firstonesoft.util;


public class ValidacionUtil {
	
	public static boolean esCI(String data) {
		return data.matches(Parameters.er_ci);
	}
	
	public static boolean esNombres(String data) {
		return data.matches(Parameters.er_nombres);
	}
	
	public static boolean esApellidos(String data) {
		return data.matches(Parameters.er_apellido_paterno);
	}
	
	public static boolean esPlaca(String data) {
		return data.matches(Parameters.er_placa);
	}
	
	public static boolean esMarca(String data) {
		return data.matches(Parameters.er_marca);
	}
	
	public static boolean esTelefono(String data) {
		return data.matches(Parameters.er_telefonos);
	}

//	public static boolean sonNumeros(String data) {
//		for (int i = 0; i < data.length(); i++) {
//			if (!Character.isDigit(data.charAt(i))) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private static boolean sonLetras(String data) {
//		for (int i = 0; i < data.length(); i++) {
//			if (!Character.isLetter(data.charAt(i))) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	public static boolean sonLetrasPalabras(String data) {
//		StringTokenizer st = new StringTokenizer(data, " ");
//		while (st.hasMoreTokens()) {
//			String s = st.nextToken();
//			if (!sonLetras(s)) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	public static boolean sonLetrasNumeros(String data) {
//		for (int i = 0; i < data.length(); i++) {
//			if (!Character.isLetter(data.charAt(i)) && !Character.isDigit(data.charAt(i))) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	public static String obtenerNumeros(String data) {
		String num = "";
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			if (Character.isDigit(c) || Character.isLetter(c)) {
				num += c;
			} else {
				break;
			}
		}
		return num;
	}
	
}
