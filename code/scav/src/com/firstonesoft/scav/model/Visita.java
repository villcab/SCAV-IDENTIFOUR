package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the visita database table.
 * 
 */
@Entity
@Table(name="visita")
@NamedQuery(name="Visita.findAll", query="SELECT v FROM Visita v")
public class Visita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ci;

	@Column(nullable=false, length=200)
	private String apellidos;

	@Column(nullable=false, length=70)
	private String nombres;

	//bi-directional many-to-one association to VehiculoVisita
	@OneToMany(mappedBy="visita")
	private List<VehiculoVisita> vehiculoVisitas1;

	//bi-directional many-to-many association to VehiculoVisita
	@ManyToMany
	@JoinTable(
		name="visita_vehiculo"
		, joinColumns={
			@JoinColumn(name="ci", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="placa", nullable=false)
			}
		)
	private List<VehiculoVisita> vehiculoVisitas2;

	public Visita() {
	}

	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<VehiculoVisita> getVehiculoVisitas1() {
		return this.vehiculoVisitas1;
	}

	public void setVehiculoVisitas1(List<VehiculoVisita> vehiculoVisitas1) {
		this.vehiculoVisitas1 = vehiculoVisitas1;
	}

	public VehiculoVisita addVehiculoVisitas1(VehiculoVisita vehiculoVisitas1) {
		getVehiculoVisitas1().add(vehiculoVisitas1);
		vehiculoVisitas1.setVisita(this);

		return vehiculoVisitas1;
	}

	public VehiculoVisita removeVehiculoVisitas1(VehiculoVisita vehiculoVisitas1) {
		getVehiculoVisitas1().remove(vehiculoVisitas1);
		vehiculoVisitas1.setVisita(null);

		return vehiculoVisitas1;
	}

	public List<VehiculoVisita> getVehiculoVisitas2() {
		return this.vehiculoVisitas2;
	}

	public void setVehiculoVisitas2(List<VehiculoVisita> vehiculoVisitas2) {
		this.vehiculoVisitas2 = vehiculoVisitas2;
	}

}