package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vehiculo_visita database table.
 * 
 */
@Entity
@Table(name="vehiculo_visita")
@NamedQuery(name="VehiculoVisita.findAll", query="SELECT v FROM VehiculoVisita v")
public class VehiculoVisita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String placa;

	@Column(length=100)
	private String marca;

	//bi-directional many-to-one association to IngresoSalidaVisita
	@OneToMany(mappedBy="vehiculoVisita")
	private List<IngresoSalidaVisita> ingresoSalidaVisitas;

	//bi-directional many-to-many association to Visita
	@ManyToMany(mappedBy="vehiculoVisitas2")
	private List<Visita> visitas;

	public VehiculoVisita() {
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public List<IngresoSalidaVisita> getIngresoSalidaVisitas() {
		return this.ingresoSalidaVisitas;
	}

	public void setIngresoSalidaVisitas(List<IngresoSalidaVisita> ingresoSalidaVisitas) {
		this.ingresoSalidaVisitas = ingresoSalidaVisitas;
	}

	public IngresoSalidaVisita addIngresoSalidaVisita(IngresoSalidaVisita ingresoSalidaVisita) {
		getIngresoSalidaVisitas().add(ingresoSalidaVisita);
		ingresoSalidaVisita.setVehiculoVisita(this);

		return ingresoSalidaVisita;
	}

	public IngresoSalidaVisita removeIngresoSalidaVisita(IngresoSalidaVisita ingresoSalidaVisita) {
		getIngresoSalidaVisitas().remove(ingresoSalidaVisita);
		ingresoSalidaVisita.setVehiculoVisita(null);

		return ingresoSalidaVisita;
	}

	public List<Visita> getVisitas() {
		return this.visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}

}