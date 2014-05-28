package com.firstonesoft.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

public class ArchivoUtil {
	
	private static final Logger log = Logger.getLogger(ArchivoUtil.class);
	
	/**
	 * Crear un file en la ruta archivo con los datos del array
	 * @param archivo
	 * @param array
	 */
	public static void crearArchivo(String archivo, byte[] array) {
        FileImageOutputStream fileOutput;
        try {
        	fileOutput = new FileImageOutputStream(new File(archivo));
        	fileOutput.write(array, 0, array.length);
        	fileOutput.close();
        } catch (FileNotFoundException e) {
            log.error("Direccion de ruta no valida: ", e);
        } catch (IOException e) {
        	log.error("Error al crear el archivo: ", e);
        }
    }
	
	/**
	 * Verifica un archivo y si existe lo elimina
	 * @param archivo
	 */
	public static void verificarEliminar(String archivo) {
        if (archivo != null) {
            File file = new File(archivo);
            if (file.exists()) {
                file.delete();
            }
        }
    }
	
}
