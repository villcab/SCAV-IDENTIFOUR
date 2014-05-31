package firstone.serializable;

import java.io.Serializable;

public class Alarma implements Serializable {

	private static final long serialVersionUID = 1L;
	private String prioridad;
	private String emisor;

	/**
	 * @return the prioridad
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * @param prioridad
	 *            the prioridad to set
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * @return the emisor
	 */
	public String getEmisor() {
		return emisor;
	}

	/**
	 * @param emisor
	 *            the emisor to set
	 */
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
}
