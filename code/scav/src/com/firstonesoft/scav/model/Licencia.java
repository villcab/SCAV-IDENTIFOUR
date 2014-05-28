package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the licencia database table.
 * 
 */
@Entity
@Table(name="licencia")
@NamedQuery(name="Licencia.findAll", query="SELECT l FROM Licencia l")
public class Licencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Boolean estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_caducidad")
	private Date fechaCaducidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Column(name="tipo_licencia")
	private Integer tipoLicencia;

	//bi-directional many-to-one association to Entorno
	@ManyToOne
	@JoinColumn(name="id_entorno")
	private Entorno entorno;

	public Licencia() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaCaducidad() {
		return this.fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public Date getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Integer getTipoLicencia() {
		return this.tipoLicencia;
	}

	public void setTipoLicencia(Integer tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	public Entorno getEntorno() {
		return this.entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

}