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
	@Column(unique=true, nullable=false)
	private Integer ci;

	@Column(nullable=false, length=200)
	private String apellidos;

	@Column(nullable=false, length=70)
	private String nombres;

	//bi-directional many-to-one association to VehiculoVisita
	@OneToMany(mappedBy="visita")
	private List<VehiculoVisita> vehiculoVisitas;

	public Visita() {
	}

	public Integer getCi() {
		return this.ci;
	}

	public void setCi(Integer ci) {
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

	public VehiculoVisita addVehiculoVisita(VehiculoVisita vehiculoVisita) {
		getVehiculoVisitas().add(vehiculoVisita);
		vehiculoVisita.setVisita(this);

		return vehiculoVisita;
	}

	public VehiculoVisita removeVehiculoVisita(VehiculoVisita vehiculoVisita) {
		getVehiculoVisitas().remove(vehiculoVisita);
		vehiculoVisita.setVisita(null);

		return vehiculoVisita;
	}

}