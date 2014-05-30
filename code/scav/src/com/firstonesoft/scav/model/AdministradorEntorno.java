package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the administrador_entorno database table.
 * 
 */
@Entity
@Table(name="administrador_entorno")
@NamedQuery(name="AdministradorEntorno.findAll", query="SELECT a FROM AdministradorEntorno a")
public class AdministradorEntorno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=10)
	private String ci;

	@Column(nullable=false, length=200)
	private String apellidos;

	@Column(length=50)
	private String email;

	@Column(nullable=false, length=70)
	private String nombres;

	@Column(nullable=false, length=100)
	private String password;

	//bi-directional many-to-one association to Entorno
	@OneToMany(mappedBy="administradorEntorno")
	private List<Entorno> entornos;

	//bi-directional many-to-one association to TelefonoAdminitrador
	@OneToMany(mappedBy="administradorEntorno")
	private List<TelefonoAdminitrador> telefonoAdminitradors;

	public AdministradorEntorno() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Entorno> getEntornos() {
		return this.entornos;
	}

	public void setEntornos(List<Entorno> entornos) {
		this.entornos = entornos;
	}

	public Entorno addEntorno(Entorno entorno) {
		getEntornos().add(entorno);
		entorno.setAdministradorEntorno(this);

		return entorno;
	}

	public Entorno removeEntorno(Entorno entorno) {
		getEntornos().remove(entorno);
		entorno.setAdministradorEntorno(null);

		return entorno;
	}

	public List<TelefonoAdminitrador> getTelefonoAdminitradors() {
		return this.telefonoAdminitradors;
	}

	public void setTelefonoAdminitradors(List<TelefonoAdminitrador> telefonoAdminitradors) {
		this.telefonoAdminitradors = telefonoAdminitradors;
	}

	public TelefonoAdminitrador addTelefonoAdminitrador(TelefonoAdminitrador telefonoAdminitrador) {
		getTelefonoAdminitradors().add(telefonoAdminitrador);
		telefonoAdminitrador.setAdministradorEntorno(this);

		return telefonoAdminitrador;
	}

	public TelefonoAdminitrador removeTelefonoAdminitrador(TelefonoAdminitrador telefonoAdminitrador) {
		getTelefonoAdminitradors().remove(telefonoAdminitrador);
		telefonoAdminitrador.setAdministradorEntorno(null);

		return telefonoAdminitrador;
	}

}