package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to AdvertenciaTranca
	@OneToMany(mappedBy="tranca")
	private List<AdvertenciaTranca> advertenciaTrancas;

	//bi-directional many-to-one association to IngresSalidaVisita
	@OneToMany(mappedBy="tranca")
	private List<IngresSalidaVisita> ingresSalidaVisitas;

	//bi-directional many-to-one association to IngresoSalida
	@OneToMany(mappedBy="tranca")
	private List<IngresoSalida> ingresoSalidas;

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

	public List<AdvertenciaTranca> getAdvertenciaTrancas() {
		return this.advertenciaTrancas;
	}

	public void setAdvertenciaTrancas(List<AdvertenciaTranca> advertenciaTrancas) {
		this.advertenciaTrancas = advertenciaTrancas;
	}

	public AdvertenciaTranca addAdvertenciaTranca(AdvertenciaTranca advertenciaTranca) {
		getAdvertenciaTrancas().add(advertenciaTranca);
		advertenciaTranca.setTranca(this);

		return advertenciaTranca;
	}

	public AdvertenciaTranca removeAdvertenciaTranca(AdvertenciaTranca advertenciaTranca) {
		getAdvertenciaTrancas().remove(advertenciaTranca);
		advertenciaTranca.setTranca(null);

		return advertenciaTranca;
	}

	public List<IngresSalidaVisita> getIngresSalidaVisitas() {
		return this.ingresSalidaVisitas;
	}

	public void setIngresSalidaVisitas(List<IngresSalidaVisita> ingresSalidaVisitas) {
		this.ingresSalidaVisitas = ingresSalidaVisitas;
	}

	public IngresSalidaVisita addIngresSalidaVisita(IngresSalidaVisita ingresSalidaVisita) {
		getIngresSalidaVisitas().add(ingresSalidaVisita);
		ingresSalidaVisita.setTranca(this);

		return ingresSalidaVisita;
	}

	public IngresSalidaVisita removeIngresSalidaVisita(IngresSalidaVisita ingresSalidaVisita) {
		getIngresSalidaVisitas().remove(ingresSalidaVisita);
		ingresSalidaVisita.setTranca(null);

		return ingresSalidaVisita;
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

}