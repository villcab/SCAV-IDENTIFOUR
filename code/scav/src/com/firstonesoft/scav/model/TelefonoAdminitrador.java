package com.firstonesoft.scav.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the telefono_adminitrador database table.
 * 
 */
@Entity
@Table(name="telefono_adminitrador")
@NamedQuery(name="TelefonoAdminitrador.findAll", query="SELECT t FROM TelefonoAdminitrador t")
public class TelefonoAdminitrador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer telefono;

	//bi-directional many-to-one association to AdministradorEntorno
	@ManyToOne
	@JoinColumn(name="ci", nullable=false)
	private AdministradorEntorno administradorEntorno;

	public TelefonoAdminitrador() {
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public AdministradorEntorno getAdministradorEntorno() {
		return this.administradorEntorno;
	}

	public void setAdministradorEntorno(AdministradorEntorno administradorEntorno) {
		this.administradorEntorno = administradorEntorno;
	}

}