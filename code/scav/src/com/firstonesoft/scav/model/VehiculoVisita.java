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

	//bi-directional many-to-one association to IngresSalidaVisita
	@OneToMany(mappedBy="vehiculoVisita")
	private List<IngresSalidaVisita> ingresSalidaVisitas;

	//bi-directional many-to-one association to Visita
	@ManyToOne
	@JoinColumn(name="ci")
	private Visita visita;

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

	public List<IngresSalidaVisita> getIngresSalidaVisitas() {
		return this.ingresSalidaVisitas;
	}

	public void setIngresSalidaVisitas(List<IngresSalidaVisita> ingresSalidaVisitas) {
		this.ingresSalidaVisitas = ingresSalidaVisitas;
	}

	public IngresSalidaVisita addIngresSalidaVisita(IngresSalidaVisita ingresSalidaVisita) {
		getIngresSalidaVisitas().add(ingresSalidaVisita);
		ingresSalidaVisita.setVehiculoVisita(this);

		return ingresSalidaVisita;
	}

	public IngresSalidaVisita removeIngresSalidaVisita(IngresSalidaVisita ingresSalidaVisita) {
		getIngresSalidaVisitas().remove(ingresSalidaVisita);
		ingresSalidaVisita.setVehiculoVisita(null);

		return ingresSalidaVisita;
	}

	public Visita getVisita() {
		return this.visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

}