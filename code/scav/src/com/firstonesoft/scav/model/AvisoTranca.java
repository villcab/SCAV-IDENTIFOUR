package com.firstonesoft.scav.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String descripcion;

	@Column(nullable=false)
	private Boolean enviado;
	
	@Column(length=100, name="de")
	private String de;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_hora")
	private Date fechaHora;

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

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Tranca getTranca() {
		return this.tranca;
	}

	public void setTranca(Tranca tranca) {
		this.tranca = tranca;
	}
	
	public String getDe() {
		return de;
	}
	
	public void setDe(String de) {
		this.de = de;
	}

}