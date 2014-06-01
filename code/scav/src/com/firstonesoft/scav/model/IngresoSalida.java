package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingreso_salida database table.
 * 
 */
@Entity
@Table(name="ingreso_salida")
@NamedQuery(name="IngresoSalida.findAll", query="SELECT i FROM IngresoSalida i")
public class IngresoSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngresoSalidaPK id;

	@Column(nullable=false, length=10)
	private String tipo;

	//bi-directional many-to-one association to Tranca
	@ManyToOne
	@JoinColumn(name="id_tranca", nullable=false, insertable=false, updatable=false)
	private Tranca tranca;

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne
	@JoinColumn(name="placa", nullable=false, insertable=false, updatable=false)
	private Vehiculo vehiculo;

	public IngresoSalida() {
	}

	public IngresoSalidaPK getId() {
		return this.id;
	}

	public void setId(IngresoSalidaPK id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Tranca getTranca() {
		return this.tranca;
	}

	public void setTranca(Tranca tranca) {
		this.tranca = tranca;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}