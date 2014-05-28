package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ingres_salida_visita database table.
 * 
 */
@Entity
@Table(name="ingres_salida_visita")
@NamedQuery(name="IngresSalidaVisita.findAll", query="SELECT i FROM IngresSalidaVisita i")
public class IngresSalidaVisita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngresSalidaVisitaPK id;

	@Column(nullable=false)
	private Integer tipo;

	//bi-directional many-to-one association to Tranca
	@ManyToOne
	@JoinColumn(name="id_tranca", nullable=false, insertable=false, updatable=false)
	private Tranca tranca;

	//bi-directional many-to-one association to VehiculoVisita
	@ManyToOne
	@JoinColumn(name="placa", nullable=false, insertable=false, updatable=false)
	private VehiculoVisita vehiculoVisita;

	public IngresSalidaVisita() {
	}

	public IngresSalidaVisitaPK getId() {
		return this.id;
	}

	public void setId(IngresSalidaVisitaPK id) {
		this.id = id;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
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