package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entorno database table.
 * 
 */
@Entity
@Table(name="entorno")
@NamedQuery(name="Entorno.findAll", query="SELECT e FROM Entorno e")
public class Entorno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	private byte[] foto;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(length=100)
	private String ubicacion;

	//bi-directional many-to-one association to AdministradorEntorno
	@ManyToOne
	@JoinColumn(name="ci")
	private AdministradorEntorno administradorEntorno;

	//bi-directional many-to-many association to Vehiculo
	@ManyToMany
	@JoinTable(
		name="entorno_vehiculo"
		, joinColumns={
			@JoinColumn(name="id_entorno", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="placa", nullable=false)
			}
		)
	private List<Vehiculo> vehiculos;

	//bi-directional many-to-one association to Licencia
	@OneToMany(mappedBy="entorno")
	private List<Licencia> licencias;

	public Entorno() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public AdministradorEntorno getAdministradorEntorno() {
		return this.administradorEntorno;
	}

	public void setAdministradorEntorno(AdministradorEntorno administradorEntorno) {
		this.administradorEntorno = administradorEntorno;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Licencia> getLicencias() {
		return this.licencias;
	}

	public void setLicencias(List<Licencia> licencias) {
		this.licencias = licencias;
	}

	public Licencia addLicencia(Licencia licencia) {
		getLicencias().add(licencia);
		licencia.setEntorno(this);

		return licencia;
	}

	public Licencia removeLicencia(Licencia licencia) {
		getLicencias().remove(licencia);
		licencia.setEntorno(null);

		return licencia;
	}

}