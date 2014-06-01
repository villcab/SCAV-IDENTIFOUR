package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the guardia database table.
 * 
 */
@Entity
@Table(name="guardia")
@NamedQuery(name="Guardia.findAll", query="SELECT g FROM Guardia g")
public class Guardia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ci;

	@Column(nullable=false, length=100)
	private String apellido;

	@Column(nullable=false)
	private Boolean estado;

	@Column(nullable=false, length=100)
	private String nombre;

	@Column(nullable=false, length=50)
	private String password;

	//bi-directional many-to-one association to BitacoraGuardia
	@OneToMany(mappedBy="guardia")
	private List<BitacoraGuardia> bitacoraGuardias;

	//bi-directional many-to-one association to Entorno
	@ManyToOne
	@JoinColumn(name="id_entorno")
	private Entorno entorno;

	public Guardia() {
	}

	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BitacoraGuardia> getBitacoraGuardias() {
		return this.bitacoraGuardias;
	}

	public void setBitacoraGuardias(List<BitacoraGuardia> bitacoraGuardias) {
		this.bitacoraGuardias = bitacoraGuardias;
	}

	public BitacoraGuardia addBitacoraGuardia(BitacoraGuardia bitacoraGuardia) {
		getBitacoraGuardias().add(bitacoraGuardia);
		bitacoraGuardia.setGuardia(this);

		return bitacoraGuardia;
	}

	public BitacoraGuardia removeBitacoraGuardia(BitacoraGuardia bitacoraGuardia) {
		getBitacoraGuardias().remove(bitacoraGuardia);
		bitacoraGuardia.setGuardia(null);

		return bitacoraGuardia;
	}

	public Entorno getEntorno() {
		return this.entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

	@Override
	public String toString() {
		return "Guardia [ci=" + ci + ", apellido=" + apellido + ", estado="
				+ estado + ", nombre=" + nombre + "]";
	}
	

}