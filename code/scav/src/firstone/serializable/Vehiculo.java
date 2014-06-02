package firstone.serializable;

import java.awt.Image;
import java.io.Serializable;

public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String placa;
	private String marca;
	private String modelo;
	private String rfid;
	private Image foto;
	
	public Vehiculo(String placa, String marca, String modelo, String rfid,
			Image foto) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.rfid = rfid;
		this.foto = foto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Image getFoto() {
		return foto;
	}

	public void setFoto(Image foto) {
		this.foto = foto;
	}
	
}
