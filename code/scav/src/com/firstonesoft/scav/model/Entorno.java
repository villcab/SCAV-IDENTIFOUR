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
	@SequenceGenerator(name="entorno_id_seq_generator", sequenceName="entorno_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="entorno_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	private byte[] foto;

	@Column(name="licencia_activa", nullable=false)
	private Boolean licenciaActiva;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(length=100)
	private String ubicacion;

	//bi-directional many-to-one association to AdministradorEntorno
	@ManyToOne
	@JoinColumn(name="ci")
	private AdministradorEntorno administradorEntorno;

	//bi-directional many-to-one association to Guardia
	@OneToMany(mappedBy="entorno")
	private List<Guardia> guardias;

	//bi-directional many-to-one association to Propietario
	@OneToMany(mappedBy="entorno")
	private List<Propietario> propietarios;

	//bi-directional many-to-one association to Tranca
	@OneToMany(mappedBy="entorno")
	private List<Tranca> trancas;

	//bi-directional many-to-one association to Vehiculo
	@OneToMany(mappedBy="entorno")
	private List<Vehiculo> vehiculos;

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

	public Boolean getLicenciaActiva() {
		return this.licenciaActiva;
	}

	public void setLicenciaActiva(Boolean licenciaActiva) {
		this.licenciaActiva = licenciaActiva;
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

	public List<Guardia> getGuardias() {
		return this.guardias;
	}

	public void setGuardias(List<Guardia> guardias) {
		this.guardias = guardias;
	}

	public Guardia addGuardia(Guardia guardia) {
		getGuardias().add(guardia);
		guardia.setEntorno(this);

		return guardia;
	}

	public Guardia removeGuardia(Guardia guardia) {
		getGuardias().remove(guardia);
		guardia.setEntorno(null);

		return guardia;
	}

	public List<Propietario> getPropietarios() {
		return this.propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public Propietario addPropietario(Propietario propietario) {
		getPropietarios().add(propietario);
		propietario.setEntorno(this);

		return propietario;
	}

	public Propietario removePropietario(Propietario propietario) {
		getPropietarios().remove(propietario);
		propietario.setEntorno(null);

		return propietario;
	}

	public List<Tranca> getTrancas() {
		return this.trancas;
	}

	public void setTrancas(List<Tranca> trancas) {
		this.trancas = trancas;
	}

	public Tranca addTranca(Tranca tranca) {
		getTrancas().add(tranca);
		tranca.setEntorno(this);

		return tranca;
	}

	public Tranca removeTranca(Tranca tranca) {
		getTrancas().remove(tranca);
		tranca.setEntorno(null);

		return tranca;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Vehiculo addVehiculo(Vehiculo vehiculo) {
		getVehiculos().add(vehiculo);
		vehiculo.setEntorno(this);

		return vehiculo;
	}

	public Vehiculo removeVehiculo(Vehiculo vehiculo) {
		getVehiculos().remove(vehiculo);
		vehiculo.setEntorno(null);

		return vehiculo;
	}

	@Override
	public String toString() {
		return "Entorno [id=" + id + ", nombre=" + nombre + ", ubicacion="
				+ ubicacion + "]";
	}

}