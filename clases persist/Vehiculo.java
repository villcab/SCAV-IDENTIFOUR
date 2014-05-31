package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name="vehiculo")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String placa;

	@Column(nullable=false)
	private Boolean estado;

	private byte[] foto;

	@Column(length=100)
	private String marca;

	@Column(length=50)
	private String modelo;

	@Column(nullable=false)
	private Integer rfid;

	//bi-directional many-to-one association to IngresoSalida
	@OneToMany(mappedBy="vehiculo")
	private List<IngresoSalida> ingresoSalidas;

	//bi-directional many-to-many association to Propietario
	@ManyToMany(mappedBy="vehiculos", fetch = FetchType.EAGER)
	private List<Propietario> propietarios;

	//bi-directional many-to-one association to Entorno
	@ManyToOne
	@JoinColumn(name="id_entorno")
	private Entorno entorno;

	public Vehiculo() {
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getRfid() {
		return this.rfid;
	}

	public void setRfid(Integer rfid) {
		this.rfid = rfid;
	}

	public List<IngresoSalida> getIngresoSalidas() {
		return this.ingresoSalidas;
	}

	public void setIngresoSalidas(List<IngresoSalida> ingresoSalidas) {
		this.ingresoSalidas = ingresoSalidas;
	}

	public IngresoSalida addIngresoSalida(IngresoSalida ingresoSalida) {
		getIngresoSalidas().add(ingresoSalida);
		ingresoSalida.setVehiculo(this);

		return ingresoSalida;
	}

	public IngresoSalida removeIngresoSalida(IngresoSalida ingresoSalida) {
		getIngresoSalidas().remove(ingresoSalida);
		ingresoSalida.setVehiculo(null);

		return ingresoSalida;
	}

	public List<Propietario> getPropietarios() {
		return this.propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public Entorno getEntorno() {
		return this.entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}
	
	@Override
	public boolean equals(Object o) {
		Vehiculo v = (Vehiculo) o;
		if (v.getPlaca().equals(placa)) {
			return true;
		}
		return false;
	}

}