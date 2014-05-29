package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the propietario database table.
 * 
 */
@Entity
@Table(name="propietario")
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p")
public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ci;

	@Column(nullable=false, length=200)
	private String apellidos;

	@Column(nullable=false)
	private Boolean estado;

	private byte[] foto;

	@Column(nullable=false, length=70)
	private String nombres;

	@Column(name="nro_licencia", nullable=false)
	private String nroLicencia;

	//bi-directional many-to-one association to AdvertenciaPropietario
	@OneToMany(mappedBy="propietario")
	private List<AdvertenciaPropietario> advertenciaPropietarios;

	//bi-directional many-to-many association to Vehiculo
	@ManyToMany
	@JoinTable(
		name="propietario_vehiculo"
		, joinColumns={
			@JoinColumn(name="ci", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="placa", nullable=false)
			}
		)
	private List<Vehiculo> vehiculos;

	//bi-directional many-to-one association to TelefonoPropietario
	@OneToMany(mappedBy="propietario")
	private List<TelefonoPropietario> telefonoPropietarios;

	public Propietario() {
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

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNroLicencia() {
		return this.nroLicencia;
	}

	public void setNroLicencia(String nroLicencia) {
		this.nroLicencia = nroLicencia;
	}

	public List<AdvertenciaPropietario> getAdvertenciaPropietarios() {
		return this.advertenciaPropietarios;
	}

	public void setAdvertenciaPropietarios(List<AdvertenciaPropietario> advertenciaPropietarios) {
		this.advertenciaPropietarios = advertenciaPropietarios;
	}

	public AdvertenciaPropietario addAdvertenciaPropietario(AdvertenciaPropietario advertenciaPropietario) {
		getAdvertenciaPropietarios().add(advertenciaPropietario);
		advertenciaPropietario.setPropietario(this);

		return advertenciaPropietario;
	}

	public AdvertenciaPropietario removeAdvertenciaPropietario(AdvertenciaPropietario advertenciaPropietario) {
		getAdvertenciaPropietarios().remove(advertenciaPropietario);
		advertenciaPropietario.setPropietario(null);

		return advertenciaPropietario;
	}

	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<TelefonoPropietario> getTelefonoPropietarios() {
		return this.telefonoPropietarios;
	}

	public void setTelefonoPropietarios(List<TelefonoPropietario> telefonoPropietarios) {
		this.telefonoPropietarios = telefonoPropietarios;
	}

	public TelefonoPropietario addTelefonoPropietario(TelefonoPropietario telefonoPropietario) {
		getTelefonoPropietarios().add(telefonoPropietario);
		telefonoPropietario.setPropietario(this);

		return telefonoPropietario;
	}

	public TelefonoPropietario removeTelefonoPropietario(TelefonoPropietario telefonoPropietario) {
		getTelefonoPropietarios().remove(telefonoPropietario);
		telefonoPropietario.setPropietario(null);

		return telefonoPropietario;
	}

	@Override
	public String toString() {
		return "Propietario [ci=" + ci + ", apellidos=" + apellidos + ", nombres=" + nombres + ", nroLicencia=" + nroLicencia + "]";
	}

}