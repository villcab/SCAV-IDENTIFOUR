package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingreso_salida_visita database table.
 * 
 */
@Entity
@Table(name="ingreso_salida_visita")
@NamedQuery(name="IngresoSalidaVisita.findAll", query="SELECT i FROM IngresoSalidaVisita i")
public class IngresoSalidaVisita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngresoSalidaVisitaPK id;

	@Column(nullable=false, length=10)
	private String tipo;

	//bi-directional many-to-one association to Tranca
	@ManyToOne
	@JoinColumn(name="id_tranca", nullable=false, insertable=false, updatable=false)
	private Tranca tranca;

	//bi-directional many-to-one association to VehiculoVisita
	@ManyToOne
	@JoinColumn(name="placa", nullable=false, insertable=false, updatable=false)
	private VehiculoVisita vehiculoVisita;

	public IngresoSalidaVisita() {
	}

	public IngresoSalidaVisitaPK getId() {
		return this.id;
	}

	public void setId(IngresoSalidaVisitaPK id) {
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

	public VehiculoVisita getVehiculoVisita() {
		return this.vehiculoVisita;
	}

	public void setVehiculoVisita(VehiculoVisita vehiculoVisita) {
		this.vehiculoVisita = vehiculoVisita;
	}

}