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
		data = data.replace(" ", "");
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
	
}
