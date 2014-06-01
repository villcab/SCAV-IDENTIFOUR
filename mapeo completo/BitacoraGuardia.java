package com.firstonesoft.scav.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the bitacora_guardia database table.
 * 
 */
@Entity
@Table(name="bitacora_guardia")
@NamedQuery(name="BitacoraGuardia.findAll", query="SELECT b FROM BitacoraGuardia b")
public class BitacoraGuardia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="bitacora_guardia_id_seq_generator", sequenceName="bitacora_guardia_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bitacora_guardia_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=100)
	private String accion;

	@Column(length=250)
	private String detalle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

	//bi-directional many-to-one association to Guardia
	@ManyToOne
	@JoinColumn(name="ci_guardia")
	private Guardia guardia;

	public BitacoraGuardia() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Guardia getGuardia() {
		return this.guardia;
	}

	public void setGuardia(Guardia guardia) {
		this.guardia = guardia;
	}

}