package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aviso_propietario database table.
 * 
 */
@Entity
@Table(name="aviso_propietario")
@NamedQuery(name="AvisoPropietario.findAll", query="SELECT a FROM AvisoPropietario a")
public class AvisoPropietario implements Serializable {
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

	//bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name="ci")
	private Propietario propietario;

	public AvisoPropietario() {
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

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

}