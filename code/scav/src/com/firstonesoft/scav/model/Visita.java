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
	private List<VehiculoVisita> vehiculoVisitas;

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

	public List<VehiculoVisita> getVehiculoVisitas() {
		return this.vehiculoVisitas;
	}

	public void setVehiculoVisitas(List<VehiculoVisita> vehiculoVisitas) {
		this.vehiculoVisitas = vehiculoVisitas;
	}

	@Override
	public String toString() {
		return "Visita [ci=" + ci + ", apellidos=" + apellidos + ", nombres="
				+ nombres + "]";
	}

}