package com.firstonesoft.scav.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tranca database table.
 * 
 */
@Entity
@Table(name="tranca")
@NamedQuery(name="Tranca.findAll", query="SELECT t FROM Tranca t")
public class Tranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=100)
	private String descripcion;

	@Column(nullable=false, length=10)
	private String tipo;
	
	//bi-directional many-to-one association to Entorno
	@ManyToOne
	@JoinColumn(name="id_entorno")
	private Entorno entorno;

	//bi-directional many-to-one association to AvisoTranca
	@OneToMany(mappedBy="tranca")
	private List<AvisoTranca> avisoTrancas;

	//bi-directional many-to-one association to IngresoSalida
	@OneToMany(mappedBy="tranca")
	private List<IngresoSalida> ingresoSalidas;

	//bi-directional many-to-one association to IngresoSalidaVisita
	@OneToMany(mappedBy="tranca")
	private List<IngresoSalidaVisita> ingresoSalidaVisitas;

	public Tranca() {
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<AvisoTranca> getAvisoTrancas() {
		return this.avisoTrancas;
	}

	public void setAvisoTrancas(List<AvisoTranca> avisoTrancas) {
		this.avisoTrancas = avisoTrancas;
	}

	public AvisoTranca addAvisoTranca(AvisoTranca avisoTranca) {
		getAvisoTrancas().add(avisoTranca);
		avisoTranca.setTranca(this);

		return avisoTranca;
	}

	public AvisoTranca removeAvisoTranca(AvisoTranca avisoTranca) {
		getAvisoTrancas().remove(avisoTranca);
		avisoTranca.setTranca(null);

		return avisoTranca;
	}

	public List<IngresoSalida> getIngresoSalidas() {
		return this.ingresoSalidas;
	}

	public void setIngresoSalidas(List<IngresoSalida> ingresoSalidas) {
		this.ingresoSalidas = ingresoSalidas;
	}

	public IngresoSalida addIngresoSalida(IngresoSalida ingresoSalida) {
		getIngresoSalidas().add(ingresoSalida);
		ingresoSalida.setTranca(this);

		return ingresoSalida;
	}

	public IngresoSalida removeIngresoSalida(IngresoSalida ingresoSalida) {
		getIngresoSalidas().remove(ingresoSalida);
		ingresoSalida.setTranca(null);

		return ingresoSalida;
	}

	public List<IngresoSalidaVisita> getIngresoSalidaVisitas() {
		return this.ingresoSalidaVisitas;
	}

	public void setIngresoSalidaVisitas(List<IngresoSalidaVisita> ingresoSalidaVisitas) {
		this.ingresoSalidaVisitas = ingresoSalidaVisitas;
	}

	public IngresoSalidaVisita addIngresoSalidaVisita(IngresoSalidaVisita ingresoSalidaVisita) {
		getIngresoSalidaVisitas().add(ingresoSalidaVisita);
		ingresoSalidaVisita.setTranca(this);

		return ingresoSalidaVisita;
	}

	public IngresoSalidaVisita removeIngresoSalidaVisita(IngresoSalidaVisita ingresoSalidaVisita) {
		getIngresoSalidaVisitas().remove(ingresoSalidaVisita);
		ingresoSalidaVisita.setTranca(null);

		return ingresoSalidaVisita;
	}
	
	public Entorno getEntorno() {
		return entorno;
	}
	
	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

}