package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aviso_tranca database table.
 * 
 */
@Entity
@Table(name="aviso_tranca")
@NamedQuery(name="AvisoTranca.findAll", query="SELECT a FROM AvisoTranca a")
public class AvisoTranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String descripcion;

	@Column(nullable=false)
	private Boolean enviado;

	@Column(name="fecha_hora")
	private Timestamp fechaHora;

	//bi-directional many-to-one association to Tranca
	@ManyToOne
	@JoinColumn(name="id_tranca")
	private Tranca tranca;

	public AvisoTranca() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEnviado() {
		return this.enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Tranca getTranca() {
		return this.tranca;
	}

	public void setTranca(Tranca tranca) {
		this.tranca = tranca;
	}

}