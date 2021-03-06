package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ingreso_salida database table.
 * 
 */
@Embeddable
public class IngresoSalidaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora", unique=true, nullable=false)
	private java.util.Date fechaHora;

	@Column(insertable=false, updatable=false, unique=true, nullable=false, length=10)
	private String placa;

	@Column(name="id_tranca", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer idTranca;

	public IngresoSalidaPK() {
	}
	public java.util.Date getFechaHora() {
		return this.fechaHora;
	}
	public void setFechaHora(java.util.Date fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String getPlaca() {
		return this.placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Integer getIdTranca() {
		return this.idTranca;
	}
	public void setIdTranca(Integer idTranca) {
		this.idTranca = idTranca;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IngresoSalidaPK)) {
			return false;
		}
		IngresoSalidaPK castOther = (IngresoSalidaPK)other;
		return 
			this.fechaHora.equals(castOther.fechaHora)
			&& this.placa.equals(castOther.placa)
			&& this.idTranca.equals(castOther.idTranca);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fechaHora.hashCode();
		hash = hash * prime + this.placa.hashCode();
		hash = hash * prime + this.idTranca.hashCode();
		
		return hash;
	}
}