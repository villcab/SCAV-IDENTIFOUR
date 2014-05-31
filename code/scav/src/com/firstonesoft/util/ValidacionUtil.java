package com.firstonesoft.util;

import java.util.StringTokenizer;

public class ValidacionUtil {

	public static boolean sonNumeros(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!Character.isDigit(data.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean sonLetras(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!Character.isLetter(data.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean sonLetrasPalabras(String data) {
		StringTokenizer st = new StringTokenizer(data, " ");
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (!sonLetras(s)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean sonLetrasNumeros(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!Character.isLetter(data.charAt(i)) && !Character.isDigit(data.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
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
